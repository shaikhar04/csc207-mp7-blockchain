package blockchain;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Wrapper class for a byte array to store a hash value.
 *
 * @author Arsal Shaikh
 * @author Vincent Yao
 */

public class Hash {
    
    // -- Fields --
    byte[] hashValue;


    // -- Constructor --
    public Hash(byte[] data) throws NoSuchAlgorithmException{
        this.hashValue = data;
    } // Hash(byte[])

    
    // -- Methods --
    public byte[] getData() {
        return this.hashValue;
    } // getData()
    
    public boolean isValid() {
        byte[] b = this.hashValue; 
        return b[0] == 0 && b[1] == 0 && b[2] == 0;
    } // isValid()
    
    @Override
    public String toString()  {
        String output = "";
        for (byte b : hashValue) {
            output += String.format("%02x", Byte.toUnsignedInt(b));
        }
        return output;
    } // toString()
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Hash) {
            Hash o = (Hash) other;
            return Arrays.equals(this.getData(), o.getData());
        }
        return false;
    } // equals(Object)
} // class Hash
