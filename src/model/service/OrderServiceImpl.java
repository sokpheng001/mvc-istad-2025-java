package model.service;

import mapper.OrderMapper;
import model.dto.OrderResponseDto;
import model.entities.Order;
import model.entities.Product;
import model.entities.User;
import model.repository.OrderRepository;
import model.repository.ProductRepository;
import model.repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper = new OrderMapper();
    private final OrderRepository orderRepository = new OrderRepository();
    private final UserRepository userRepository = new UserRepository();
    private final ProductRepository productRepository = new ProductRepository();
    @Override
    public OrderResponseDto makeOrder(String userUuid, List<String> productUuid) {
        //
        User userOrdered = userRepository.findUserByUuid(userUuid);
        List<Product> productList = new ArrayList<>();
        for(String pUuid: productUuid){
            Product product = productRepository.findByProductUuid(pUuid);
            System.out.println("Product: " + product);
            if(product!=null){
                productList.add(product);
            }
        }
        List<Integer> productIds = new ArrayList<>();
        for(Product p: productList){
            productIds.add(p.getId());
        }
        Order order = new Order(userOrdered.getId(), productIds);
        Order order1 = orderRepository.save(order);
        return orderMapper.fromOrderToOrderResponseDto(order1);
    }
}
