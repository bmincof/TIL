import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    final static int[] di = new int[]{0,1,0,-1};
    final static int[] dj = new int[]{1,0,-1,0};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] paint = new int[n][m];
        int[][] vis = new int[512][512];

        int cnt = 0;
        int maxSize = 0;

        while(br.ready()) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    paint[i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }

        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(paint[i][j] == 1 && vis[i][j] == 0) {
                    cnt++;
                    int size = 1;
                    vis[i][j] = 1;
                    q.add(new Point(i,j));

                    while(!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int ni = cur.i + di[dir];
                            int nj = cur.j + dj[dir];

                            if(ni < 0 || ni >= n || nj < 0 || nj >= m) continue;
                            if(vis[ni][nj] == 1 || paint[ni][nj] == 0) continue;

                            vis[ni][nj] = 1;
                            q.add(new Point(ni,nj));
                            size++;
                        }
                    }
                    if(size > maxSize) maxSize = size;
                }

            }
        }

        System.out.println(cnt);
        System.out.println(maxSize);

    }

    static class Point {
        int i;
        int j;

        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
