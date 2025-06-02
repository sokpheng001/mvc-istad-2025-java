package model.service;

import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> getAllProducts();
    ProductResponseDto insertNewProduct(ProductCreateDto product);
    Integer deleteProductByUuid(String uuid);
    ProductResponseDto getProductByUuid(String uuid);
    ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto);
}
