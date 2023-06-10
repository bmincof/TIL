package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        while(n-- > 0) pq.offer(Long.parseLong(st.nextToken()));

        while(m-- > 0) {
            long sum = pq.poll() + pq.poll();

            pq.offer(sum);
            pq.offer(sum);
        }

        long answer = 0;

        while(!pq.isEmpty()) answer += pq.poll();

        System.out.println(answer);
    }
}
