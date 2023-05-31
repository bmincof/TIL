package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        // 구간합을 저장할 배열
        int[] prefixSum = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(prefixSum[end] - prefixSum[start - 1]).append("\n");
        }

        System.out.println(sb);
    }
}
