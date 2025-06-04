package model.dto;

import java.sql.Date;


public record ProductResponseDto(
        String uuid,
        String pName,
        Date expiredDate
) {
}
