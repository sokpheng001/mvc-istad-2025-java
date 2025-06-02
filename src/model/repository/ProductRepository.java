package model.repository;

import model.dto.UpdateProductDto;
import model.entities.Product;
import utils.DatabaseConnectionConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductRepository implements Repository<Product, Integer> {

    @Override
    public Product save(Product product) {
        String sql = """
                INSERT INTO products(uuid, p_name, expired_date)
                VALUES(?,?,?)
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            assert con != null;
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, product.getUuid());
            pre.setString(2, product.getPName());
            pre.setDate(3, product.getExpiredDate());
            int rowAffected = pre.executeUpdate();
            return rowAffected>0 ? product: null;
        }catch (Exception exception){
            System.out.println("[!] Error during insert data to Product table: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        String sql = """
                SELECT * FROM products
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery(sql);
            List<Product> products = new ArrayList<>();
            while (result.next()){
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setUuid(result.getString("uuid"));
                product.setPName(result.getString("p_name"));
                product.setExpiredDate(result.getDate("expired_date"));
                // add product to list
                products.add(product);
            }
            return products;
        }catch (Exception exception){
            System.out.println("[!] Error during get all products: " + exception.getMessage());
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) throws NoSuchElementException {
        String sql = """
                DELETE FROM products
                WHERE id = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1,  id);
            int rowAffected = pre.executeUpdate();
            return rowAffected>0? rowAffected: 0;
        }catch (Exception exception){
            System.out.println("[!] Error during delete product: " + exception.getMessage());
        }
        return 0;
    }
    public Product findByProductUuid(String uuid){
        String sql = """
                SELECT * FROM products
                WHERE uuid = ?
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, uuid);
            ResultSet result = pre.executeQuery();
            Product product = new Product();
            while (result.next()){
                product.setId(result.getInt("id"));
                product.setUuid(result.getString("uuid"));
                product.setPName(result.getString("p_name"));
                product.setExpiredDate(result.getDate("expired_date"));
            }
            return product;
        }catch (Exception exception){
            System.out.println("[!] Error during get product by uuid: " + exception.getMessage());
        }
        return null;
    }
    public Product updateProductByUuid(String uuid, UpdateProductDto updateProductDto){
        Product product
                 = findByProductUuid(uuid);
        if(product!=null){
            String sql = """
                    UPDATE products
                    SET p_name=?, expired_date=?
                    WHERE uuid = ?
                    """;
            try(Connection con = DatabaseConnectionConfig.getConnection()){
                PreparedStatement pre = con.prepareStatement(sql);
                pre.setString(1, updateProductDto.pName());
                pre.setDate(2, updateProductDto.expired_date());
                pre.setString(3, uuid);
                int rowAffected = pre.executeUpdate();
                if(rowAffected>0){
                    return findByProductUuid(uuid);
                }
            }catch (Exception exception){
                System.err.println("[!] Error during update product by uuid: " + exception.getMessage());
            }
            return null;
        }
        throw new NoSuchElementException("Cannot find Product");
    }
}
