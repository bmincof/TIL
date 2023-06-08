package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] arr = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++)
                arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];

            int maxSum = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++) {
                for(int j = i + 1; j <= n; j++) {
                    maxSum = Math.max(maxSum, arr[j] - arr[i]);
                }
            }

            sb.append(maxSum).append("\n");
        }

        System.out.println(sb);
    }
}
