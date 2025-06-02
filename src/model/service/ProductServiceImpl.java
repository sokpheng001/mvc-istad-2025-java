package model.service;

import mapper.ProductMapper;
import model.repository.ProductRepository;
import model.dto.ProductCreateDto;
import model.dto.ProductResponseDto;
import model.dto.UpdateProductDto;
import model.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository = new ProductRepository();
    @Override
    public List<ProductResponseDto> getAllProducts() {
        List<ProductResponseDto> productResponseDtoList
                 = new ArrayList<>();
        productRepository.findAll()
                .stream()
                .forEach(p->{
                    productResponseDtoList
                            .add(new ProductResponseDto(p.getUuid()
                                    , p.getPName(),
                                    p.getExpiredDate()));
                });
        return productResponseDtoList;
    }
    @Override
    public ProductResponseDto insertNewProduct(ProductCreateDto productCreateDto) {
        Product product1
                 = ProductMapper.mapFromProductCreateDtoToProduct(productCreateDto);
        return ProductMapper.mapFromProductToProductResponseDto(
                productRepository.save(product1)
        );
    }
    @Override
    public Integer deleteProductByUuid(String uuid) {
        try{
            Product product = productRepository.findByProductUuid(uuid);
            return productRepository.delete(product.getId());
        }catch (NoSuchElementException exception){
            System.out.println("Error during deleting Product by Uuid:" + exception.getMessage());
        }
        return 0;
    }
    @Override
    public ProductResponseDto getProductByUuid(String uuid) {
        try{
            return ProductMapper.mapFromProductToProductResponseDto(
                    productRepository.findByProductUuid(uuid)
            );
        }catch (NoSuchElementException exception){
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Override
    public ProductResponseDto updateProductByUuid(String uuid, UpdateProductDto updateProductDto) {
        try {
            return ProductMapper
                    .mapFromProductToProductResponseDto(
                            productRepository.updateProductByUuid(uuid, updateProductDto)
                    );
        }catch (NoSuchElementException exception){
            System.out.println(exception.getMessage()
            );
        }
        return null;
    }

}
