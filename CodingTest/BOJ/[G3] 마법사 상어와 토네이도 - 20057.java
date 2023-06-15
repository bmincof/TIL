package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// author : bmincof
// date   : 2023-06-15
public class Main {
    static int N;

    // 토네이도 메서드를 왼쪽 방향에 대해서만 만들고 지도를 돌리면서 해결해보기
    // 토네이도가 반시계 방향으로 도니까 지도를 시계방향으로 돌리면 같은 결과를 만들 수 있다.
    // 대신 매우매우매우 느립니다.
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        boolean[][] passed = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작은 중간에서
        int tornadoRow = N/2;
        int tornadoCol = N/2;

        int moved = 1;
        int answer = 0;
        passed[tornadoRow][tornadoCol] = true;
        // 항상 토네이도는 왼쪽으로만 이동하며 회전도 왼쪽만 체크
        while (moved < N*N) {
            int nr = tornadoRow - 1;
            int nc = tornadoCol;

            if(isInside(tornadoRow, tornadoCol-1) && !passed[tornadoRow][tornadoCol-1]) {
                nr = tornadoRow;
                nc = tornadoCol - 1;

                AfterRotated rotated = rotate(map, passed, nr, nc);
                map = rotated.rotatedMap;
                passed = rotated.rotatedPassed;
                nr = rotated.rotatedRow;
                nc = rotated.rotatedCol;
            }

            passed[nr][nc] = true;
            answer += tornado(map, nr, nc);

            tornadoRow = nr;
            tornadoCol = nc;

            moved++;
        }

        System.out.println(answer);
    }

    // 맵을 시계방향으로 돌리기
    static AfterRotated rotate(int[][] map, boolean[][] passed, int tornadoRow, int tornadoCol) {
        AfterRotated after = new AfterRotated();

        int[][] rotatedMap = new int[N][N];
        boolean[][] rotatedPassed = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                rotatedMap[j][N-1-i] = map[i][j];
                rotatedPassed[j][N-1-i] = passed[i][j];

                if(i == tornadoRow && j == tornadoCol) {
                    after.rotatedRow = j;
                    after.rotatedCol = N-1-i;
                }
            }
        }

        after.rotatedMap = rotatedMap;
        after.rotatedPassed = rotatedPassed;
        return after;
    }

    // r, c : 토네이도가 y점에 있을 때 좌표
    static int tornado(int[][] map, int r, int c) {
        // 격자 밖으로 날아간 모래의 양
        int blown = 0;

        // 퍼센트별 이동해야하는 모래량
        int one = map[r][c] / 100;
        int two = map[r][c] * 2 / 100;
        int five = map[r][c] * 5 / 100;
        int seven = map[r][c] * 7 / 100;
        int ten = map[r][c] / 10;

        int alpha = map[r][c] - (2 * one + 2 * two + five + 2 * seven + 2 * ten);

        // 1 % 구역
        if(isInside(r+1, c-1)) {
            map[r+1][c-1] += one;
        } else {
            blown += one;
        }
        if(isInside(r+1, c+1)) {
            map[r+1][c+1] += one;
        } else {
            blown += one;
        }

        // 2% 구역
        if(isInside(r, c-2)) {
            map[r][c-2] += two;
        } else {
            blown += two;
        }
        if(isInside(r, c+2)) {
            map[r][c+2] += two;
        } else {
            blown += two;
        }

        // 5% 구역
        if(isInside(r-2, c)) {
            map[r-2][c] += five;
        } else {
            blown += five;
        }

        // 7% 구역
        if(isInside(r, c-1)) {
            map[r][c-1] += seven;
        } else {
            blown += seven;
        }
        if(isInside(r, c+1)) {
            map[r][c+1] += seven;
        } else {
            blown += seven;
        }

        // 10% 구역
        if(isInside(r-1, c-1)) {
            map[r-1][c-1] += ten;
        } else {
            blown += ten;
        }
        if(isInside(r-1, c+1)) {
            map[r-1][c+1] += ten;
        } else {
            blown += ten;
        }

        // y에 남은 모래를 a로 이동
        if(isInside(r-1, c)) {
            map[r-1][c] += alpha;
        } else {
            blown += alpha;
        }
        map[r][c] = 0;

        return blown;
    }

    static boolean isInside(int r, int c) {
        return !(r < 0 || c < 0 || r >= N || c >= N);
    }

    static class AfterRotated {
        int rotatedRow, rotatedCol;
        int[][] rotatedMap;
        boolean[][] rotatedPassed;
    }
}
