package model.dto;

import lombok.Builder;

import java.sql.Date;
import java.util.List;
@Builder
public record UserResponseDto(
        String uuid,
        String userName,
        String email,
        Date createdDate

) { }