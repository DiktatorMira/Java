package itstep.learning.services;
import java.security.SecureRandom;

public class RandomFileNameService {
    private static final String ALLOWED_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int DEFAULT_ENTROPY_BITS = 64;
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateRandomFileName(int length) {
        StringBuilder fileName = new StringBuilder(length);
        for (int i = 0; i < length; i++) fileName.append(ALLOWED_CHARS.charAt(RANDOM.nextInt(ALLOWED_CHARS.length())));
        return fileName.toString();
    }
    public String generateRandomFileName() {
        int length = (int) Math.ceil(DEFAULT_ENTROPY_BITS / Math.log(ALLOWED_CHARS.length()) / Math.log(2));
        return generateRandomFileName(length);
    }
}