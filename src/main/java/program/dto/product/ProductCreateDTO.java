package program.dto.product;

import jdk.jfr.Category;
import lombok.Data;

import java.util.Date;

@Data
public class ProductCreateDTO {
    private String name;

    private String description;

    private String photoUrl;

    private Date dateCreate;

    private Category category;
}
