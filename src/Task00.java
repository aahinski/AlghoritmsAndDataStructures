import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Task00 {
    public static void main(String[] args) throws IOException {
        Set<Long> set = new TreeSet<>();
        File file = new File("inputs//input00.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
            set.add(sc.nextLong());
        sc.close();
        Long sum = set.stream().mapToLong(Long::longValue).sum();
        FileWriter fw = new FileWriter("outputs//output00.txt");
        fw.write(sum.toString());
        fw.close();
    }
}