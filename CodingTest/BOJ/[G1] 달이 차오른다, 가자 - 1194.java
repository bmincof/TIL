package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-14
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        // 세로, 가로 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // {보유한 열쇠 상태, 행, 열}
        // 가지고 있는 열쇠는 비트마스킹을 이용해서 표시
        Queue<int[]> queue = new LinkedList<>();

        char[][] map = new char[N][M];
        // [보유한 열쇠 상태][행][열] 의 방문상태를 나타내는 배열
        int[][][] visited = new int[1 << 6][N][M];


        int[] startPoint = new int[2];

        for(int i = 0; i < N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = line[j];

                if(map[i][j] == '0') {
                    startPoint[0] = i;
                    startPoint[1] = j;
                }
            }
        }

        // BFS 시작
        queue.offer(new int[] {0, startPoint[0], startPoint[1]});
        visited[0][startPoint[0]][startPoint[1]] = 1;

        int answer = -1;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();

            // 보유한 열쇠의 상태
            int status = curr[0];
            int r = curr[1];
            int c = curr[2];
          
            // 목적지에 도착했다면 BFS를 종료
            if(map[r][c] == '1') {
                answer = visited[status][r][c] - 1;
                break;
            }

            for(int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                // 방문할 수 없는 위치라면 생략하기
                if(nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '#') continue;
                char visit = map[nr][nc];
                int nStatus = status;

                // 열쇠를 발견했다면 보유한 열쇠에 추가하기
                if('a' <= visit && visit <= 'f') nStatus |= 1 << (visit - 'a');
                // 문을 발견했고 열쇠가 없다면 생략하기
                else if('A' <= visit && visit <= 'F' && (status & 1 << (visit - 'A')) == 0) continue;

                // 같은 상태에 더 일찍 도착한 적 있다면 continue
                if(visited[nStatus][nr][nc] != 0) continue;

                // 방문한 위치를 BFS 큐에 추가하기
                queue.offer(new int[] {nStatus, nr, nc});
                visited[nStatus][nr][nc] = visited[status][r][c] + 1;
            }
        }

        System.out.println(answer);
    }
}
