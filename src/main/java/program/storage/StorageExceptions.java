package program.storage;

public class StorageExceptions extends RuntimeException {
    public StorageExceptions(String massage) {
        super(massage);
    }

    public StorageExceptions(String massage, Throwable causes) {
        super(massage, causes);
    }
}
