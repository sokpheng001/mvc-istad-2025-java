package controller;

import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;
import model.service.ProductServiceImpl;

import java.util.List;

public class ProductController {
    private ProductServiceImpl productService = new ProductServiceImpl();
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }
    public ProductResponseDto insertNewProduct(ProductCreateDto productCreateDto){
        return productService
                .insertNewProduct(productCreateDto);
    }
    public ProductResponseDto getProductByUuid(String uuid){
        return productService.getProductByUuid(uuid);
    }
    public Integer deleteProductByUuid(String uuid){
        return productService.deleteProductByUuid(uuid);
    }
    public ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto){
        return productService.updateProductByUuid(uuid, updateProductDto);
    }
}
