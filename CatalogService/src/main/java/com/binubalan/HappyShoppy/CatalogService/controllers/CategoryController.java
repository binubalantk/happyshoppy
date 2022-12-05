package com.binubalan.HappyShoppy.CatalogService.controllers;

import com.binubalan.HappyShoppy.CatalogService.abstractions.ICategoryService;
import com.binubalan.HappyShoppy.CatalogService.entities.Category;
import com.binubalan.HappyShoppy.CatalogService.models.CommonIdRequest;
import com.binubalan.HappyShoppy.CatalogService.models.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Categories")
@SuppressWarnings("unused")
public class CategoryController {

    @Autowired
    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategories")
    public ServerResponse<List<Category>> getCategories() {
        return new ServerResponse<>(true, this.categoryService.getAll());
    }

    @GetMapping("/getById")
    public ServerResponse<Category> getById(@RequestBody CommonIdRequest<String> idRequest) {
        return new ServerResponse<>(true, this.categoryService.getById(idRequest.id));
    }

    @PostMapping("/create")
    public ServerResponse<Category> create(@RequestBody Category category){
        return new ServerResponse<>(true, this.categoryService.create(category));
    }

    @PostMapping("/remove")
    public ServerResponse<Boolean> remove(@RequestBody CommonIdRequest<String> idRequest){
        this.categoryService.remove(idRequest.id);
        return new ServerResponse<>(true);
    }
}
