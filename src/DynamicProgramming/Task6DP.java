package DynamicProgramming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Task6DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input6dp.txt"));
        int n = Integer.parseInt(br.readLine());
        String[] strArray = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(strArray[i]);
        }
        int [] F = new int[n];
        int lisLength = 0;
        for (int i = 0; i < n; i++) {
            int index = Arrays.binarySearch(F, 0, lisLength, array[i]);
            if(index < 0)
                index = - (index + 1);
            F[index] = array[i];
            if(index == lisLength)
                lisLength++;
        }
        FileWriter fw = new FileWriter("outputs//output6dp.txt");
        fw.write(String.valueOf(lisLength));
        fw.close();
    }
}