package ra.category_product.repository.impl;

import ra.category_product.model.Categories;
import ra.category_product.repository.ICategoriesRepository;


import java.util.ArrayList;
import java.util.List;

public class CategoriesRepositoryImpl implements ICategoriesRepository {
    public static List <Categories> categoryList = new ArrayList<>();
    @Override
    public List<Categories> getAll() {
        return categoryList;
    }

    @Override
    public void save(Categories categories) {
        categoryList.add(categories);
    }

    @Override
    public void update(Integer id, Categories categories) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getId() == id) {
                categoryList.set(i, categories);
                return;
            }
        }
    }

    @Override
    public Categories findById(Integer id) {
        for (Categories categories : categoryList) {
            if (categories.getId() == id) {
                return categories;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
      Categories indexDeleted = findById(id);
      if (indexDeleted != null) {
          categoryList.remove(indexDeleted);
      }
    }

    @Override
    public List<Categories> searchByName(String name) {
        List<Categories> result = new ArrayList<>();
        for (Categories category : categoryList) {
            if (category.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(category);
            }
        }
        return result;
    }


}
