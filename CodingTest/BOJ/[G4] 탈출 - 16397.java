package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-23
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int LIMIT = 99999;

        // LED로 표현된 수
        int N = Integer.parseInt(st.nextToken());
        // 버튼을 누를 수 있는 최대 횟수
        int T = Integer.parseInt(st.nextToken());
        // 탈출을 위해 똑같이 만들어야 하는 수
        int G = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        int[] cnt = new int[LIMIT+1];

        q.offer(N);
        cnt[N] = 1;

        while(!q.isEmpty()) {
            int cur = q.poll();

            if(cur == G) break;
            if(cnt[cur] > T) continue;

            // 버튼 A
            int a = cur + 1;
            if(a <= LIMIT && cnt[a] == 0) {
                cnt[a] = cnt[cur] + 1;
                q.offer(a);
            }

            // 버튼 B
            int b = cur * 2;
            if(b > LIMIT) continue;

            int digit = 10000;
            while(digit > 0) {
                if(b / digit != 0) {
                    b -= digit;
                    break;
                }
                digit /= 10;
            }

            if(b <= LIMIT && cnt[b] == 0) {
                q.offer(b);
                cnt[b] = cnt[cur] + 1;
            }
        }

        System.out.println(cnt[G] == 0 ? "ANG" : (cnt[G] - 1));
    }
}
