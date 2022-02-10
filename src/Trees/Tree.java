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

    private Node deleteRecursively(Node node, long key) {
        if(node == null)
            return null;

        if (key < node.key) {
            node.left = deleteRecursively(node.left, key);
            return node;
        }
        else if (key > node.key) {
            node.right = deleteRecursively(node.right, key);
            return node;
        }

        if(node.left == null)
            return node.right;
        if(node.right == null)
            return node.left;

        else {
            long minKey = findMin(node.right).key;
            node.key = minKey;
            node.right = deleteRecursively(node.right, minKey);
            return node;
        }
    }

    private Node findMin(Node node) {
        if(node.left != null)
            return findMin(node.left);
        else return node;
    }

    public void delete(long key) { root = deleteRecursively(root, key); }
}
