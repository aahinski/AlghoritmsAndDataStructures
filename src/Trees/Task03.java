package Trees;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task03 implements Runnable {
    static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }

        Node() {}
    }

    public boolean isBinarySearchTree(Node node, Integer min, Integer max) {
        if(node == null)
            return true;

        if(min != null)
            if(node.key < min)
                return false;

        if(max != null)
            if(node.key >= max)
                return false;

        return isBinarySearchTree(node.right, node.key, max) && isBinarySearchTree(node.left, min, node.key);
    }

    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input03.txt"));
        Node[] tree = new Node[Integer.parseInt(br.readLine())];
        tree[0] = new Node(Integer.parseInt(br.readLine()));
        int i = 1;
        String line;
        while((line = br.readLine()) != null) {
            String[] fields = line.split(" ");
            Node node = new Node(Integer.parseInt(fields[0]));
            tree[i] = node;
            if(fields[2].equals("L"))
                tree[Integer.parseInt(fields[1]) - 1].left = node;
            else tree[Integer.parseInt(fields[1]) - 1].right = node;
            i++;
        }
        br.close();
        FileWriter fw = new FileWriter("outputs//output03.txt");
        if(isBinarySearchTree(tree[0], null, null))
            fw.write("YES");
        else fw.write("NO");
        fw.close();
    }

    public static void main(String[] args) { new Thread(null, new Task03(), "", 64 * 1024 * 1024).start() ; }

    public void run() {
        try {
            solution();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}