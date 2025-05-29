package model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Product {
    private Integer id;
    private String uuid;
    private String pName;
    private LocalDate expiredDate;
}
