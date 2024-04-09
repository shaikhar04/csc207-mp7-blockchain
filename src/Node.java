
class Node {
    Block block;
    Node next;

    public Node(Block block, Node next) {
        this.block = block;
        this.next = next;
    }
    public Node(Block block) {
        this.block = block;
        this.next = null;
    }
}
