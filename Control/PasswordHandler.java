package Control;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Zhengxiao Wu
 * @date 2023/05/25
 *       the password handler, used to hash and check password
 */
public class PasswordHandler {

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static boolean checkPassword(String password, String hash) {
        return hash.equals(hashPassword(password));
    }
}
