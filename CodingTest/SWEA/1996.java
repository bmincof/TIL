import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;
        int[] arr;
 
        final int T = Integer.parseInt(br.readLine());
        int n;
 
 
        for(int i=1; i<=T; i++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            sb = new StringBuilder(2*n);
            sb.append("#").append(i);
            arr = new int[n];
 
            for(int j=0; j<n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
 
            Arrays.sort(arr);
 
            for (int j : arr) {
                sb.append(" ").append(j);
            }
 
            System.out.println(sb);
        }
    }
}
