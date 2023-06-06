package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] dx = {1,-1,0,0,1,-1,-1,1};
        int[] dy = {0,0,1,-1,1,1,-1,-1};

        char[][] map = new char[H][W];

        int[][] fuel = new int[H][W];
        Deque<int[]> dq = new ArrayDeque<>();

        int[] treasure = null;
        for(int i = 0; i < H; i++) {
            char[] row = br.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                map[i][j] = row[j];
                fuel[i][j] = 1_000_000;

                if(map[i][j] == 'K') {
                    dq.offer(new int[] {i, j});
                    map[i][j] = '.';
                    fuel[i][j] = 0;
                } else if(map[i][j] == '*') {
                    treasure = new int[] {i, j};
                }
            }
        }

        while(!dq.isEmpty()) {
            int[] curr = dq.pollFirst();

            int x = curr[0];
            int y = curr[1];

            for(int dir = 0; dir < 8; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 0 || nx >= H || ny < 0 || ny >= W) continue;
                // 암초이거나 방문했으면 continue
                if(map[nx][ny] == '#') continue;
                if(dy[dir] == 1) {
                    if(fuel[nx][ny] <= fuel[x][y]) continue;

                    fuel[nx][ny] = fuel[x][y];
                    dq.offerFirst(new int[] {nx, ny});
                } else {
                    if(fuel[nx][ny] <= fuel[x][y] + 1) continue;

                    fuel[nx][ny] = fuel[x][y] + 1;
                    dq.offerLast(new int[] {nx, ny});
                }
            }
        }

        System.out.println(fuel[treasure[0]][treasure[1]] == 1_000_000 ?
                -1 : fuel[treasure[0]][treasure[1]]);
    }
}
