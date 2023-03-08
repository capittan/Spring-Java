package program.storage;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;

    public FileSystemStorageService(StorageProperties properties) {
        rootLocation = Paths.get(properties.getLocation()); // path, which we indicated ("uploading")
    }

    @Override
    public void init() {
        try {
            if (!Files.exists(rootLocation))
                Files.createDirectories(rootLocation);
        } catch (IOException exception) {
            throw new StorageExceptions("Error of creating repos ", exception);
        }
    }

    @Override
    public Resource loadAsResources(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable())
                return resource;
            throw new StorageExceptions("Problem reading of file !");
        } catch (MalformedURLException exception) {
            throw new StorageExceptions("File didn't find !" + filename);
        }
    }

    @Override
    public String save(String base64) {
        try {
            if (base64.isEmpty())
                throw new StorageExceptions("Empty file !"); // base64 is empty
            UUID uuid = UUID.randomUUID();
            String randomFileName = uuid.toString() + ".jpg";
            String[] charArray = base64.split(",");
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = new byte[0];
            bytes = decoder.decode(charArray[1]);
            String folder = rootLocation.toString() + "/" + randomFileName;
            new FileOutputStream(folder).write(bytes);
            return randomFileName;
        } catch (IOException exception) {
            throw new StorageExceptions("Problem of saving file ", exception);
        }
    }

    @Override
    public String saveMultipartFile(MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            String extension = "jpg";  // we can get extension later
            String randomFileName = uuid.toString() + "." + extension;
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = file.getBytes(); // getting bytes from file
            int[] imageSize = {32, 150, 300, 600, 1200}; // we indicate the dimensions
            try (var byteStream = new ByteArrayInputStream(bytes)) {
                var image = ImageIO.read(byteStream);
                for (int size : imageSize) {
                    String fileSaveItem = rootLocation.toString() + "/" + size + "_" + randomFileName;
                    BufferedImage newImg = ImageUtils.resizeImage(image, extension == "jpg" ?
                            ImageUtils.IMAGE_JPEG : ImageUtils.IMAGE_PNG, size, size);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ImageIO.write(newImg, extension, byteArrayOutputStream);
                    byte[] newBytes = byteArrayOutputStream.toByteArray();
                    FileOutputStream out = new FileOutputStream(fileSaveItem);
                    out.write(newBytes);
                    out.close();
                }
            } catch (IOException exception) {
                throw new StorageExceptions("Error editing photo");
            }
            return randomFileName;
        } catch (IOException exception) {
            throw new StorageExceptions("Error of work with file");
        }
    }

    @Override
    public void removeFile(String name) {
        int[] imageSize = {32, 150, 300, 600, 1200};
        for (var size : imageSize) {
            Path path = load(size + "_" + name);
            File file = new File(path.toString());
            if (file.delete()) System.out.println("File: " + name + " was deleted !");
            else System.out.println("File: " + name + " didn't find !");
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
}
