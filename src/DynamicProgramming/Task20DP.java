package DynamicProgramming;

import java.io.*;

public class Task20DP {
    static int max(int a, int b) {
        if(a >= b) return a;
        else return b;
    }

    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("outputs//output20dp.txt");
        BufferedReader br = new BufferedReader(new FileReader("inputs//input20dp.txt"));
        String str = br.readLine();
        char[] array = str.toCharArray();
        int answer;
        int n = array.length;
        if(n == 1) {
            answer = 1;
            fw.write(1 + "\n" + str);
        }
        else if (n == 2) {
            if(array[0] == array[1]) {
                answer = 2;
                fw.write(2 + "\n" + str);
            }
            else {
                answer = 1;
                fw.write(1 + "\n" + String.valueOf(array[0]));
            }
        }
        else {
            int[][] F = new int[n][n];
            for(int i = 0; i < n; i++)
                F[i][i] = 1;
            for(int i = 0; i < (n - 1); i++) {
                if(array[i] == array[i + 1])
                    F[i][i + 1] = 2;
                else F[i][i + 1] = 1;
            }
            for(int j = 2; j < n; j++) {
                for(int i = 0; i < (n - j); i++) {
                    if(array[i] == array[i + j])
                        F[i][i + j] = F[i + 1][i + j - 1] + 2;
                    else F[i][i + j] = max(F[i + 1][i + j], F[i][i + j - 1]);
                }
            }
            answer = F[0][n - 1];
            StringBuilder sb = new StringBuilder("");
            int i = 0;
            int j = str.length() - 1;
            while (j >= i) {
                int k = F[i][j];
                while ((i < n - 1) && (F[i + 1][j] == k))
                    i++;
                while (j > 0 && F[i][j - 1] == k)
                    j--;
                i++;
                sb.append(array[j]);
                j--;
            }
            fw.write(answer + "\n" + sb + sb.reverse().substring(F[0][n - 1] % 2));
        }
        fw.close();
    }
}