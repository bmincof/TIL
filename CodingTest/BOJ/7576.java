import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] day = new int[1010][1010];
        int[][] box = new int[1010][1010];
        int[] dx = new int[]{1,-1,0,0};
        int[] dy = new int[]{0,0,1,-1};

        for(int[] d: day) {
            Arrays.fill(d, -1);
        }
        while(br.ready()) {
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    box[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(box[i][j] == 1) {
                    day[i][j] = 0;
                    q.add(new Point(i,j));
                }
            }
        }

        while(!q.isEmpty()) {
            Point cur = q.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(box[nx][ny] != 0 || day[nx][ny] >= 0) continue;

                box[nx][ny] = 1;
                day[nx][ny] = day[cur.x][cur.y] + 1;
                q.add(new Point(nx,ny));
            }
        }

        int last = 0;
        boolean isImpo = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(box[i][j] == 0) {
                    isImpo = true;
                    break;
                }
                last = Math.max(last, day[i][j]);
            }
        }

        System.out.println(isImpo ? -1 : last);

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
