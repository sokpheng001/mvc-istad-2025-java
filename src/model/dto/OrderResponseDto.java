package model.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderResponseDto(
        String orderNo,
        UserResponseDto userResponseDto,
        List<ProductResponseDto> products
) {
}
