package mapper;

import model.dto.OrderResponseDto;
import model.dto.ProductResponseDto;
import model.dto.UserResponseDto;
import model.entities.Order;
import model.entities.Product;
import model.entities.User;
import model.repository.ProductRepository;
import model.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderMapper {
    private final UserMapper userMapper = new UserMapper();
    private final UserRepository userRepository = new UserRepository();
    //
    private final ProductRepository productRepository = new ProductRepository();
    public OrderResponseDto fromOrderToOrderResponseDto(Order order){
        User user = userRepository.findUserById(order.getUserId());
        UserResponseDto  userResponseDto = userMapper.fromUserToUserResponseDto(user);
        //
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for(Integer pId: order.getProductIds()){
            Product product = productRepository.findByProductId(pId);
            ProductResponseDto productResponseDto = ProductMapper
                    .mapFromProductToProductResponseDto(product);
            productResponseDtoList.add(productResponseDto);
        }
        return OrderResponseDto.builder()
                .orderNo(UUID.randomUUID().toString())
                .userResponseDto(userResponseDto)
                .products(productResponseDtoList)
                .build();
    }
}
