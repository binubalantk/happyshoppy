package com.binubalan.HappyShoppy.CatalogService.abstractions;

import com.binubalan.HappyShoppy.CatalogService.entities.Category;

import java.util.List;
import java.util.UUID;

public interface ICategoryService {
    List<Category> getAll();
    Category getById(String id);
    Category create(Category category);
    void remove(String id);
}
