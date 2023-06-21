package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-21
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            // 가지고 있는 열쇠를 비트마스킹으로 표현한 값
            // 한 번 도달할 수 있는 열쇠는 모든 경우에서 얻을 수 있는 열쇠이므로
            // 전역으로 관리해서 불필요한 이동을 줄이기
            int key = 0;

            char[][] map = new char[h+2][w+2];
            // 방문했을 때 가지고 있는 키의 종류
            int[][] visit = new int[h+2][w+2];
            Queue<int[]> q = new LinkedList<>();

            // BFS 편의를 위해 패딩추가
            for(int i = 0; i < h+2; i++) {
                char[] line = {};
                if(1 <= i && i <= h) {
                    line = br.readLine().toCharArray();
                }

                for(int j = 0; j < w+2; j++) {
                    visit[i][j] = -1;
                    if(1 <= i && i <= h && 1 <= j && j <= w)
                        map[i][j] = line[j-1];
                }
            }

            String firstKey = br.readLine();
            if(firstKey.charAt(0) != '0') {
                for (int i = 0; i < firstKey.length(); i++) {
                    key |= 1 << (firstKey.charAt(i) - 'a');
                }
            }


            visit[0][0] = key;
            // 행, 열, 키
            q.offer(new int[] {0, 0});

            // 훔친 문서의 수
            int docs = 0;
            while(!q.isEmpty()) {
                int[] curr = q.poll();

                for(int dir = 0; dir < 4; dir++) {
                    int nx = curr[0] + dx[dir];
                    int ny = curr[1] + dy[dir];

                    if(nx < 0 || nx >= h+2 || ny < 0 || ny >= w+2) continue;
                    // 벽이거나 이전 방문했을 때와 가지고 있는 열쇠가 같다면
                    if(map[nx][ny] == '*' || visit[nx][ny] == key) continue;
                    // 열쇠라면
                    if('a' <= map[nx][ny] && map[nx][ny] <= 'z') {
                        key |= (1 << (map[nx][ny] - 'a'));
                    }
                    // 문이라면
                    else if('A' <= map[nx][ny] && map[nx][ny] <= 'Z') {
                        // 필요한 열쇠가 없다면
                        if((key & (1 << (map[nx][ny] - 'A'))) == 0) continue;
                    }
                    // .이라면
                    else if(map[nx][ny] == '$') {
                        map[nx][ny] = '.';
                        docs++;
                    }

                    visit[nx][ny] = key;
                    q.offer(new int[] {nx, ny});
                }
            }

            sb.append(docs).append("\n");
        }

        System.out.println(sb);
    }
}
