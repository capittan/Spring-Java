package program.entities;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private String photoUrl;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreate;
}

