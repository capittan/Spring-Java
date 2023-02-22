package program.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import program.dto.product.ProductCreateDTO;
import program.dto.product.ProductUpdateDTO;
import program.entities.ProductEntity;
import program.repositories.ProductRepository;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> index() {
        var list = repository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductEntity> create(ProductCreateDTO dto) {
        ProductEntity product = new ProductEntity();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPhotoUrl(dto.getPhotoUrl());
        product.setDateCreate(dto.getDateCreate());

        repository.save(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductEntity> get(@PathVariable("id") Integer id) {
        var product = repository.findById(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductEntity> update(@PathVariable("id") Integer id, ProductUpdateDTO dto) {
        ProductEntity product = repository.findById(id).get();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPhotoUrl(dto.getPhotoUrl());
        product.setDateCreate(dto.getDateCreate());

        var updateProduct = repository.save(product);
        return new ResponseEntity<>(updateProduct, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Product was successful deleted !", HttpStatus.OK);
    }
}