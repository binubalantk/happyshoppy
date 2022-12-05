package com.binubalan.HappyShoppy.CatalogService.Services;

import com.binubalan.HappyShoppy.CatalogService.abstractions.ICategoryRepo;
import com.binubalan.HappyShoppy.CatalogService.abstractions.ICategoryService;
import com.binubalan.HappyShoppy.CatalogService.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private final ICategoryRepo categoryRepo;

    public CategoryService(ICategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public List<Category> getAll() {
        return this.categoryRepo.getCategories();
    }

    @Override
    public Category getById(String id) {
        UUID uuid = UUID.fromString(id);
        return this.categoryRepo.getById(uuid);
    }

    @Override
    public Category create(Category category) {
        return this.categoryRepo.create(category);
    }

    @Override
    public void remove(String id) {
        UUID uuid = UUID.fromString(id);
        Category category = this.categoryRepo.getById(uuid);
        if(category == null){
            return;
        }
        this.categoryRepo.remove(category);
    }
}
