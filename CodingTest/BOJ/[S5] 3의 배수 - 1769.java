package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// author : bmincof
// date   : 2023-06-12
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();

        // 변환 횟수
        int cnt = 0;
        while(n.length() > 1) {
            // 최대 100만 자리이므로 9가 100만 개 있을 때가 최대이고 int범위로 표현가능
            int tmp = 0;

            // n의 각 자리수를 더하기
            for(int i = 0; i < n.length(); i++) {
                tmp += n.charAt(i) - '0';
            }

            n = String.valueOf(tmp);
            cnt++;
        }

        System.out.println(cnt);
        // n은 1~9 사이의 숫자가 됨
        System.out.println(Integer.parseInt(n) % 3 == 0 ? "YES" : "NO");
    }
}
