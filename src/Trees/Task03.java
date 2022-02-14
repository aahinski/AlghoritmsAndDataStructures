package Trees;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Task03 implements Runnable {
    static class Node {
        int key;
        Node parent;
        int max;
        int min;

        Node(int key) {
            this.key = key;
            this.parent = null;
            this.min = -2147483648;
            this.max = 2147483647;
        }

        Node() {}
    }

    boolean isBinarySearchedTree(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Node[] tree = new Node[Integer.parseInt(br.readLine())];
        tree[0] = new Node(Integer.parseInt(br.readLine()));
        int i = 1;
        String line;
        while((line = br.readLine()) != null) {
            Node node = new Node();
            String[] fields = line.split(" ");
            node.key = Integer.parseInt(fields[0]);
            node.parent = tree[Integer.parseInt(fields[1]) - 1];
            if(fields[2].equals("L")) {
                node.min = node.parent.min;
                node.max = node.parent.key - 1;
            } else {
                node.min = node.parent.key;
                node.max = node.parent.max;
            }
            if(node.key < node.min || node.key > node.max)
                return false;
            tree[i] = node;
            i++;
        }
        br.close();
        return true;
    }

    public static void main(String[] args) { new Thread(null, new Task03(), "", 64 * 1024 * 1024).start() ; }

    public void run() {
        boolean isBinarySearchTree = false;
        try {
            isBinarySearchTree = isBinarySearchedTree("inputs//input03.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw = null;
        try {
            fw = new FileWriter("outputs//output03.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(isBinarySearchTree) {
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