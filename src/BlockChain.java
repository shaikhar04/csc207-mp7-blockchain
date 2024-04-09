import java.io.PrintWriter;

import org.w3c.dom.Node;

public class BlockChain {

    private Node first;
    private Node last;
    private int size;


    public BlockChain(int initial){
        this.first = new Node(new Block(0, initial, null));
        this.last = this.first;
    }

    // Mines and returns a new block to be added to the chain
    public Block mine(int amount){

    }

    // Returns the size of the blockchain
    public int getSize(){

    }

    // Appends a block to the blockchain
    public void append(Block blk) throws IllegalArgumentException{

    }

    // Removes the last block from the blockchain
    public boolean removeLast(){

    }

    // Returns the hash of the last block in the chain
    public Hash getHash(){

    }

    // Checks the validity of the entire blockchain
    public boolean isValidBlockChain(){

    }

    // Prints the balances of Alexis and Blake
    public void printBalances(PrintWriter pen){

    }

    // Returns a string representation of the blockchain
    public String toString(){
        
    }
}
