package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-20
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 정점의 개수
        int V = Integer.parseInt(br.readLine());

        // [노드번호] {자식노드번호, 거리}
        List<int[]>[] adj = new ArrayList[V+1];
        for(int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

        for(int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int child = Integer.parseInt(st.nextToken());
                if(child == -1) break;

                int distance = Integer.parseInt(st.nextToken());
                adj[parent].add(new int[] {child, distance});
            }
        }

        // 루트노드를 시작점으로 bfs해서 가장 먼 노드를 찾기 (리프노드 중 하나)
        // 트리가 보장되므로 루트노드는 임의의 노드로 선택해도 됨
        int farthest = bfs(adj, V, 1)[0];
        System.out.println(bfs(adj, V, farthest)[1]);
    }

    static int[] bfs(List<int[]>[] tree, int V, int root) {
        int[] farthest = new int[2];

        Queue<int[]> q = new LinkedList<>();
        boolean[] vis = new boolean[V+1];

        q.offer(new int[] {root, 0});
        vis[root] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            // 루트노드로부터 가장 먼 거리의 노드 번호와 거리를 저장
            if(curr[1] > farthest[1]) {
                farthest[0] = curr[0];
                farthest[1] = curr[1];
            }

            for(int[] child : tree[curr[0]]) {
                if(vis[child[0]]) continue;

                // 현재까지의 거리 + 자식노드의 거리
                q.offer(new int[] {child[0], curr[1] + child[1]});
                vis[child[0]] = true;
            }
        }

        return farthest;
    }
}
