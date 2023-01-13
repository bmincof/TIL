import java.util.Scanner;
 
class Solution {
     
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
         
        final int T = sc.nextInt();
        int[] change = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
        int money;
         
        for(int testCase = 1; testCase <= T; testCase++) {
            money = sc.nextInt();
            sb = new StringBuilder(2*change.length);
            System.out.println("#" + testCase);
            for(int i = 0; i < change.length; i++) {
                sb.append(money / change[i]).append(" ");
                money %= change[i];
            }
             
            System.out.println(sb);
        }       
    }
     
}
