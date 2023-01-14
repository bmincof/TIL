import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(br.readLine());
        int[] arr = new int[1000001];
        int[] cnts = new int[2000001];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            if(x-arr[i] > 0 && cnts[x-arr[i]] > 0) cnt += cnts[x-arr[i]];
            else cnts[arr[i]]++;
        }

        System.out.println(cnt);
    }
}
