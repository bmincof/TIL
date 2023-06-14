package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-14
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 자연수의 최대 범위
        int m = Integer.parseInt(br.readLine());

        // 함숫값의 sparse table
        int[][] f_x = new int[m + 1][21];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= m; i++) {
            f_x[i][0] = Integer.parseInt(st.nextToken());
        }

        // Sparse Table을 채워넣기
        for(int i = 1; i <= 20; i++) {
            for(int num = 1; num <= m; num++) {
                f_x[num][i] = f_x[f_x[num][i-1]][i-1];
            }
        }

        // 쿼리의 수
        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for(int i = 20; i >= 0; i--) {
                if((n & (1 << i)) != 0) {
                    x = f_x[x][i];
                }
            }

            sb.append(x).append("\n");
        }

        System.out.println(sb);
    }
}
