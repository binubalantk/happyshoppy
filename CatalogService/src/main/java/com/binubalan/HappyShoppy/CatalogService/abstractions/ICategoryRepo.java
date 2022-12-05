package com.binubalan.HappyShoppy.CatalogService.abstractions;

import com.binubalan.HappyShoppy.CatalogService.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryRepo {
    List<Category> getCategories();

    Category getById(UUID id);
    Category create(Category category);
    void remove(Category category);
}
