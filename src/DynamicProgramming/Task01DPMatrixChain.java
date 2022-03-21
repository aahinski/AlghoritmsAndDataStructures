package DynamicProgramming;

import java.io.*;

public class Task01DPMatrixChain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("inputs//input01dp.txt"));
        int n = Integer.parseInt(br.readLine());
        int[] dimensions = new int[n + 1];
        String[] strArray;
        String line;
        strArray = br.readLine().split(" ");
        dimensions[0] = Integer.parseInt(strArray[0]);
        dimensions[1] = Integer.parseInt(strArray[1]);
        int i = 2;
        while((line = br.readLine()) != null) {
            strArray = line.split(" ");
            dimensions[i] = Integer.parseInt(strArray[1]);
            i++;
        }
        int[][] minimums = new int[n][n];
        for(i = 0; i < n; i++)
            minimums[i][i] = 0;
        int min;
        int j;
        int tmp;
        for(int d = 1; d < n; d++) {
            for(i = 0; i < n - d; i++) {
                min = Integer.MAX_VALUE;
                for(j = i; j < i + d; j++) {
                    tmp = minimums[i][j] + minimums[j + 1][i + d] +
                            (dimensions[i] * dimensions[j + 1] * dimensions[i + d + 1]);
                    if(tmp < min)
                        min = tmp;
                }
                minimums[i][i + d] = min;
            }
        }
        FileWriter fw = new FileWriter("outputs//output01dp.txt");
        fw.write(String.valueOf(minimums[0][n - 1]));
        fw.close();
    }
}
