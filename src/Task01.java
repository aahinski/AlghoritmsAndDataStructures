import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task01 {
    public static void main(String[] args) throws IOException {
        List<Integer> list = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        File file = new File("inputs//input01.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext())
            list.add(sc.nextInt());
        sc.close();
        list.stream().filter(x -> x <= list.get(0)).forEach(x -> left.add(x));
        list.stream().filter(x -> x > list.get(0)).forEach(x -> right.add(x));
        FileWriter fw = new FileWriter("outputs//output01.txt");
        Integer father = left.get(0);
        left.remove(0);
        fw.write(father.toString() + "\n");
        boolean exists = false;
        while(left.size() != 0) {
            for(Integer key : left) {
                if(key < father) {
                    exists = true;
                    father = key;
                    left.remove(key);
                    fw.write(father.toString() + "\n");
                    break;
                }
            }
            if(exists == false) {
                father = left.get(0);
                left.remove(0);
                fw.write(father.toString() + "\n");
            }
            exists = false;
        }
        father = right.get(0);
        right.remove(0);
        fw.write(father.toString() + "\n");
        exists = false;
        while(right.size() != 0) {
            for(Integer key : left) {
                if(key > father) {
                    exists = true;
                    father = key;
                    right.remove(key);
                    fw.write(father.toString() + "\n");
                    break;
                }
            }
            if(exists == false) {
                father = right.get(0);
                right.remove(0);
                fw.write(father.toString() + "\n");
            }
            exists = false;
        }
        fw.close();
    }
}