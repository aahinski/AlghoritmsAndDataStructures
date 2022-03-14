package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Task03DP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int k = Integer.parseInt(tmp[1]);
        long[][] C = new long[n + 1][n + 1];
        C[0][0] = 1;
        for (int i = 1; i < (n + 1); i++) {
            C[i][0] = 1;
            C[i][i] = 1;
        }
        long mod = 1000000007;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % mod;
            }
        }
        System.out.print(C[n][k]);
    }
}