package model.dto;

import java.sql.Date;
import java.time.LocalDate;

public record ProductResponseDto(
        String uuid,
        String pName,
        Date expiredDate
) {
}
