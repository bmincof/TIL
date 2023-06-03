package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 데이터의 수
        int N = Integer.parseInt(st.nextToken());
        // 쿼리의 수
        int Q = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] delta = new int[N + 1];
        st = new StringTokenizer(br.readLine());

        // 구간별 변화량 저장하기
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(i >= 2)
                delta[i] = delta[i - 1] + Math.abs(arr[i] - arr[i - 1]);
        }

        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(delta[end] - delta[start]).append("\n");
        }

        System.out.println(sb);
    }
}
