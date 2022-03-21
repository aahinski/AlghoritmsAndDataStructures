package DynamicProgramming;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Task13DPTestTubes {
    static class Case {
        int k;
        int l;
        int m;
        int[] d;

        public Case(int k, int l, int m, int[] d) {
            this.k = k;
            this.l = l;
            this.m = m;
            this.d = d;
        }
    }

    static int[][] table = new int[101][101];
    static int x;
    static int[] d;
    static int qCount;
    static int i;

    static List<Case> lowerList(List<Case> list) {
        List<Case> lower = new LinkedList<>();
        for(Case c : list) {
            for(i = 0; i < d.length; i++) {
                if(c.k - d[i] >= 0) {
                    if((c.l + c.k - d[i]) >= 0) { // 1->2 mark of 1st
                        if(table[d[i]][c.l + c.k - d[i]] == 0) {
                            table[d[i]][c.l + c.k - d[i]] = 1;
                            table[c.l + c.k - d[i]][d[i]] = 1;
                            if((c.l + c.k - d[i]) != d[i])
                                qCount = qCount + 2;
                            else qCount++;
                            lower.add(new Case(d[i], (c.l + c.k - d[i]), c.m, d));
                        }
                    }
                    if((c.m + c.k - d[i]) >= 0) { // 1->3
                        if(table[d[i]][c.l] == 0) {
                            table[d[i]][c.l] = 1;
                            table[c.l][d[i]] = 1;
                            if(c.l != d[i])
                                qCount = qCount + 2;
                            else qCount++;
                            lower.add(new Case(d[i], c.l, (c.m + c.k - d[i]), d));
                        }
                    }
                }
                if(c.l >= d[i]) {
                    if((c.l + c.k - d[i]) >= 0) {
                        if(table[c.l + c.k - d[i]][d[i]] == 0) { // 2->1 marks of 2nd
                            table[d[i]][c.l + c.k - d[i]] = 1;
                            table[c.l + c.k - d[i]][d[i]] = 1;
                            if((c.l + c.k - d[i]) != d[i])
                                qCount = qCount + 2;
                            else qCount++;
                            lower.add(new Case((c.l + c.k - d[i]), d[i], c.m, d));
                        }
                    }
                    if(c.m + c.l - d[i] >= 0) { // 2->3
                        if(table[c.k][d[i]] == 0) {
                            table[d[i]][c.k] = 1;
                            table[c.k][d[i]] = 1;
                            if(c.k != d[i])
                                qCount = qCount + 2;
                            else qCount++;
                            lower.add(new Case(c.k, d[i], (c.m + c.l - d[i]), d));
                        }
                    }
                }
                if((d[i] > c.l) && ((c.k - d[i] + c.l) >= 0)) { //1->2 marks of 2nd
                    if(table[c.k - d[i] + c.l][d[i]] == 0) {
                        table[c.k - d[i] + c.l][d[i]] = 1;
                        table[d[i]][c.k - d[i] + c.l] = 1;
                        if((c.k - d[i] + c.l) != d[i])
                            qCount = qCount + 2;
                        else qCount++;
                        lower.add(new Case((c.k - d[i] + c.l), d[i], c.m, d));
                    }
                }
                if((d[i] > c.k) && ((c.l + c.k - d[i]) >= 0)) { //2->1 marks of 1st
                    if(table[d[i]][c.l + c.k - d[i]] == 0) {
                        table[d[i]][c.l + c.k - d[i]] = 1;
                        table[c.l + c.k - d[i]][d[i]] = 1;
                        if(d[i] != (c.l + c.k + d[i]))
                            qCount = qCount + 2;
                        qCount++;
                        lower.add(new Case(d[i], (c.l + c.k + d[i]), c.m, d));
                    }
                }
                if((d[i] > c.l) && ((c.m + c.l - d[i]) >= 0)) { //3->2
                    if(table[c.k][d[i]] == 0) {
                        table[c.k][d[i]] = 1;
                        table[d[i]][c.k] = 1;
                        if(c.k != d[i])
                            qCount = qCount + 2;
                        else qCount++;
                        lower.add(new Case(c.k, d[i], (c.m + c.l - d[i]), d));
                    }
                }
                if((d[i] > c.k) && ((c.m + c.k - d[i]) >= 0)) { //3->1
                    if(table[d[i]][c.l] == 0) {
                        table[d[i]][c.l] = 1;
                        table[c.l][d[i]] = 1;
                        if(c.l != d[i])
                            qCount = qCount + 2;
                        else qCount++;
                        lower.add(new Case(d[i], c.l, (c.m + c.k - d[i]), d));
                    }
                }
            }
            if(table[c.k][c.m + c.l] == 0) {
                table[c.k][c.m + c.l] = 1;
                table[c.m + c.l][c.k] = 1;
                if(c.k != c.m + c.l)
                    qCount = qCount + 2;
                else qCount++;
                lower.add(new Case(c.k, c.m + c.l, 0, d));
            }
            if(table[c.k + c.m][c.l] == 0) {
                table[c.l][c.m + c.k] = 1;
                table[c.m + c.k][c.l] = 1;
                if(c.l != c.m + c.k)
                    qCount = qCount + 2;
                else qCount++;
                lower.add(new Case(c.k + c.m, c.l, 0, d));
            }
        }
        return lower;
    }

    static int solution() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("intputs//input20dp.txt"));
        x = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        int l = Integer.parseInt(br.readLine());
        int m = 100 - k - l;
        if((100 - k - l) == x)
            return 0;
        String[] tmp = br.readLine().split(" ");
        d = new int[tmp.length];
        for(i = 0; i < d.length; i++)
            d[i] = Integer.parseInt(tmp[i]);
        for(int[] a : table)
            for(int b : a)
                a[b] = 0;
        List<Case> upper = new LinkedList<>();
        List<Case> lower;
        qCount = 1;
        int lvl = 1;
        upper.add(new Case(k, l, m, d));
        lower = new LinkedList<>(lowerList(upper));
        if(lower.size() == 0)
            return 10201;
        for(Case c : lower) {
            System.out.println(c.k + " " + c.l + " " + c.m);
            if((100 - c.k - c.l) == x)
                return lvl;
        }
        System.out.println();
        while(qCount != 10201) {
            upper = new LinkedList<>(lower);
            lower = new LinkedList<>(lowerList(upper));
            if(lower.size() == 0)
                return 10201;
            lvl++;
            for(Case c : lower) {
                if((100 - c.k - c.l) == x)
                    return lvl;
            }
        }
        return lvl;
    }

    public static void main(String[] args) throws IOException {
        int answer = solution();
        FileWriter fw = new FileWriter("outputs//output20dp.txt");
        if(answer != 10201)
            fw.write(String.valueOf(answer));
        else fw.write("No solution");
        fw.close();
    }
}