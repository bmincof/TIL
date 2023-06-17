package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-17
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int[][] dig = {
                {},                     // 0
                {1},                    // 1
                {1, 2, 4, 8, 7, 5},     // 2
                {1, 3},                 // 3
                {1, 4, 7},              // 4
                {1, 5, 7, 8, 4, 2},     // 5
                {1, 6},                 // 6
                {1, 7, 4},              // 7
                {1, 8},                 // 8
                {1}                     // 9
        };

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // 작업의 반복 횟수
            int K = Integer.parseInt(st.nextToken());
            // 골드 넘버에 곱할 수
            int M = Integer.parseInt(st.nextToken());
            // 동쪽이 +
            int x = 0;
            // 북쪽이 +
            int y = 0;
            int dir = 0;

            // digitSum을 계산
            M = M % 9 == 0 ? 9 : (M % 9);

            int[] distance = dig[M];
            boolean hasNine = false;

            if(M % 3 == 0) hasNine = true;
            if(hasNine) {
                if(K == 1) {
                    sb.append(0).append(" ").append(distance[0]).append("\n");
                    continue;
                } else if(M != 9 && K == 2) {
                    sb.append(distance[1]).append(" ").append(distance[0]).append("\n");
                    continue;
                }

                int moved = M == 9 ? 1 : 2;
                K = (K - moved) % 4;

                x = moved == 2 ? distance[1] : 0;
                y = distance[0];
                dir = moved;

                for(int i = 0; i < K; i++) {
                    x += 9 * dx[dir % 4];
                    y += 9 * dy[dir % 4];
                    dir++;
                }
            } else {
                int period = distance.length;
                K %= period * 4;

                for(int i = 0; i < K; i++) {
                    x += distance[i % period] * dx[dir % 4];
                    y += distance[i % period] * dy[dir % 4];
                    dir++;
                }
            }

            sb.append(x + " " + y + "\n");
        }

        System.out.println(sb);
    }
}
