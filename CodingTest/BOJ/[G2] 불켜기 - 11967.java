package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 한 변의 크기
        int N = Integer.parseInt(st.nextToken());
        // 스위치의 개수
        int M = Integer.parseInt(st.nextToken());

        // 스위치 정보 (인접 리스트)
        List<int[]>[][] switches = new ArrayList[N+1][N+1];

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(switches[x][y] == null) switches[x][y] = new ArrayList<>();

            switches[x][y].add(new int[] {a, b});
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] isReachable = new boolean[N+1][N+1];
        boolean[][] isLighted = new boolean[N+1][N+1];
        boolean[][] visited = new boolean[N+1][N+1];

        int lighted = 1;

        q.offer(new int[] {1, 1});
        isLighted[1][1] = visited[1][1] = isReachable[1][1] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int x = curr[0];
            int y = curr[1];

            // 불이 켜진 곳의 인접한 칸들을 표시하기
            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if(nx < 1 || nx > N || ny < 1 || ny > N) continue;
                isReachable[nx][ny] = true;
                // 불을 켜놓고 방문한 적 없다면
                if(isLighted[nx][ny] && !visited[nx][ny]) {
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
            }

            // 현재 위치에서 조작할 수 있는 스위치 모두 켜기
            if(switches[x][y] != null) {
                for(int[] next : switches[x][y]) {
                    int a = next[0];
                    int b = next[1];

                    // 처음 불을 켜는 곳이라면
                    if(!isLighted[a][b]) {
                        isLighted[a][b] = true;
                        lighted++;
                    }

                    // 새롭게 불을 켠 곳에 방문할 수 있다면
                    if(isReachable[a][b] && !visited[a][b]) {
                        q.offer(new int[] {a, b});
                        visited[a][b] = true;
                    }
                }
            }
        }

        System.out.println(lighted);
    }
}
