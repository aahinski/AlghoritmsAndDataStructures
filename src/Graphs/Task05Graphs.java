package Graphs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task05Graphs {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input05graphs.txt"));
        int n = Integer.parseInt(sc.nextLine());
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = 0;
        }
        while(sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split(" ");
            array[Integer.parseInt(tmp[1]) - 1] = Integer.parseInt(tmp[0]);
        }
        FileWriter fw = new FileWriter(new File("outputs//output05graphs.txt"));
        for (int x : array)
            fw.write(x + " ");
        fw.close();
    }
}