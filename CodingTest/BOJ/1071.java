import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    현재 숫자와 연속된 숫자가 있을 경우, 연속된 숫자보다 더 큰 수가 있을 때와 없을 때
    숫자들을 배열하는 패턴이 다르다는 점을 이용
    1. 현재, 현재 + 1, 현재 + 1 + a 가 있다면
    현재 , 현재 + 1 + a, 현재 + 1 순서로 배치 
    2. 현재, 현재 + 1 만 있다면
    현재 + 1, 현재 순서로 배치
    3. 연속된 수가 없다면 그냥 순서대로 배치
 */
public class BOJ_1071 {
    static int[] cnt = new int[1001];
    static int n;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder(300);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        ArrayList<Integer> values = new ArrayList<>(n);
        int vIdx = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(!values.contains(a)) values.add(a);
            cnt[a]++;
        }

        Collections.sort(values);

        int m = values.size();
        while(vIdx < m) {
            int value = values.get(vIdx);
            if (cnt[value] == 0) {
                vIdx++;
                continue;
            }
            // 현재 값과 연속된 숫자가 없다면
            if (vIdx + 1 < m && (values.get(vIdx + 1) != value + 1 || cnt[values.get(vIdx + 1)] == 0)) {
                for (int i = 0; i < cnt[value]; i++) {
                    sb.append(value).append(' ');
                }
                cnt[value] = 0;
            }
            // 연속된 숫자가 있을 경우
            else if (vIdx + 1 < m && values.get(vIdx + 1) == value + 1 && cnt[values.get(vIdx + 1)] != 0) {                                    // 현재 값과 연속된 숫자가 있다면

                if (vIdx + 2 < m) {             // 연속된 숫자보다 큰 숫자가 있는 경우
                    int nNext = values.get(vIdx + 2);
                    for (int i = 0; i < cnt[value]; i++) {
                        sb.append(value).append(' ');
                    }
                    cnt[value] = 0;
                    sb.append(nNext).append(' ');
                    cnt[nNext]--;
                }
                else {                          // 연속된 숫자가 가장 큰 수 인 경우
                    int next = values.get(vIdx + 1);
                    for (int i = 0; i < cnt[next]; i++) {
                        sb.append(next).append(' ');
                    }
                    cnt[next] = 0;
                    for (int i = 0; i < cnt[value]; i++) {
                        sb.append(value).append(' ');
                    }
                    cnt[value] = 0;
                }
            }
            else {                              // 가장 큰 수이면서 연속되지도 않은 경우
                for (int i = 0; i < cnt[value]; i++) {
                    sb.append(value).append(' ');
                }
            }
            vIdx++;
        }

        System.out.println(sb);
    }

}
