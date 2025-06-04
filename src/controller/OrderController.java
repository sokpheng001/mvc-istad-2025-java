package controller;

import model.dto.OrderResponseDto;
import model.service.OrderServiceImpl;

import java.util.List;

public class OrderController {
    private OrderServiceImpl orderService = new OrderServiceImpl();
    public OrderResponseDto makeOrder(String userUuid, List<String> productUuid){
        return orderService.makeOrder(userUuid, productUuid);
    }
}
