package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    final static long modular = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 항의 개수
        int n = Integer.parseInt(br.readLine());

        long answer = 0;

        while(n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long c = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());

            if(k == 0) continue;

            long term = (c * k) % modular;
            term = (term * powOf2(k - 1)) % modular;

            answer = (answer + term) % modular;
        }

        System.out.println(answer);
    }

    static long powOf2(long k) {
        if(k == 0) return 1;
        else if(k == 1) return 2;

        long value = powOf2(k / 2);

        long result = (value * value) % modular;
        result = (result * powOf2(k % 2)) % modular;

        return result;
    }
}
