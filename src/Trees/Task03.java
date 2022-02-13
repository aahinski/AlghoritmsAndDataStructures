package Trees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Task03 implements Runnable {
    static class Node {
        int key;
        Node parent;
        boolean left;

        Node(int key) {
            this.key = key;
            this.parent = null;
            this.left = true;
        }

        Node(Node node) {
            this.key = node.key;
            this.parent = node.parent;
            this.left = node.left;
        }

        Node() {}
    }

    boolean isBinarySearchedTree(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner sc = new Scanner(file);
        Node[] tree = new Node[Integer.parseInt(sc.nextLine())];
        tree[0] = new Node(Integer.parseInt(sc.nextLine()));
        int i = 1;
        while(sc.hasNext()) {
            Node node = new Node();
            String[] fields = sc.nextLine().split(" ");
            node.key = Integer.parseInt(fields[0]);
            node.parent = tree[Integer.parseInt(fields[1]) - 1];
            if(fields[2].equals("L"))
                node.left = true;
            else node.left = false;
            Node tmp = new Node(node);
            while(tmp != tree[0]) {
                boolean left = tmp.left;
                if(left) {
                    if (node.key >= tmp.parent.key)
                        return false;
                } else {
                    if (node.key < tmp.parent.key)
                        return false;
                }
                tmp = tmp.parent;
            }
            tree[i] = node;
            i++;
        }
        return true;
    }

    public static void main(String[] args) { new Thread(null, new Task03(), "", 64 * 1024 * 1024).start() ; }

    public void run() {
        LocalDateTime from = LocalDateTime.now();
        File file = new File("outputs//output03.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if(isBinarySearchedTree("inputs//input03.txt"))
                fw.write("YES");
            else fw.write("NO");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalDateTime to = LocalDateTime.now();
        System.out.println(Duration.between(from, to).toMillis());
    }
}