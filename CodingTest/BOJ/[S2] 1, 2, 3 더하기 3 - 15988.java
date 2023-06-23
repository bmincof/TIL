    package boj;

    import java.io.BufferedReader;
    import java.io.InputStreamReader;

    // author : bmincof
    // date   : 2023-06-24
    public class Main {
        public static void main(String[] args) throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

            final int MOD = 1_000_000_009;

            int T = Integer.parseInt(br.readLine());

            while(T-- > 0) {
                int n = Integer.parseInt(br.readLine());

                // 1, 2, 3으로 숫자 [i]를 만들 수 있는 경우의 수
                int[] cnt = new int[n > 3 ? n+1 : 4];
                cnt[1] = 1;
                cnt[2] = 2;
                cnt[3] = 4;

                for(int i = 4; i <= n; i++) {
                    cnt[i] = (cnt[i] + cnt[i-3]) % MOD;
                    cnt[i] = (cnt[i] + cnt[i-2]) % MOD;
                    cnt[i] = (cnt[i] + cnt[i-1]) % MOD;
                }

                sb.append(cnt[n]).append("\n");
            }
            System.out.println(sb);
        }
    }
