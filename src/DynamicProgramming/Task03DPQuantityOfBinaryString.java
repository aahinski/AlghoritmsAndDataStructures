package DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Task03DPQuantityOfBinaryString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        int n = Integer.parseInt(tmp[0]);
        int k = Integer.parseInt(tmp[1]);
        BigInteger b = BigInteger.valueOf(1);
        BigInteger a = BigInteger.valueOf(1);
        int mod = 1000000007;
        for (int i = (n - k + 1); i <= n; ++i)
            b = b.multiply(BigInteger.valueOf(i)).mod(BigInteger.valueOf(mod));
        for (int i = 2; i <= k; ++i)
            a = a.multiply(BigInteger.valueOf(i)).mod(BigInteger.valueOf(mod));

        BigInteger res = BigInteger.valueOf(1);
        int mod_ = mod - 2;
        while(mod_ != 0){
            if ((mod_ % 2) == 1) {
                res = res.multiply(a).mod(BigInteger.valueOf(mod));
                --mod_;
            }
            else {
                a = a.multiply(a).mod(BigInteger.valueOf(mod));
                mod_ /= 2;
            }
        }

        System.out.print(res.multiply(b).mod(BigInteger.valueOf(mod)));
    }
}