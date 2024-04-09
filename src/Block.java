import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Implements the Block object within a blockchain.
 *
 * @author Arsal Shaikh
 * @author Vincent Yao
 */

public class Block {
    
    int num;
    int amount;
    long nonce;
    Hash prevHash;
    Hash hash;


    // +--------------+------------------------------------------------
    // | Constructors |
    // +--------------+

    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = mineNonce(this);
        this.hash = new Hash(hash(this, this.nonce));
    }
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.hash = new Hash(hash(this, this.nonce));
    }
    

    // +---------+-----------------------------------------------------
    // | Methods |
    // +---------+
     
    public int getNum(){
        return this.num;
    } // getNum()
    
    public int getAmount(){
        return this.amount;
    } // getAmount()
    
    public long getNonce(){
        return this.nonce;
    } // getNonce()
    
    public Hash getPrevHash(){
        return this.prevHash;
    } // getPrevHash()
    
    public Hash getHash(){
        return this.hash;
    } // getHash()

    public String toString(){
        return String.format("Block %d (Amount: %d, Nonce: %s, prevHash: %s, hash: %s)", 
        this.num, this.amount, this.nonce, this.prevHash, this.hash);
    } // toString()


    // +-----------------+---------------------------------------------
    // | Private Methods |
    // +-----------------+
    
    private long mineNonce(Block block) throws NoSuchAlgorithmException {    
        long nonce = 0;
        Hash blockHash = new Hash(hash(block, nonce));
        while (!blockHash.isValid()) {
            nonce++;
            blockHash = new Hash(hash(block, nonce));
        }
        return nonce;
    } // mineNonce(Block)

    private byte[] hash(Block block, long nonce) throws NoSuchAlgorithmException {
        byte[] numBytes = ByteBuffer.allocate(Integer.BYTES).putInt(block.num).array();
        byte[] amountBytes = ByteBuffer.allocate(Integer.BYTES).putInt(block.amount).array();
        byte[] longBytes = ByteBuffer.allocate(Long.BYTES).putLong(block.nonce).array();
        
        MessageDigest md = MessageDigest.getInstance("sha-256");
        md.update(numBytes);
        md.update(amountBytes);
        md.update(longBytes);

        return md.digest();
    } // hash(Block, long)
}
