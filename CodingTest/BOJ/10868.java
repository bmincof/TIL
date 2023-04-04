package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10868 {
    static int size;
    static int[] arr;
    static int[] minTree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        arr = new int[n];
        size = 4*n;
        minTree = new int[size];
        Arrays.fill(minTree, Integer.MAX_VALUE);
        for(int i = 0; i < n; i++) {
            arr[i]  = Integer.parseInt(br.readLine());
        }

        init(0, n-1, 1);

        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken()) - 1;
            int right = Integer.parseInt(st.nextToken()) - 1;

            sb.append(findMin(left, right, 1, 0, n-1)).append("\n");
        }

        System.out.println(sb);
    }

    static void init(int ldx, int rdx, int node) {
        if(ldx == rdx) {
            minTree[node] = arr[ldx];
            return;
        }

        int mid = (ldx + rdx) / 2;

        init(ldx, mid, node*2);
        init(mid+1, rdx, node*2+1);
        minTree[node] = Math.min(minTree[node*2], minTree[node*2+1]);
    }

    static int findMin(int left, int right, int node, int ldx, int rdx) {
        // 범위 밖이면 최댓값 출력
        if(ldx > right || rdx < left) {
            return Integer.MAX_VALUE;
        }
        if(left <= ldx && rdx <= right) {
            return minTree[node];
        }
        int mid = (ldx + rdx) / 2;
        return Math.min(findMin(left, right, node*2, ldx, mid),
                findMin(left, right, node*2+1, mid+1, rdx));
    }

}
