package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-06-27
public class Main {
    final static int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        // 몇 번째 피보나치 수를 구할 건지
        long n = Long.parseLong(st.nextToken());

        long[][] fiboMatrix = {{1, 1}, {1, 0}};

        fiboMatrix = power(fiboMatrix, n);
        System.out.println(fiboMatrix[0][1] % MOD);
    }

    static long[][] power(long[][] M, long k) {
        if(k == 1) return M;

        long[][] half = power(M, k/2);
        long[][] result = multiply(half, half);

        if(k % 2 == 1) {
            result = multiply(result, M);
        }
        return result;
    }

    static long[][] multiply(long[][] A, long[][] B) {
        long[][] result = new long[2][2];

        result[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        result[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
        result[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        result[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;

        return result;
    }
}
