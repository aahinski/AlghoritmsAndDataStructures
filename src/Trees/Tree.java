package Trees;

import java.util.List;

public class Tree {
    Node root;

    private Node insertRecursively(Node node, long key) {
        if (node == null)
            return new Node(key);

        if (key < node.key) {
            node.left = insertRecursively(node.left, key);
        } else if (key > node.key) {
            node.right = insertRecursively(node.right, key);
        } else {
            return node;
        }

        return node;
    }

    public void insert(long key) {
        root = insertRecursively(root, key);
    }

    public List preOrderTraversal(Node node, List list) {
        if(node != null) {
            list.add(node.key);
            preOrderTraversal(node.left, list);
            preOrderTraversal(node.right, list);
        }
        return list;
    }
}
