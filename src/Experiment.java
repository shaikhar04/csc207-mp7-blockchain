import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Experiment {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String toHash = "Grinnell";
        Hash h = new Hash(toHash.getBytes());
        String firstThreeBytes = h.toString();
        System.out.println(firstThreeBytes);

        Block block = new Block(0, 50, h);
        System.out.println(block.toString());
    }
}
