package DynamicProgramming;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ChristmasToysDP implements Runnable {
    static List<Integer> takenToys(int[][] F, int[] d, int i, int j, List<Integer> list) {
        if(F[i][j] == 0)
            return list;
        if(F[i - 1][j] == F[i][j])
            takenToys(F, d, i - 1, j, list);
        else {
            takenToys(F, d, i - 1, j - d[i - 1], list);
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        new Thread(null, new ChristmasToysDP(), "", 256 * 1024 * 1024).start();
    }

    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("inputs//inputChristmas.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] snm = new String[0];
        try {
            snm = br.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
        int s = Integer.parseInt(snm[0]);
        int n = Integer.parseInt(snm[1]);
        int m = Integer.parseInt(snm[2]);
        int[] d = new int[s];
        int[] t = new int[s];
        for (int i = 0; i < s; i++) {
            String[] dt = new String[0];
            try {
                dt = br.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
            d[i] = Integer.parseInt(dt[0]);
            t[i] = Integer.parseInt(dt[1]);
        }
        int[][] F = new int[s + 1][n * m + 1];
        for (int i = 0; i < s + 1; i++) {
            F[0][i] = 0;
            F[i][0] = 0;
        }
        for (int i = 1; i < s + 1; i++) {
            for (int j = 1; j < n * m + 1; j++) {
                if(d[i - 1] <= j)
                    F[i][j] = Math.max(F[i - 1][j], F[i - 1][j - d[i - 1]] + t[i - 1]);
                else
                    F[i][j] = F[i - 1][j];
            }
        }
        for (int i = 0; i <= s; i++) {
            for (int j = 0; j <= n * m; j++) {
                System.out.print(F[i][j] + " ");
            }
            System.out.println();
        }
        List<Integer> list = new ArrayList<>();
        list = takenToys(F, d, s, n * m, list);
        FileWriter fw = null;
        try {
            fw = new FileWriter("outputs//outputChristmas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.write(list.size() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int pack : list) {
            try {
                fw.write(pack + " ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}