package Trees;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Task7 {
    static class Node {
        long key;
        Node right;
        Node left;
        long height;
        long rHeight;
        long lHeight;

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

        public void findHeightsViaPostOrderTraversal(Node node) {
            if(node != null) {
                findHeightsViaPostOrderTraversal(node.left);
                findHeightsViaPostOrderTraversal(node.right);
                if(node.left != null && node.right != null) {
                    node.height = Math.max(node.left.height, node.right.height) + 1;
                    node.lHeight = node.left.height;
                    node.rHeight = node.right.height;
                }
                else if(node.left == null && node.right != null) {
                    node.height = node.right.height + 1;
                    node.lHeight = - 1;
                    node.rHeight = node.right.height;
                }
                else if(node.left != null && node.right == null) {
                    node.height = node.left.height + 1;
                    node.lHeight = node.left.height;
                    node.rHeight =  - 1;
                }
                else {
                    node.height = 0;
                    node.lHeight = - 1;
                    node.rHeight =  - 1;
                }
            }
        }

        public List preOrderTraversal(Node node, List list) {
            if (node != null) {
                list.add(node.key);
                preOrderTraversal(node.left, list);
                preOrderTraversal(node.right, list);
            }
            return list;
        }

        public void insert(long key) {
            root = insertRecursively(root, key);
        }

        public List findNodesWithLevel(Node node, long i, long level, List list) {
            if(node != null) {
                if (i != level) {
                    i--;
                    findNodesWithLevel(node.left, i, level, list);
                    findNodesWithLevel(node.right, i, level, list);
                } else list.add(node);
            }
            return list;
        }
    }

    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();
        BufferedReader br = new BufferedReader(new FileReader("inputs//input7.txt"));
        String line;
        while((line = br.readLine()) != null)
            tree.insert(Long.parseLong(line));
        br.close();
        tree.findHeightsViaPostOrderTraversal(tree.root);
        long treeHeight = tree.root.height;
        long level;
        if(treeHeight % 2 == 0)
            level = treeHeight / 2;
        else level = (treeHeight - 1) / 2;
        long i = treeHeight;
        List<Node> nodesList = new LinkedList<>();
        nodesList = tree.findNodesWithLevel(tree.root, i, level, nodesList);
        nodesList = nodesList.stream()
                .filter(x -> (x.rHeight == x.lHeight))
                .collect(Collectors.toList());
        if(nodesList.size() % 2 == 1) {
            tree.delete(nodesList.get((nodesList.size() - 1) / 2).key);
        }
        List<Long> list = new LinkedList<>();
        list = tree.preOrderTraversal(tree.root, list);
        FileWriter fw = new FileWriter("outputs//output7.txt");
        for(Long height : list)
            fw.write(height.toString() + "\n");
        fw.close();
    }
}