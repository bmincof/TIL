package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String n = st.nextToken();
        int len = n.length();

        int k = Integer.parseInt(st.nextToken());

        // 만들었던 수 중 가장 큰 숫자를 저장하기 위한 변수
        int biggest = Integer.MIN_VALUE;
        // 몇 번 연산했는지
        int operated = 0;

        Queue<String> q = new ArrayDeque<>();
        q.offer(n);

        while(!q.isEmpty()) {
            int iter = q.size();
            operated++;

            // 한 번 만들었던 수를 체크하기 위한 배열
            boolean[] check = new boolean[1_000_001];
            boolean swap = false;

            for(int i = 0; i < iter; i++) {
                String curr = q.poll();
                char[] currArr = curr.toCharArray();

                // 각 자릿수의 숫자를 바꾸면서 새로운 값을 만들기
                for(int digitSm = 0; digitSm < len - 1; digitSm++) {
                    for (int digitLg = digitSm + 1; digitLg < len; digitLg++) {
                        char[] nextArr = Arrays.copyOf(currArr, len);

                        char tmp = nextArr[digitLg];
                        nextArr[digitLg] = nextArr[digitSm];
                        nextArr[digitSm] = tmp;
                        
                        // 0으로 시작하는 숫자는 불가능
                        if (nextArr[0] == '0') continue;

                        // 새로만든 숫자
                        String next = String.valueOf(nextArr);
                        int nextNumber = Integer.parseInt(next);

                        // 이미 만든 적 있는 숫자이면 스킵
                        if (check[nextNumber]) continue;

                        check[nextNumber] = true;
                        swap = true;

                        if (operated < k) q.offer(next);
                        if (operated == k) {
                            biggest = Math.max(biggest, nextNumber);
                        }
                    }
                }
            }
            if(!swap) operated--;
        }
        
        System.out.println(operated == k ? biggest : -1);
    }
}
