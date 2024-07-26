package ra.category_product.controller;

import jdk.jfr.Category;
import ra.category_product.model.Categories;
import ra.category_product.model.Product;
import ra.category_product.repository.impl.CategoriesRepositoryImpl;
import ra.category_product.repository.impl.ProductRepositoryImpl;
import ra.category_product.service.IProductRepository;

import java.io.*;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ProductController", value = "/product")
public class ProductController extends HttpServlet {
    private ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
    private CategoriesRepositoryImpl categoriesRepository = new CategoriesRepositoryImpl();


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                showCreateFormProduct(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProducts(request,response);
                break;
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                createProduct(request, response);
                break;
            case "edit":
                updateProduct(request, response);
                break;
            default:
                listProducts(request,response);
                break;
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List <Product> productList = productRepository.getAll();
        request.setAttribute("product", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product/listProducts.jsp");
        dispatcher.forward(request,response);
    }

    private void showCreateFormProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product/createProductForm.jsp");
        request.setAttribute("categoriesList", CategoriesRepositoryImpl.categoryList);
        dispatcher.forward(request,response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = inputProductId();
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String producer = request.getParameter("producer");
        int stock = Integer.parseInt(request.getParameter("stock"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Categories categoryModel = new Categories();

        for (Categories categories: CategoriesRepositoryImpl.categoryList){
            if (categories.getId() == categoryId){
                categoryModel = categories;
                break;
            }
        }

        Product product = new Product(id,name,image,categoryModel,price,description,producer,stock,status);
        productRepository.save(product);
//       response.sendRedirect("/productServlet"); cach thu hai
        listProducts(request,response);
    }
    private int inputProductId() {
        List<Product> productList = productRepository.getAll();
        if (productList.isEmpty()) {
            return 1;
        }
        int indexMax = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() > indexMax) {
                indexMax = productList.get(i).getId();
            }
        }
        return indexMax + 1;
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existtingProduct = productRepository.findById(id);
        request.setAttribute("product", existtingProduct);
        request.setAttribute("categoriesList", CategoriesRepositoryImpl.categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/product/updateProductForm.jsp");
        dispatcher.forward(request,response);

    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");// LAY VALUE
        String image = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        Categories categoryModel = categoriesRepository.findById(categoryId);
        Categories categories = categoryModel;
        double price = Double.parseDouble(request.getParameter("price"));
        String description = request.getParameter("description");
        String producer = request.getParameter("producer");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Product product = productRepository.findById(id);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setCategories(categories);
            product.setDescription(description);
            product.setProducer(producer);
            product.setStatus(status);
            productRepository.update(id, product);
        }
        try {
            listProducts(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productRepository.findById(id);
        if (product != null) {
            productRepository.delete(id);
            request.setAttribute("message", "product has been deleted successfully.");
        }else {
            request.setAttribute("message", "product is not found.");
        }
        response.sendRedirect("/product");
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name = request.getParameter("name");
        List<Product> productList;
        if (name == null || name.isEmpty()) {
            request.getRequestDispatcher("views/error.jsp").forward(request, response);
        }else {
            productList = productRepository.searchByName(name);
            request.setAttribute("product", productList);
            // Hiển thị danh sách sản phẩm tìm được trên trang productList.jsp
            request.getRequestDispatcher("views/product/listProducts.jsp").forward(request, response);
        }
    }


    public void destroy() {
    }
}