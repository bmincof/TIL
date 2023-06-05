package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        // 쿼리의 수
        int Q = Integer.parseInt(br.readLine());

        int[][] alphabets = new int[26][str.length() + 1];

        for(int i = 1; i <= str.length(); i++) {
            for(int j = 0; j < 26; j++) {
                alphabets[j][i] = alphabets[j][i - 1] + (str.charAt(i - 1) - 'a' == j ? 1 : 0);
            }
        }

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char c = st.nextToken().charAt(0);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(alphabets[c-'a'][end + 1] - alphabets[c-'a'][start]).append("\n");
        }

        System.out.println(sb);
    }
}
