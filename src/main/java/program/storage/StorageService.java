package program.storage;

import org.springframework.core.io.Resource;

public interface StorageService {
    void init();

    Resource loadAsResources(String filename);

    String save(String base64);
}
