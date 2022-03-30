package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class TaskDivisibleSubsequenceDP {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        String[] numbers = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(numbers[i]);
        }
        Integer[] F = new Integer[n];
        F[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (A[j] != 0 && A[i] % A[j] == 0 && F[j] + 1 > max) {
                    max = F[j] + 1;
                }
            }
            F[i] = max;
        }
        System.out.println(n - Collections.max(Arrays.asList(F)));
    }
}
