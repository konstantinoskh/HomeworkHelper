import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileEncryption {
    private static final String KEY = "mysecretpassword"; // change this to your own secret key

    public static void encryptFile() throws Exception {
        // read the contents of the text file into a byte array
        byte[] fileContents = Files.readAllBytes(Paths.get("users.txt"));

        // create a secret key from the key string
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), "AES");

        // create a cipher and initialize it with the secret key in encrypt mode
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // encrypt the file contents
        byte[] encryptedFileContents = cipher.doFinal(fileContents);

        // write the encrypted contents back to the file
        Files.write(Paths.get("users.txt"), encryptedFileContents);
    }
}

