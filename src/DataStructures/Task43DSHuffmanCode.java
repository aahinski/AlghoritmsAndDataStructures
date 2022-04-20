package DataStructures;

import java.io.*;
import java.util.PriorityQueue;

public class Task43DSHuffmanCode {

    static class Tree {
        private Node root;
    }

    static class Node {
        private long key;
        private Node L;
        private Node R;

        public Node(long key) {
            this.key = key;
        }

        public Node(long key, Node L, Node R) {
            this.key = key;
            this.L = L;
            this.R = R;
        }
    }

    static long length;

    public static long findLength(Node node, long count) {
        if(node.L != null) {
            count++;
            findLength(node.L, count);
            count--;
        }
        if(node.R != null){
            count++;
            findLength(node.R, count);
            count--;
        }
        if(node.L == null && node.R == null) {
            length += count * node.key;
        }
        return length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input43ds.txt"));
        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree();
        String nodesStringArray[] = br.readLine().split(" ");
        PriorityQueue<Node> nodes = new PriorityQueue<>((x, y) -> x.key > y.key ? 1 : x.key < y.key ? -1 : 0);
        for (int i = 0; i < n; i++) {
            nodes.add(new Node(Integer.parseInt(nodesStringArray[i])));
        }
        while(nodes.size() > 1) {
            Node x = nodes.poll();
            Node y = nodes.poll();
            Node root = new Node(x.key + y.key, y, x);
            nodes.add(root);
            tree.root = root;
        }
        FileWriter fw = new FileWriter(new File("outputs//output43ds.txt"));
        fw.write(String.valueOf(findLength(tree.root, 0L)));
        fw.close();
    }
}