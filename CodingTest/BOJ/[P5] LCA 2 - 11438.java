package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-15
public class Main {
    static int[][] parent;
    static int[] nodeDepth;
    static boolean[] visit;
    static List<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        final int MAX_DEPTH = 20;

        // 노드의 개수
        int N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];

        parent = new int[MAX_DEPTH+1][N+1];
        nodeDepth = new int[N+1];
        visit = new boolean[N+1];

        for(int i = 1; i <= N; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        initTree();

        // 희소 배열을 초기화하기
        for(int i = 1; i <= MAX_DEPTH; i++) {
            for(int j = 1; j <= N; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        int Q = Integer.parseInt(br.readLine());

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());

            int higher = Integer.parseInt(st.nextToken());
            int lower = Integer.parseInt(st.nextToken());

            if(nodeDepth[higher] != nodeDepth[lower]) {
                // 노드의 depth에 따라 변수에 담기
                if (nodeDepth[higher] > nodeDepth[lower]) {
                    int tmp = higher;
                    higher = lower;
                    lower = tmp;
                }

                int depthDiff = nodeDepth[lower] - nodeDepth[higher];
                // 두 노드의 높이를 맞춰주기
                for (int i = MAX_DEPTH; i >= 0; i--) {
                    if ((depthDiff & (1 << i)) == 0) continue;
                    lower = parent[i][lower];
                }
            }

            if(higher == lower) {
                sb.append(higher).append("\n");
                continue;
            }

            // 최소 공통 조상 직전까지 올라가기
            for(int i = MAX_DEPTH; i >= 0; i--) {
                // 2^i번째 조상노드가 같다면 건너뛰기 (최소 공통조상을 지나칠 수 있음)
                if(parent[i][higher] == parent[i][lower]) continue;
                higher = parent[i][higher];
                lower = parent[i][lower];
            }

            sb.append(parent[0][higher]).append("\n");
        }

        System.out.println(sb);
    }

    static void initTree() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {1, 0});
        visit[1] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();

            for(int child : adj[curr[0]]) {
                if(visit[child]) continue;

                parent[0][child] = curr[0];
                nodeDepth[child] = curr[1] + 1;
                q.offer(new int[] {child, nodeDepth[child]});
                visit[child] = true;
            }
        }
    }
}
