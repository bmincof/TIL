package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        // 쿼리의 수
        int Q = Integer.parseInt(st.nextToken());

        // 픽셀 밝기의 2차원 누적합
        int[][] pixel = new int[R + 1][C + 1];

        for(int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= C; j++) {
                pixel[i][j] = Integer.parseInt(st.nextToken()) + pixel[i - 1][j] + pixel[i][j - 1] - pixel[i - 1][j - 1];
            }
        }

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int avg = pixel[r2][c2] + pixel[r1 - 1][c1 - 1] - pixel[r2][c1 - 1] - pixel[r1 - 1][c2];
            avg /= (r2 - r1 + 1) * (c2 - c1 + 1);

            sb.append(avg).append("\n");
        }

        System.out.println(sb);
    }
}
