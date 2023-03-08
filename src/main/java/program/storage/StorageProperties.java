package program.storage;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("store")
@Data
public class StorageProperties {
    private String location = "uploading"; // name of folder, in which will be uploaded photos (location: in folder of project)

}
