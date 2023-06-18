package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-06-19
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];

        for(int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int minRepaint = Integer.MAX_VALUE;

        // 시작점을 for문으로 결정
        for(int i = 0; i <= N - 8; i++) {
            for(int j = 0; j <= M - 8; j++) {
                int blackRepaint = 0;
                int whiteRepaint = 0;

                int factor = (i + j) % 2;

                for(int r = 0; r < 8; r++) {
                    for(int c = 0; c < 8; c++) {
                        // 첫 칸이 흰색일 경우
                        if((r + c) % 2 == factor && board[i + r][j + c] != 'W') whiteRepaint++;
                        else if((r + c) % 2 == (factor + 1) % 2  && board[i + r][j + c] != 'B') whiteRepaint++;

                        // 첫 칸이 검정색일 경우
                        if((r + c) % 2 == factor && board[i + r][j + c] != 'B') blackRepaint++;
                        else if((r + c) % 2 == (factor + 1) % 2 && board[i + r][j + c] != 'W') blackRepaint++;
                    }
                }
                minRepaint = Math.min(minRepaint, whiteRepaint);
                minRepaint = Math.min(minRepaint, blackRepaint);
            }
        }

        System.out.println(minRepaint);
    }
}
