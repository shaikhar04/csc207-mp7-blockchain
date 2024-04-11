package blockchain;

/**
 * Implements a Node object to wrap the blocks of the BlockChain.
 *
 * @author Arsal Shaikh
 * @author Vincent Yao
 */

class Node {
    // -- Fields --
    Block block;
    Node next;

    // -- Constructors --
    public Node(Block block, Node next) {
        this.block = block;
        this.next = next;
    }
    public Node(Block block) {
        this.block = block;
        this.next = null;
    }
} // class Node
