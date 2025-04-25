package ee.rara.bruno.bruno.util;
import java.util.Random;

public class RandomGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int STRING_LENGTH = 10;

    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(STRING_LENGTH);

        for (int i = 0; i < STRING_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }

        return sb.toString();
    }

    public static int generateRandomPIN(){
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    public static void main(String[] args) {
        System.out.println("Random String: " + generateRandomString());
    }
}
