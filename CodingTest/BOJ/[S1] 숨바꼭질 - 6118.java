package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-13
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 헛간을 그래프의 정점으로 생각하고 1번 정점으로부터 가장 먼 정점을 찾으면 된다.

        // 정점의 개수
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        // 인접 리스트
        List<Integer>[] adj = new ArrayList[V+1];
        for(int i = 1; i <= V; i++) adj[i] = new ArrayList<>();

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        // BFS로 1번 정점으로부터 가장 먼 정점을 찾는다.
        boolean[] vis = new boolean[V+1];
        Queue<Integer> q = new LinkedList<>();

        q.offer(1);
        vis[1] = true;

        // 정답을 저장할 변수
        int hide = 0;   // 숨어야 하는 헛간 번호
        int dist = -1;   // 그 헛간까지의 거리
        int cnt = 0;    // 같은 거리의 헛간 개수

        while(!q.isEmpty()) {
            // 숨어야 하는 헛간 번호와 같은 거리의 헛간 수를 초기화
            cnt = q.size();
            hide = Integer.MAX_VALUE;
            // 거리 1 증가
            dist++;
            // 같은 거리를 갖는 헛간들에 대해서 반복
            for(int i = 0; i < cnt; i++) {
                int curr = q.poll();

                hide = Math.min(hide, curr);
                for(int next : adj[curr]) {
                    if(vis[next]) continue;

                    q.offer(next);
                    vis[next] = true;
                }
            }
        }

        System.out.println(hide + " " + dist + " " + cnt);
    }
}
