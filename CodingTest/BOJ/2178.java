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

        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};

        int[][] dist = new int[100][100];
        char[][] maze = new char[n][m];

        while(br.ready()) {
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    maze[i][j] = s.charAt(j);
                }
            }
        }

        Queue<Point> q = new LinkedList<>();

        q.add(new Point(0,0));
        dist[0][0] = 1;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(nx < 0 || nx >= n || ny < 0 || ny >=m) continue;
                if(maze[nx][ny] != '1' || dist[nx][ny] > 0) continue;

                dist[nx][ny] = dist[cur.x][cur.y] + 1;
                if(nx == n-1 && ny == m-1) break;
                q.add(new Point(nx, ny));
            }
        }

        System.out.println(dist[n-1][m-1]);
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
