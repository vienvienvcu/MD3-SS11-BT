package ra.category_product.repository.impl;

import ra.category_product.model.Categories;
import ra.category_product.model.Product;
import ra.category_product.repository.IProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements IProductRepository {
    public static List <Product> productList= new ArrayList<>();


    @Override
    public List<Product> getAll() {
        return productList;
    }

    @Override
    public void save(Product product) {
        productList.add(product);
    }

    @Override
    public void update(Integer id, Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                productList.set(i, product);
                return;
            }
        }
    }

    @Override
    public Product findById(Integer id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }
    @Override
    public void delete(Integer id) {
        Product indexDelete = findById(id);
        productList.remove(indexDelete);
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;

    }
}
