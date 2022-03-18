package DynamicProgramming;

import java.io.*;
import java.util.*;

public class Task692DP {
    static int g(int a, int b, int i) {
        if(a >= b)
            return i - 1;
        else return  i - 2;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] strArray = br.readLine().split(" ");
        int[] array = new int[n];
        for (int i = 0; i < n; i++)
            array[i] = Integer.parseInt(strArray[i]);
        if(n == 1) {
            System.out.print(array[0] + "\n" + 1);
        }
        else if(n == 2) {
            System.out.print(-1);
        }
        else {
            int[] F = new int[n];
            F[0] = array[0];
            F[1] = Integer.MIN_VALUE;
            F[2] = F[0] + array[2];
            int[] G = new int[n];
            G[0] = -1;
            G[2] = 1;
            for (int i = 3; i < n; i++) {
                G[i] = g(F[i - 2], F[i - 3], i);
                F[i] = F[G[i] - 1] + array[i];
            }
            System.out.print(F[n - 1] + "\n" + 1 + " ");
            Stack<Integer> stack = new Stack<>();
            int tmp = n;
            while(tmp != 1) {
                stack.push(tmp);
                tmp = G[tmp - 1];
            }
            while(!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }
}