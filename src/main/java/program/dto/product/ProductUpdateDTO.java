package program.dto.product;

import jdk.jfr.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductUpdateDTO {
    private String name;

    private String description;

    private String photoUrl;

    private Date dateCreate;

    private Category category;
}
