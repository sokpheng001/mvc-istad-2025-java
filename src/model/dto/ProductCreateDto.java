package model.dto;

import java.time.LocalDate;

public record ProductCreateDto(
        String pName,
        LocalDate expiredDate
) {
}
