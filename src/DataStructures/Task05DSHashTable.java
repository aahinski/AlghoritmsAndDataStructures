package DataStructures;

import java.io.*;
import java.util.Scanner;

public class Task05DSHashTable {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input05ds.txt"));
        String[] mcn = br.readLine().split(" ");
        int m = Integer.parseInt(mcn[0]);
        int c = Integer.parseInt(mcn[1]);
        int n = Integer.parseInt(mcn[2]);
        int[] hashTable = new int[m];
        for(int i = 0; i < m; i++)
            hashTable[i] = -1;
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            for (int j = 0; j < m; j++) {
                int h = (x % m + c * j) % m;
                if(hashTable[h] == -1) {
                    hashTable[h] = x;
                    break;
                } else if(hashTable[h] == x)
                    break;
            }
        }
        FileWriter fw = new FileWriter("outputs//output05ds.txt");
        for(int bucket : hashTable)
            fw.write(bucket + " ");
        fw.close();
    }
}