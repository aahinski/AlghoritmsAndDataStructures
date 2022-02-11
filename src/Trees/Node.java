package Trees;

public class Node {
    long key;
    Node right;
    Node left;

    Node(long key) {
        this.key = key;
        this.right = null;
        this.left = null;
    }
}