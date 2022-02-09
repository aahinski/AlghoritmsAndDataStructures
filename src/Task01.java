import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task01 {
    public static void main(String[] args) throws IOException {
        LinkedHashSet<Long> set = new LinkedHashSet<>();
        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();
        File file = new File("inputs//input01.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNext())
            set.add(sc.nextLong());
        sc.close();
        Iterator<Long> iterator = set.iterator();
        long root = (Long)iterator.next();
        for(Long key : set) {
            if(key <= root)
                left.add(key);
            else right.add(key);
        }
        FileWriter fw = new FileWriter("outputs//output01.txt");
        Long father = left.get(0);
        left.remove(0);
        fw.write(father.toString() + "\n");
        boolean exists = false;
        while(left.size() != 0) {
            for(Long key : left) {
                if(key < father) {
                    exists = true;
                    father = key;
                    left.remove(key);
                    fw.write(father + "\n");
                    break;
                }
            }
            if(!exists) {
                father = left.get(0);
                left.remove(0);
                fw.write(father.toString() + "\n");
            }
            exists = false;
        }
        if(right.size() != 0) {
            father = right.get(0);
            right.remove(0);
            fw.write(father.toString() + "\n");
            while (right.size() != 0) {
                for (Long key : right) {
                    if (key > father) {
                        exists = true;
                        father = key;
                        right.remove(key);
                        fw.write(father + "\n");
                        break;
                    }
                }
                if (!exists) {
                    father = right.get(0);
                    right.remove(0);
                    fw.write(father.toString() + "\n");
                }
                exists = false;
            }
        }
        fw.close();
    }
} 