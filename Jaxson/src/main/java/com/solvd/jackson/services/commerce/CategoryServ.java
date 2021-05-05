package com.solvd.jackson.services.commerce;

import com.solvd.jackson.impl.commerce.CategoryDAO;
import com.solvd.jackson.models.commerce.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CategoryServ {
    private final static Logger logger = LogManager.getLogger(CategoryServ.class);
    private final CategoryDAO categoryDAO = new CategoryDAO();

    public void create(Category category) {
        categoryDAO.create(category);
        logger.info("Category created in DB: " + category.toString());
    }

    public Category getByID(int id) {
        Category category = categoryDAO.getByID(id);
        logger.info("Getting category by " + id + ": "+ category.toString());
        return category;
    }

    public void update(Category category) {
        categoryDAO.update(category);
        logger.info("Updating category: " + category.toString());
    }

    public void delete(int id) {
        categoryDAO.delete(id);
        logger.info("Deleting category with id " + id);
    }
}
