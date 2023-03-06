package program.entities;

import jakarta.persistence.*;
import lombok.Data;
import program.dto.category.CategoryItemDTO;

@Data
@Entity
@Table(name = "tbl_categories")
public class CategoryEntity extends CategoryItemDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(length = 4000)
    private String description;

    @Column(nullable = false)
    private String image;
}
