package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Task02DPQuantityOfBinaryString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int k = Integer.parseInt(tmp[1]);
        if (k == 0 || n == 0)
            System.out.print(1);
        else if (k == n)
            System.out.print(1);
        else {
            if (k > (n / 2))
                k = n - k;
            BigInteger a = BigInteger.valueOf(1 + n - k);
            BigInteger b = BigInteger.valueOf(1);
            BigInteger result = BigInteger.valueOf(a.intValue());
            while (b.intValue() != k) {
                a = a.add(BigInteger.ONE);
                b = b.add(BigInteger.ONE);
                result = result.multiply(a);
                result = result.divide(b);
            }
            System.out.print((result.mod(BigInteger.valueOf(1000000007))));
        }
    }
}