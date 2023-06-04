package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 소의 수
        int N = Integer.parseInt(st.nextToken());
        // 쿼리의 수
        int Q = Integer.parseInt(st.nextToken());

        int[][] cows = new int[4][N+1];

        for(int i = 1; i <= N; i++) {
            int cowNum = Integer.parseInt(br.readLine());

            cows[1][i] = cows[1][i - 1] + (cowNum == 1 ? 1 : 0);
            cows[2][i] = cows[2][i - 1] + (cowNum == 2 ? 1 : 0);
            cows[3][i] = cows[3][i - 1] + (cowNum == 3 ? 1 : 0);
        }

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            // 1번부터 3번 소의 수
            sb.append(cows[1][end] - cows[1][start - 1] + " ")
                    .append(cows[2][end] - cows[2][start - 1] + " ")
                    .append(cows[3][end] - cows[3][start - 1] + " ")
                    .append("\n");
        }

        System.out.println(sb);
    }
}
