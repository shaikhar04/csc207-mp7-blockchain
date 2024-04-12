package blockchain;

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
        this.hash = new Hash(this.computeHash(this.nonce));
    }
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.hash = new Hash(this.computeHash(this.nonce));
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
        Hash blockHash = new Hash(block.computeHash(nonce));
        while (!blockHash.isValid()) {
            nonce++;
            blockHash = new Hash(this.computeHash(nonce));
        } // while
        

        return nonce;
    } // mineNonce(Block)


    private byte[] computeHash(long nonce) throws NoSuchAlgorithmException {        
        byte[] numBytes = ByteBuffer.allocate(Integer.BYTES).putInt(num).array();
        byte[] amountBytes = ByteBuffer.allocate(Integer.BYTES).putInt(amount).array();
        byte[] nonceBytes = ByteBuffer.allocate(Long.BYTES).putLong(nonce).array();
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(numBytes);
        md.update(amountBytes);
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        md.update(nonceBytes);

        return md.digest();
    }
}
