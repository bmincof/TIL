import java.io.IOException;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) throws IOException {
         
        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
         
        final int T = sc.nextInt();
        int n; 
         
        for (int testCase = 1; testCase <= T; testCase++) {
            n = sc.nextInt();
            sb = new StringBuilder(n*n);
             
            int[][] pascal = new int[n][n];
             
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    if (j == 0 || j == i) {
                        pascal[i][j] = 1;
                    } else {
                        pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];   
                    }
                     
                    sb.append(pascal[i][j] + " ");
                }
                 
                if (i != n-1) sb.append("\n");
            }
            System.out.println("#"+testCase);
            System.out.println(sb.toString());
        }
    }
}
