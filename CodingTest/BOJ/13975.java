package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long total = 0;
            while(pq.size() > 1) {
                long cost = pq.poll() + pq.poll();

                total += cost;
                pq.offer(cost);
            }

            sb.append(total).append("\n");
        }

        System.out.println(sb);
    }
}
