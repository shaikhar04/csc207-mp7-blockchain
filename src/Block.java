import java.security.NoSuchAlgorithmException;

public class Block {
    
    int num;

    int amount;
    
    long nonce;

    Hash prevHash;

    Hash hash;

    private long mineNonce(Block block) throws NoSuchAlgorithmException {
        
        long nonce = 0;
        Hash blockHash = hash(block, nonce);
        while (!blockHash.isValid()) {
            nonce++;
            blockHash = hash(block, nonce);
        }
        return nonce;
    } 

    private Hash hash(Block block, long nonce) throws NoSuchAlgorithmException {
        String blockString = String.valueOf(this.num) + String.valueOf(this.amount) + 
        String.valueOf(this.prevHash) + String.valueOf(this.nonce);
        
        Hash hash =  new Hash(blockString.getBytes());
        return hash;
    }

    public Block(int num, int amount, Hash prevHash) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = mineNonce(this);
        this.hash = hash(this, this.nonce);
    }
    public Block(int num, int amount, Hash prevHash, long nonce) throws NoSuchAlgorithmException {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        this.hash = hash(this, this.nonce);
    }
    public int getNum(){
        return this.num;
    } 
    public int getAmount(){
        return this.amount;
    }
    public long getNonce(){
        return this.nonce;
    }
    public Hash getPrevHash(){
        return this.prevHash;
    }
    public Hash getHash(){
        return this.hash;
    }
    public String toString(){
        return String.format("Block %d (Amount: %d, Nonce: %s, prevHash: %s, hash: %s)", 
        this.num, this.amount, this.nonce, this.prevHash, this.hash);
    }
}
