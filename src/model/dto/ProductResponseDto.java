package model.dto;

import java.time.LocalDate;

public record ProductResponseDto(
        String uuid,
        String pName,
        LocalDate expiredDate
) {
}
