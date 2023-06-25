package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// author : bmincof
// date   : 2023-06-25
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        final int MOD = 1_000_000_000;

        int[] fibo = new int[1_000_001];
        fibo[1] = 1;

        for (int i = 2; i <= 1_000_000; i++) {
            fibo[i] = (fibo[i - 1] + fibo[i - 2]) % MOD;
        }
        int n = Integer.parseInt(br.readLine());

        int answer = fibo[Math.abs(n)];
        int sign = 0;
        if (answer > 0) sign = 1;
        sign *= n < 0 && n % 2 == 0 ? -1 : 1;

        System.out.println(sign);
        System.out.println(answer);
    }
}
