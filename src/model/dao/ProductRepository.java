package model.dao;

import model.ProductData;
import model.dto.UpdateProductDto;
import model.entities.Product;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepository implements Repository<Product, Integer> {

    @Override
    public Product save(Product product) {
        ProductData.products.add(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return ProductData.products;
    }

    @Override
    public Integer delete(Integer id) throws NoSuchElementException {
        Product product = ProductData.products
                .stream()
                .filter(p->p.getId().equals(id))
                .findFirst().get();
        boolean isDeleted = ProductData.products.remove(product);
        return isDeleted? 1: 0;
    }
    public Product findByProductUuid(String uuid){
        return ProductData.products
                .stream()
                .filter(p->p.getUuid().equals(uuid))
                .findFirst().get();
    }
    public Product updateProductByUuid(String uuid, UpdateProductDto updateProductDto){
        Product product
                 = findByProductUuid(uuid);
        if(product!=null){
            ProductData.products.remove(product);
            product.setPName(updateProductDto.pName());
            //
            ProductData.products.add(product);
            Collections.reverse(ProductData.products);
            return product;
        }
        throw new NoSuchElementException("Cannot find Product");
    }
}
