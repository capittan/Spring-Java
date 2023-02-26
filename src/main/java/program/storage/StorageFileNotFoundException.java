package program.storage;

public class StorageFileNotFoundException extends StorageExceptions {
    public StorageFileNotFoundException(String massage) {
        super(massage);
    }

    public StorageFileNotFoundException(String massage, Throwable causes) {
        super(massage, causes);
    }
}
