package ra.category_product.controller;

import ra.category_product.model.Categories;
import ra.category_product.repository.ICategoriesRepository;
import ra.category_product.repository.impl.CategoriesRepositoryImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesController", value = "/categories")
public class CategoriesController extends HttpServlet {
    private ICategoriesRepository categoriesRepository = new CategoriesRepositoryImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                showCreateForm(request, response);
                break;
            case "edit":
                showUpdateForm(request, response);
                break;
            case "delete":
                deleteCategory(request, response);
                break;
            case "search":
                searchCategories(request, response);
                break;
            default:
                listCategories(request, response);
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                createCategory(request, response);
                break;
            case "edit":
                editCategory(request, response);
                break;
            default:
                listCategories(request, response);
                break;
        }
    }

    private void listCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Categories> categoriesList = categoriesRepository.getAll();
        request.setAttribute("categoriesList", categoriesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/categories/listCategories.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/categories/createCategories.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = inputCategoriesId();
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Categories categories = new Categories(id, name, image, status);
        categoriesRepository.save(categories);
        listCategories(request, response);
    }

    private int inputCategoriesId() {
        if (categoriesRepository.getAll().isEmpty()){
            return 1;
        }
        int indexMax = 0;
        for (Categories categories : categoriesRepository.getAll()) {
            if (categories.getId() > indexMax) {
                indexMax = categories.getId();
            }
        }
        return indexMax + 1;
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categories existingCategories = categoriesRepository.findById(id);
        request.setAttribute("categories", existingCategories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/categories/editCategories.jsp");
        dispatcher.forward(request, response);
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String image = request.getParameter("image");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Categories categories = categoriesRepository.findById(id);
        if (categories!=null) {
            categories.setName(name);
            categories.setImage(image);
            categories.setStatus(status);
            categoriesRepository.update(id, categories);
            listCategories(request, response);
        }

    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categories categories = categoriesRepository.findById(id);
        if (categories != null) {
            categoriesRepository.delete(id);
            request.setAttribute("message", "Category has been deleted successfully.");
            response.sendRedirect("categories");
        } else {
            request.setAttribute("message", "Category not found.");
        }
    }
    private void searchCategories(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Categories> categoriesList = categoriesRepository.searchByName(name);
        request.setAttribute("categoriesList", categoriesList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/categories/listCategories.jsp");
        dispatcher.forward(request, response);
    }



}
