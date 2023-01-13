import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
 
/*
 * input : N(사람 수), M(생산시간), K(생산개수)
 * M 초마다 K 개의 붕어빵
 * 
 * 1. 변수 time, stock, customer
 * 2. time이 M의 배수라면 stock += K
 * 3. customer[time] > stock 이면 불가능
 * 4. 아니라면 stock - customer[time] and customer[time] = 0
 * 5. customer 끝까지 도달하면 가능
 * 
 */
 
 
class Solution {
     
    public static void main(String[] args) throws IOException {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
         
        final int T = Integer.parseInt(br.readLine());
        int n;
        int m;
        int k;
         
        int time;
        int stock;
        int custCount;
        int[] customer;
         
        for(int testCase = 1; testCase <= T; testCase++) {
            time = 0;
            stock = 0;
            custCount = 0;
             
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
             
            customer = new int[11112];
            st = new StringTokenizer(br.readLine());
             
            while(st.hasMoreTokens()) {
                customer[Integer.parseInt(st.nextToken())]++;
            }
             
            while(custCount < n) {
                if(time % m == 0 && time != 0) stock += k;
                 
                if(customer[time] > stock) {
                    break;
                } else {
                    stock -= customer[time];
                    custCount += customer[time];
                    customer[time] = 0;
                }
                time++;
            }
             
            System.out.println("#" + testCase + " " + (custCount == n ? "Possible" : "Impossible"));
             
        }       
    }
     
}
