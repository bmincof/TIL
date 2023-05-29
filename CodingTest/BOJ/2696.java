package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append((n + 1) / 2).append("\n");

            int numberOfLines = (n / 10) + (n % 10 != 0 ? 1 : 0);

            PriorityQueue<Integer> lower = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> upper = new PriorityQueue<>();

            for(int i = 0; i < numberOfLines; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int tokenCount = 0;

                while(st.hasMoreTokens()){
                    tokenCount++;

                    int next = Integer.parseInt(st.nextToken());

                    // 다음 값이 현재 중간값이하라면
                    if (lower.isEmpty() || next <= lower.peek()) {
                        lower.offer(next);
                    } else {
                        upper.offer(next);
                    }

                    // 두 우선순위 큐의 크기 차이가 2 이상이라면 밸런싱하기
                    if (lower.size() - upper.size() > 1) {
                        upper.offer(lower.poll());
                    } else if (upper.size() - lower.size() > 1) {
                        lower.offer(upper.poll());
                    }

                    if (tokenCount % 2 == 1)
                        sb.append(lower.size() >= upper.size() ? lower.peek() : upper.peek()).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
