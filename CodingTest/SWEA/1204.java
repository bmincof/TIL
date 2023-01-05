import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
 
class Solution {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cases = Integer.parseInt(st.nextToken());
 
        for(int t = 0; t < cases; t++) {
            Integer[] numbers = new Integer[101];
            Arrays.fill(numbers, 0);
            int max = 0;
            br.readLine();
             
            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()) {
                numbers[Integer.parseInt(st.nextToken())]++;
            }
             
            for (int i = 1; i < numbers.length; i ++) {
                if(numbers[i] >= numbers[max]) {
                    max = i;
                }
            }
             
            System.out.println("#"+(t+1)+" "+max);
        }
 
    }
 
}
