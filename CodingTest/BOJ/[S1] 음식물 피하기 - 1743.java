package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dx = {1, -1 , 0, 0};
        int[] dy = {0, 0, 1, -1};

        int[][] map = new int[n][m];

        while(k-- > 0) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = 1;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        int answer = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    int size = 0;

                    q.offer(new int[] {i, j});
                    visited[i][j] = true;

                    while(!q.isEmpty()) {
                        int[] curr = q.poll();
                        size++;

                        for(int dir = 0; dir < 4; dir++) {
                            int nx = curr[0] + dx[dir];
                            int ny = curr[1] + dy[dir];

                            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                            if(map[nx][ny] == 0 || visited[nx][ny]) continue;

                            visited[nx][ny] = true;
                            q.offer(new int[] {nx, ny});
                        }
                    }

                    answer = Math.max(answer, size);
                }
            }
        }

        System.out.println(answer);
    }
}
