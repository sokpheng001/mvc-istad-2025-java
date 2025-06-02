package model.dto;

import java.sql.Date;
import java.time.LocalDate;

public record ProductCreateDto(
        String pName,
        Date expiredDate
) {
}
