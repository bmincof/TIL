package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-17
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int[] dx = {1, -1, 0, 0};
        final int[] dy = {0, 0, 1, -1};

        final int DAY = 0;
        final int NIGHT = 1;
        final char EMPTY = '0';
        final char WALL = '1';

        // 가로, 세로
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 벽을 부술 수 있는 횟수
        int K = Integer.parseInt(st.nextToken());

        // 각 칸이 벽인지
        char[][] map = new char[N][M];
        // [낮/밤][벽 부순 횟수][행][열] 에 방문한 시간
        int[][][][] visit = new int[2][K+1][N][M];

        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Queue<int[]> queue = new LinkedList<>();
        visit[DAY][0][0][0] = 1;
        // {낮/밤, 벽 부순 횟수, 행, 열}
        queue.offer(new int[] {DAY, 0, 0, 0});

        int answer = -1;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            int time = curr[0];
            int broke = curr[1];
            int x = curr[2];
            int y = curr[3];

            int currDist = visit[time][broke][x][y];

            if(x == N-1 && y == M-1) {
                answer = currDist;
                break;
            }

            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                // nx, ny가 벽이면
                if(map[nx][ny] == WALL) {
                    // 밤이면서 낮일 때 현재 위치에 온 적 없으면 한 번 기다리기
                    if(time == NIGHT && visit[DAY][broke][x][y] == 0) {
                        queue.offer(new int[] {DAY, broke, x, y});
                        visit[DAY][broke][x][y] = currDist + 1;
                    }
                    // 낮이면서 이전에 부순적 없으면 부수면서 이동하기
                    else if(time == DAY && broke < K && visit[NIGHT][broke+1][nx][ny] == 0) {
                        queue.offer(new int[] {NIGHT, broke+1, nx, ny});
                        visit[NIGHT][broke+1][nx][ny] = currDist + 1;
                    }
                }
                // nx, ny가 그냥 통로이고 방문한 적 없으면
                else if(visit[(time+1)% 2][broke][nx][ny] == 0) {
                    queue.offer(new int[] {(time+1)%2, broke, nx, ny});
                    visit[(time+1)%2][broke][nx][ny] = currDist + 1;
                }
            }
        }

        System.out.println(answer);
    }
}
