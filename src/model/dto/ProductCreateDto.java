package model.dto;

import java.sql.Date;

public record ProductCreateDto(
        String pName,
        Date expiredDate
) {
}
