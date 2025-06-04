package model.repository;

import model.entities.Order;
import utils.DatabaseConnectionConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class OrderRepository implements Repository<Order, Integer>{
    private void makeOrder(Integer userId, Integer productId){
        String sql = """
                INSERT INTO orders 
                VALUES(?,?)
                """;
        try(Connection con = DatabaseConnectionConfig.getConnection()){
            PreparedStatement pre  =con.prepareStatement(sql);
            pre.setInt(1, userId);
            pre.setInt(2, productId);
            int rowAffected = pre.executeUpdate();
        }catch (Exception exception){
            System.out.println("Error during making order: " + exception.getMessage());
        }
    }
    @Override
    public Order save(Order order) {
        for(Integer pId: order.getProductIds()){
            makeOrder(order.getUserId(), pId);
        }
        System.out.println("Order success, the rider is on the way :)");
        return order;
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }

    @Override
    public Integer delete(Integer id) {
        return 0;
    }
}
