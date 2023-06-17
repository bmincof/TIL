package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-16
public class Main {
    static List<int[]>[] adj;
    static boolean[] visit;

    // 리프노드에서 시작해서 dfs로 가장 긴 거리를 측정하기
    // 근데 모든 리프노드마다 확인해봐야 놓치는 경우가 없어진다.
    // 리프노드의 수가 그렇게 많아지지는 않을 것이므로 시간복잡도 문제는 없을듯
    // 루트에서 가장 먼 리프노드를 찾고, 그 리프노드부터 가장 먼 거리를 찾으면 지름이 된다 (더 빠른 풀이)
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();

        int[] outdegree = new int[n+1];

        for(int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            adj[u].add(new int[] {v, dist});
            adj[v].add(new int[] {u, dist});

            outdegree[u]++;
        }

        int diameter = 0;
        for(int i = 1; i <= n; i++) {
            if(outdegree[i] == 0) {
                visit = new boolean[n+1];
                diameter = Math.max(diameter, (dfs(i, 0)));
            }
        }

        System.out.println(diameter);
    }

    static int dfs(int nodeNumber, int length) {
        int diameter = length;
        visit[nodeNumber] = true;

        // 자식 노드로 내려가면서 가장 긴 길이 찾기
        for(int[] edge : adj[nodeNumber]) {
            if(visit[edge[0]]) continue;
            diameter = Math.max(diameter, dfs(edge[0], length + edge[1]));
        }

        return diameter;
    }
}
