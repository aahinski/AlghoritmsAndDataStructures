package Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task03 implements Runnable {
    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    Node[] binaryTreeArray(String inputFileName) throws FileNotFoundException {
        File file = new File(inputFileName);
        Scanner sc = new Scanner(file);
        Node[] tree = new Node[Integer.parseInt(sc.nextLine())];
        tree[0] = new Node(Integer.parseInt(sc.nextLine()));
        int i = 1;
        while(sc.hasNext()) {
            String[] fields = sc.nextLine().split(" ");
            Node node = new Node(Integer.parseInt(fields[0]));
            tree[i] = node;
            if(fields[2].equals("L"))
                tree[Integer.parseInt(fields[1]) - 1].left = node;
            else tree[Integer.parseInt(fields[1]) - 1].right = node;
            i++;
        }
        sc.close();
        return tree;
    }

    boolean isBinarySearchedTree(Node root) {
        if(root == null)
            return true;

        if(root.left != null)
            if(root.key <= root.left.key)
                return false;

        if(root.right != null)
            if(root.key > root.right.key)
                return false;

        return isBinarySearchedTree(root.left) && isBinarySearchedTree(root.right);
    }

    public static void main(String[] args) { new Thread(null, new Task03(), "", 64 * 1024 * 1024).start() ; }

    public void run() {
        Node[] tree = new Node[0];
        try {
            tree = binaryTreeArray("inputs//input03.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isBinarySearchedTree = isBinarySearchedTree(tree[0]);
        File file = new File("outputs//output03.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isBinarySearchedTree == true) {
            try {
                fw.write("YES");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                fw.write("NO");
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