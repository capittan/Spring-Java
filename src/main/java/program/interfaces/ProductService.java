package program.interfaces;

import program.dto.product.ProductCreateDTO;
import program.dto.product.ProductUpdateDTO;
import program.dto.product.ProductItemDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    ProductItemDTO create(ProductCreateDTO model);
    List<ProductItemDTO> get();
    ProductItemDTO edit(int id, ProductUpdateDTO model);
    ProductItemDTO getById(int id);
    ArrayList<ProductItemDTO> getByCategory(int id);
}