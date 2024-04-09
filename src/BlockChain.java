
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

public class BlockChain {

    private Node first;
    private Node last;
    private int size;


    public BlockChain(int initial) throws NoSuchAlgorithmException {
        this.first = new Node(new Block(0, initial, null));
        this.last = this.first;
    }

    // Mines and returns a new block to be added to the chain
    public Block mine(int amount){

    }

    // Returns the size of the blockchain
    public int getSize(){
        return this.size;
    }

    // Appends a block to the blockchain
    public void append(Block blk) throws IllegalArgumentException {
        // Validate block
        if (!blk.hash.isValid()) {
            throw new IllegalArgumentException();
        }
        // Validate prev hash
        if (this.last.block.hash != blk.prevHash) {
            throw new IllegalArgumentException();
        }
        // Validate hash of new block
        try {
            Hash calculatedHash = new Hash(Block.hash(blk, blk.nonce));
            if (!blk.getHash().equals(calculatedHash)) {
                throw new IllegalArgumentException();
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException();
        } // try catch
        
        Node n = new Node(blk);
        this.last.next = n;
        this.last = n;
        this.size++;

        // Verify appended blockchain is valid
        if (!this.isValidBlockChain()) {
            this.removeLast();
            throw new IllegalArgumentException();
        }
    }

    // Removes the last block from the blockchain
    public boolean removeLast() {
        // Case: contains only only one node
        if (this.size == 1) {
            return false;
        }

        // traverse to find second to last node
        Node walker = this.first;
        while (walker.next.next != null) {
            walker = walker.next;
        }

        walker.next = null;
        this.last = walker;
        this.size--;
        return true;
    }

    // Returns the hash of the last block in the chain
    public Hash getHash(){
        return this.last.block.getHash();
    }

    // Checks the validity of the entire blockchain
    public boolean isValidBlockChain() {

    } // isValidBlockChain()

    // Prints the balances of Alexis and Blake
    public void printBalances(PrintWriter pen) {
        int alexBalance = this.first.block.getAmount();
        int blakeBalance = 0;
        
        // Traverse the chain
        Node walker = this.first;
        while (!walker.equals(this.last)) {
            walker = walker.next;
            alexBalance += walker.block.getAmount();
            blakeBalance -= walker.block.getAmount();
        }

        pen.printf("Alex: %i, Blake: %i\n", alexBalance, blakeBalance);
        pen.flush();
    } // printBalances(PrintWriter)

    // Returns a string representation of the blockchain
    public String toString() {
        
    } // toString()
}
