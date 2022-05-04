package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task06Graphs {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input06graphs.txt"));
        String[] tmp = sc.nextLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        List[] listArray = new List[n];
        for (int i = 0; i < n; i++) {
            listArray[i] = new ArrayList<Integer>();
        }
        while(sc.hasNextLine()) {
            tmp = sc.nextLine().split(" ");
            int a = Integer.parseInt(tmp[0]) - 1;
            int b = Integer.parseInt(tmp[1]) - 1;
            listArray[a].add(b + 1);
            listArray[b].add(a + 1);
        }
        FileWriter fw = new FileWriter(new File("outputs//output06graphs.txt"));
        for (int i = 0; i < n; i++) {
            int size = listArray[i].size();
            fw.write(size + " ");
            for (int j = 0; j < size; j++) {
                fw.write(listArray[i].get(j) + " ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}
