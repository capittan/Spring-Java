package program.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import program.dto.category.CategoryCreateDTO;
import program.dto.category.CategoryItemDTO;
import program.entities.CategoryEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-26T17:31:20+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryItemDTO CategoryItemByCategory(CategoryItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryItemDTO categoryItemDTO = new CategoryItemDTO();

        categoryItemDTO.setId( dto.getId() );
        categoryItemDTO.setName( dto.getName() );
        categoryItemDTO.setDescription( dto.getDescription() );
        categoryItemDTO.setImage( dto.getImage() );

        return categoryItemDTO;
    }

    @Override
    public List<CategoryItemDTO> CategoryItemsByCategories(List<CategoryEntity> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryItemDTO> list = new ArrayList<CategoryItemDTO>( categories.size() );
        for ( CategoryEntity categoryEntity : categories ) {
            list.add( categoryEntityToCategoryItemDTO( categoryEntity ) );
        }

        return list;
    }

    @Override
    public CategoryEntity CategoryByCreateCategoryDTO(CategoryCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setName( dto.getName() );
        categoryEntity.setDescription( dto.getDescription() );

        return categoryEntity;
    }

    protected CategoryItemDTO categoryEntityToCategoryItemDTO(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryItemDTO categoryItemDTO = new CategoryItemDTO();

        categoryItemDTO.setId( categoryEntity.getId() );
        categoryItemDTO.setName( categoryEntity.getName() );
        categoryItemDTO.setDescription( categoryEntity.getDescription() );
        categoryItemDTO.setImage( categoryEntity.getImage() );

        return categoryItemDTO;
    }
}
