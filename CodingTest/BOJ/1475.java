import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();
        int[] cnt = new int[10];
        int mx = 0;
        for (int i = 0; i < n.length(); i++) cnt[n.charAt(i)-'0']++;
        int sn = cnt[6] + cnt[9];

        for (int i = 0; i < 10; i++) {
            if((i != 6 && i != 9) && cnt[i] > mx) mx = cnt[i];
        }

        System.out.println(Math.max(mx,sn/2 + sn%2));

    }
}
