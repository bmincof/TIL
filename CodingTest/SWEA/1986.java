import java.util.Scanner;
 
class Solution {
     
    public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
         
        final int T = sc.nextInt();
        int n;
        int result;
         
        for(int testCase = 1; testCase <= T; testCase++) {
            n = sc.nextInt();
            result = -1 * n/2 + (n % 2 == 1 ? n : 0);
            sb = new StringBuilder("#").append(testCase).append(" ").append(result);
            System.out.println(sb);
        }       
    }
     
}
