package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.dto.category.CategoryUpdateDTO;
import program.entities.CategoryEntity;
import program.mapper.CategoryMapper;
import program.repositories.CategoryRepository;
import program.storage.StorageService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final StorageService storageService;

    @GetMapping
    public ResponseEntity<List<CategoryItemDTO>> index() {
        var list = categoryRepository.findAll();
        var model = categoryMapper.CategoryItemsByCategories(list);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(CategoryCreateDTO dto) {
        var image = storageService.save(dto.getBase64());
        CategoryEntity category = categoryMapper.CategoryByCreateCategoryDTO(dto);
        category.setName(dto.getName());
        category.setImage(image);
        categoryRepository.save(category);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryEntity> get(@PathVariable("id") Integer categoryId) {
        var category = categoryRepository.findById(categoryId);
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryEntity> update(@PathVariable("id") Integer categoryId, CategoryUpdateDTO dto) {
        CategoryEntity category = categoryRepository.findById(categoryId).get();
        category.setName(dto.getName());
        category.setImage(dto.getPhotoUrl());
        var updateCategory = categoryRepository.save(category);
        return new ResponseEntity<>(updateCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer categoryId) {
        categoryRepository.deleteById(categoryId);
        return new ResponseEntity<>("Category was successful deleted !", HttpStatus.OK);
    }
}