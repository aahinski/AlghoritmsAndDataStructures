package Trees;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Task7 implements Runnable {
    static class Arc {
        long key;
        Arc right;
        Arc left;
        long height;
        long heightR;
        long heightL;

        Arc(long key) {
            this.key = key;
            this.right = null;
            this.left = null;
        }
    }

    static class Tree {
        Arc root;

        private Arc insertRecursively(Arc arc, long key) {
            if (arc == null)
                return new Arc(key);

            if (key < arc.key) {
                arc.left = insertRecursively(arc.left, key);
            } else if (key > arc.key) {
                arc.right = insertRecursively(arc.right, key);
            } else {
                return arc;
            }

            return arc;
        }

        private Arc delete1(Arc arc, long key) {
            if (arc == null)
                return null;

            if (key < arc.key) {
                arc.left = delete1(arc.left, key);
                return arc;
            } else if (key > arc.key) {
                arc.right = delete1(arc.right, key);
                return arc;
            }

            if (arc.left == null)
                return arc.right;
            if (arc.right == null)
                return arc.left;

            else {
                long minKey = findMin(arc.right).key;
                arc.key = minKey;
                arc.right = delete1(arc.right, minKey);
                return arc;
            }
        }

        private Arc findMin(Arc arc) {
            if (arc.left != null)
                return findMin(arc.left);
            else return arc;
        }

        public void delete(long key) {
            root = delete1(root, key);
        }

        public void findHeights(Arc arc) {
            if (arc != null) {
                findHeights(arc.left);
                findHeights(arc.right);
                if (arc.left != null && arc.right != null) {
                    arc.height = Math.max(arc.left.height, arc.right.height) + 1;
                    arc.heightL = arc.left.height;
                    arc.heightR = arc.right.height;
                } else if (arc.left == null && arc.right != null) {
                    arc.height = arc.right.height + 1;
                    arc.heightL = -1;
                    arc.heightR = arc.right.height;
                } else if (arc.left != null && arc.right == null) {
                    arc.height = arc.left.height + 1;
                    arc.heightL = arc.left.height;
                    arc.heightR = -1;
                } else {
                    arc.height = 0;
                    arc.heightL = -1;
                    arc.heightR = -1;
                }
            }
        }

        public List preOrderTraversal(Arc arc, List list) {
            if (arc != null) {
                list.add(arc.key);
                preOrderTraversal(arc.left, list);
                preOrderTraversal(arc.right, list);
            }
            return list;
        }

        public List findArcsWithEqualSubtreeViaPreOrderTraversal(Arc arc, List list) {
            if (arc != null) {
                if (arc.heightL == arc.heightR) {
                    list.add(arc.key);
                }
                findArcsWithEqualSubtreeViaPreOrderTraversal(arc.left, list);
                findArcsWithEqualSubtreeViaPreOrderTraversal(arc.right, list);
            }
            return list;
        }

        public void insert(long key) {
            root = insertRecursively(root, key);
        }

    }

    public static void main(String[] args) {
        new Thread(null, new Task7(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        Tree tree = new Tree();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("tst.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            tree.insert(Long.parseLong(line));
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        tree.findHeights(tree.root);
        List<Long> list = new LinkedList<>();
        list = tree.findArcsWithEqualSubtreeViaPreOrderTraversal(tree.root, list);

        if (list.size() % 2 == 1) {
            Collections.sort(list);
            tree.delete(list.get((list.size() - 1) / 2));
        }
        List<Long> list1 = new LinkedList<>();
        list = tree.preOrderTraversal(tree.root, list1);
        FileWriter fw = null;
        try {
            fw = new FileWriter("tst.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Long height : list) {
            try {
                fw.write(height.toString() + "\n");
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