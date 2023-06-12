package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// author : bmincof
// date   : 2023-06-12
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        char[][] map = new char[R][C];

        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 시작점에서 가장 먼 거리
        int maxDist = 0;

        // bfs 시작
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                // 바다이면 다른 곳 찾아보기
                if(map[i][j] == 'W') continue;

                // i, j에서 bfs 시작하기
                Queue<int[]> q = new LinkedList<>();

                int[][] dist = new int[R][C];
                dist[i][j] = 1;
                q.offer(new int[] {i, j});

                while(!q.isEmpty()) {
                    int[] curr = q.poll();

                    int x = curr[0];
                    int y = curr[1];

                    for(int dir = 0; dir < 4; dir++) {
                        int nx = x + dx[dir];
                        int ny = y + dy[dir];

                        if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                        if(map[nx][ny] == 'W' || dist[nx][ny] > 0) continue;

                        dist[nx][ny] = dist[x][y] + 1;
                        q.offer(new int[] {nx, ny});

                        if(dist[nx][ny] > maxDist) maxDist = dist[nx][ny];
                    }
                }
            }
        }

        System.out.println(maxDist - 1);
    }
}
