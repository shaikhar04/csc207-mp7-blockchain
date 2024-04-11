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
            System.out.println("Mining..." + nonce);
            nonce++;
            blockHash = new Hash(this.computeHash(nonce));
          //  
        }
        System.out.println("nonce:" + nonce);
        return nonce;
    } // mineNonce(Block)

    // public static byte[] hash(Block block, long nonce) throws NoSuchAlgorithmException {
    //     byte[] numBytes = ByteBuffer.allocate(Integer.BYTES).putInt(block.num).array();
    //     byte[] amountBytes = ByteBuffer.allocate(Integer.BYTES).putInt(block.amount).array();
    //     byte[] nonceBytes = ByteBuffer.allocate(Long.BYTES).putLong(block.nonce).array();
        
    //     MessageDigest md = MessageDigest.getInstance("SHA-256");
    //     md.update(numBytes);
    //     md.update(amountBytes);
    //     if (block.prevHash != null) {
    //         md.update(block.prevHash.getData());
    //     }
    //     md.update(nonceBytes);

    //     return md.digest();

    public byte[] computeHash(long nonce) throws NoSuchAlgorithmException {
        int num = this.num;
        int amount = this.amount;
        Hash prevHash = this.getPrevHash();
        
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(ByteBuffer.allocate(Integer.BYTES).putInt(num).array());
        md.update(ByteBuffer.allocate(Integer.BYTES).putInt(amount).array());
        if (prevHash != null) {
            md.update(prevHash.getData());
        }
        md.update(ByteBuffer.allocate(Long.BYTES).putLong(nonce).array());
        return md.digest();
        }
        
        // private long mineNonce() throws NoSuchAlgorithmException {
        //     long nonce = 0;
        //     do{
        //     nonce++;
        //     hash = new Hash(this.computeHash(nonce));
        //     System.out.println(nonce);
        //     }while(!hash.isValid());

        //     System.out.println("VALID HASH FOUND");
        //     return nonce;
        // }
}
