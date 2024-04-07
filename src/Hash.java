import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Hash {
    
    byte[] hashValue;

    public Hash(byte[] data) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(data);
        this.hashValue = md.digest();
    }
    public byte[] getData() {
        return this.hashValue;
    }
    public boolean isValid() {
        for (int i = 0; i < 3; i++) {
            if (this.hashValue[i] != (byte) 0) {
                return false;
            }
        }
        return true;
    }
    public String toString()  {
        String output = "";
        for (byte b : hashValue) {
            output += String.format("%02x", Byte.toUnsignedInt(b));
        }
        return output;
    }
    public boolean equals(Object other) {
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            return Arrays.equals(this.getData(), o.getData());
        }
        return false;
    } // equals(Object)
}
