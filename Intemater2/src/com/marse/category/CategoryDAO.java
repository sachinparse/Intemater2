package com.marse.category;

import java.util.List;

import com.marse.model.Category;

public interface CategoryDAO {
	
	public List<Category> listOfCategory();  // list of all the categories
	public void addCategory(Category objCategory); // adding a new category
	public void updateCategory(Category objCategory); // updating an existing category
	public void deleteCategory(Category objCategory); // deleting the selected category

}
