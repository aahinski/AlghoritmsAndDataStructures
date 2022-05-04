package Graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task04Graphs {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input04graphs.txt"));
        String[] tmp = sc.nextLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }
        while(sc.hasNextLine()) {
            tmp = sc.nextLine().split(" ");
            int a = Integer.parseInt(tmp[0]) - 1;
            int b = Integer.parseInt(tmp[1]) - 1;
            matrix[a][b] = 1;
            matrix[b][a] = 1;
        }
        FileWriter fw = new FileWriter("outputs//output04graphs.txt");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                fw.write(matrix[i][j] + " ");
            }
            fw.write("\n");
        }
        fw.close();
    }
}
