package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-06-26
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        // 행렬의 크기
        int N = Integer.parseInt(st.nextToken());
        // 제곱할 횟수
        long B = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        matrix = power(matrix, B, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((matrix[i][j] % 1000) + " ");
            }
            System.out.println();
        }
    }

    static int[][] power(int[][] M, long k, int n) {
        if(k == 1) return M;

        int[][] halfPowered = power(M, k/2, n);
        int[][] result = multiply(halfPowered, halfPowered, n);

        if(k % 2 == 1) {
            result = multiply(result, M, n);
        }
        return result;
    }

    static int[][] multiply(int[][] A, int[][] B, int n) {
        int[][] multiplied = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    multiplied[i][j] = (multiplied[i][j] + A[i][k] * B[k][j]) % 1000;
                }
            }
        }

        return multiplied;
    }
}
