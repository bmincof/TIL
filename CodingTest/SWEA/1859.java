import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int days;
        int day;
        int[] prices;
        int highest;
        long benefit;

        for(int i = 1; i <= T; i++) {
            days = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            prices = new int[days];
            day = 0;
            benefit = 0;
            
            while(st.hasMoreTokens()) {
                prices[day++] = Integer.parseInt(st.nextToken());
            }
            highest = prices[days-1];
            
            for(int today = days - 2; today >= 0; today--) {
                if(prices[today] < highest) {
                    benefit += highest - prices[today];
                } else {
                    highest = prices[today];
                }
            }

            System.out.println("#" + i + " " + benefit);

        }
    }
}
