package Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Task02 implements Runnable {
    static class Node {
        long key;
        Node right;
        Node left;

        Node(long key) {
            this.key = key;
            this.right = null;
            this.left = null;
        }
    }

    static class Tree {
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
            if (node != null) {
                list.add(node.key);
                preOrderTraversal(node.left, list);
                preOrderTraversal(node.right, list);
            }
            return list;
        }

        private Node deleteRecursively(Node node, long key) {
            if (node == null)
                return null;

            if (key < node.key) {
                node.left = deleteRecursively(node.left, key);
                return node;
            } else if (key > node.key) {
                node.right = deleteRecursively(node.right, key);
                return node;
            }

            if (node.left == null)
                return node.right;
            if (node.right == null)
                return node.left;

            else {
                long minKey = findMin(node.right).key;
                node.key = minKey;
                node.right = deleteRecursively(node.right, minKey);
                return node;
            }
        }

        private Node findMin(Node node) {
            if (node.left != null)
                return findMin(node.left);
            else return node;
        }

        public void delete(long key) {
            root = deleteRecursively(root, key);
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Task02(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        Tree tree = new Tree();
        File file = new File("inputs//input02.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long keyToDelete = sc.nextLong();
        while(sc.hasNext())
            tree.insert(sc.nextLong());
        sc.close();
        List<Long> list = new LinkedList<>();
        tree.delete(keyToDelete);
        list = tree.preOrderTraversal(tree.root, list);
        file = new File("outputs//output02.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(Long key : list) {
            try {
                fw.write(key.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
