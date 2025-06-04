package model.service;

import model.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    OrderResponseDto makeOrder(String userUuid, List<String> productUuid);
}
