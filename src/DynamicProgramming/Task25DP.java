package DynamicProgramming;

import java.io.*;

public class Task25DP {
    public static int min(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input25dp.txt"));
        int delta;
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        int z = Integer.parseInt(br.readLine());
        char[] B = br.readLine().toCharArray();
        char[] A = br.readLine().toCharArray();
        int[][] F = new int[B.length + 1][A.length + 1];
        int m = Math.min(z, x + y);
        for (int i = 0; i < A.length + 1; i++)
            F[0][i] = i * y;
        for (int i = 1; i < B.length + 1; i++)
            F[i][0] = i * x;
        for (int i = 1; i < B.length + 1; i++) {
            for (int j = 1; j < A.length + 1; j++) {
                if(B[i - 1] == A[j - 1]) delta = 0;
                else delta = 1;
                F[i][j] = min(F[i - 1][j] + x, F[i][j - 1] + y, F[i - 1][j - 1] + delta * m);
            }
        }
        FileWriter fw = new FileWriter("outputs//output25dp.txt");
        fw.write(String.valueOf(F[B.length][A.length]));
        fw.close();
    }
}