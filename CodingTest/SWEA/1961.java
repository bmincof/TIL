import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
 
        int testCases = Integer.parseInt(br.readLine());
 
        for(int t = 0; t < testCases; t++) {
            int n = Integer.parseInt(br.readLine());
            int[][] matrix = new int[n][n];
 
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int j = 0;
 
                while(st.hasMoreTokens()) {
                    matrix[i][j++] = Integer.parseInt(st.nextToken());
                }
            }
 
            int[][] r90 = rot90(matrix);
            int[][] r180 = rot180(matrix);
            int[][] r270 = rot270(matrix);
 
            System.out.println("#"+(t+1));
            for(int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder(4*n);
                 
                for(int j = 0; j < n; j++) {
                    sb.append(r90[i][j]);
                }
                sb.append(" ");
                for(int j = 0; j < n; j++) {
                    sb.append(r180[i][j]);
                }
                sb.append(" ");
                for(int j = 0; j < n; j++) {
                    sb.append(r270[i][j]);
                }
                 
                System.out.println(sb.toString());
            }
        }
    }
    static int[][] rot90(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
 
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[i][j] = mat[n-1-j][i];
            }
        }
 
        return rotated;
    }
 
    static int[][] rot180(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
 
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[i][j] = mat[n-1-i][n-1-j];
            }
        }
 
        return rotated;
    }
 
    static int[][] rot270(int[][] mat) {
        int n = mat.length;
        int[][] rotated = new int[n][n];
 
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotated[i][j] = mat[j][n-1-i];
            }
        }
 
        return rotated;
    }
}
