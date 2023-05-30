package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // 각 정점의 그룹을 나타내기 위한 배열
            Boolean[] color = new Boolean[V + 1];

            String result = "YES\n";

            List<Integer>[] adj = new ArrayList[V + 1];

            for(int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
            }

            while(E-- > 0) {
                st = new StringTokenizer(br.readLine());

                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj[u].add(v);
                adj[v].add(u);
            }

            Queue<Integer> q = new ArrayDeque<>();

            for(int i = 1; i <= V; i++) {
                if(color[i] != null) continue;

                // 아직 방문한 적 없는 컴포넌트의 시작점은 true로
                color[i] = true;
                q.offer(i);

                check:
                while (!q.isEmpty()) {
                    int curr = q.poll();

                    // 현재 노드와 인접한 노드를 살펴보기
                    for (int next : adj[curr]) {
                        // 처음 방문하는 곳이면
                        if (color[next] == null) {
                            // 현재 노드와 다른 색을 칠하기
                            color[next] = !color[curr];
                            q.offer(next);
                            // 이미 방문한 곳이면
                        } else {
                            // 현재 노드와 색깔이 같은 노드가 있다면 이분 그래프가 아님
                            if (color[next].equals(color[curr])) {
                                result = "NO\n";
                                break check;
                            }
                        }
                    }
                }
            }

            sb.append(result);
        }

        System.out.println(sb);
    }
}
