package DataStructures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Task03DSisBinaryHeap {
    static boolean isBinaryHeap(int[] array) {
        int size = array.length;
        for (int j = 1; j < size; j++) {
            if(2 * j < size)
                if(array[j] > array[2 * j])
                    return false;
            if(2 * j + 1 < size)
                if(array[j] > array[2 * j + 1])
                    return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(new File("inputs//input03ds.txt"));
        int n = Integer.parseInt(sc.nextLine());
        int[] array = new int[n + 1];
        array[0] = 0;
        int i = 1;
        while(sc.hasNextInt()) {
            array[i] = sc.nextInt();
            i++;
        }
        FileWriter fw = new FileWriter("outputs//output03ds.txt");
        if(isBinaryHeap(array) == true)
            fw.write("Yes");
        else fw.write("No");
        fw.close();
    }
}
