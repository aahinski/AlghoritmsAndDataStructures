import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task01 implements Runnable{
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

        public List<Long> preOrderTraversal(Node node, List<Long> list) {
            if(node != null) {
                list.add(node.key);
                preOrderTraversal(node.left, list);
                preOrderTraversal(node.right, list);
            }
            return list;
        }
    }

    public static void main(String[] args) {
        new Thread(null, new Task01(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        Tree tree = new Tree();
        File file = new File("inputs//input01.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(sc.hasNext())
            tree.insert(sc.nextLong());
        sc.close();
        List<Long> list = new LinkedList<>();
        list = tree.preOrderTraversal(tree.root, list);
        file = new File("outputs//output01.txt");
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