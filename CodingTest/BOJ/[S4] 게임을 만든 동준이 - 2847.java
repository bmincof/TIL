package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// author : bmincof
// date   : 2023-06-10
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];

        for(int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int answer = 0;

        // 연산횟수를 최소로 하기 위해 가장 뒤부터 계산합니다
        for(int i = n-2; i >= 0; i--) {
            // 조건에 부합하면 넘어가기
            if(arr[i] < arr[i+1]) continue;

            // 자신 바로 뒤 레벨의 점수보다 1점 낮도록 설정하면 가장 적은 비용이 됩니다.
            int target = arr[i+1] - 1;
            answer += arr[i] - target;

            arr[i] = target;
        }

        System.out.println(answer);
    }
}
