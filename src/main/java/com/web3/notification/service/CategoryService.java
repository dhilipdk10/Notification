package com.web3.notification.service;

import java.util.List;
import com.web3.notification.model.CategoryModel;

public interface CategoryService {

  String createNewCategory(CategoryModel categoryModel);

  CategoryModel updateAnExistingCategory(CategoryModel notificationCategoryModel, int id);

  List<CategoryModel> getAllCategory();
  
  CategoryModel getCategoryById(int id);

  String deleteCategoryDetailsById(int id);

}
