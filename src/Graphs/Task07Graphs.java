package Graphs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task07Graphs {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input07graphs.txt"));
        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = new int[n][n];
        int i = 0;
        while(sc.hasNextLine()) {
            String[] tmp = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(tmp[j]);
            }
            i++;
        }
        int[] array = new int[n];
        for (int j = 0; j < n; j++) {
            array[j] = 0;
        }
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if(matrix[j][k] == 1)
                    array[k] = j + 1;
            }
        }
        FileWriter fw = new FileWriter(new File("outputs//output07graphs.txt"));
        for (int x : array)
            fw.write(x + " ");
        fw.close();
    }
}
