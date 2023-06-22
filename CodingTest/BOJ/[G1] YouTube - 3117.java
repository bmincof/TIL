package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-23
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        final int MAX_STEP = 30;

        st = new StringTokenizer(br.readLine());

        // 학생의 수
        int N = Integer.parseInt(st.nextToken());
        // 동영상의 개수
        int K = Integer.parseInt(st.nextToken());
        // 남은 수업 시간
        // 영상의 이동은 M-1번이므로 1을 빼서 저장
        int M = Integer.parseInt(st.nextToken()) - 1;

        // [i]번 학생이 가장 처음 보고 있는 동영상의 번호
        int[] video = new int[N];
        // [i][j] : [j]번 동영상을 봤을 때 2^[i]번 뒤에 시청할 동영상의 번호
        int[][] nextVideo = new int[MAX_STEP][K+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            video[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= K; i++) {
            nextVideo[0][i] = Integer.parseInt(st.nextToken());
        }

        // 희소테이블 초기화
        for(int i = 1; i < MAX_STEP; i++) {
            for(int j = 1; j <= K; j++) {
                nextVideo[i][j] = nextVideo[i-1][nextVideo[i-1][j]];
            }
        }

        for(int i = MAX_STEP; i >= 0; i--) {
            if((M & (1 << i)) == 0) continue;
            for(int j = 0; j < N; j++) {
                video[j] = nextVideo[i][video[j]];
            }
        }

        for(int latest : video) {
            sb.append(latest).append(" ");
        }
        System.out.println(sb);
    }
}
