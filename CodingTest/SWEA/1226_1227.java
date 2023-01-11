import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	
	static final int VISITED = 5; 
	static final int FINISH = 3;
	static final int T = 10;
	static final int N = 16;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String row;
        
        for (int t = 0; t < T; t++) {
        	int testCase = Integer.parseInt(br.readLine());
			int[][] maze = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				row = br.readLine();
				for (int j = 0; j < N; j++) {
					maze[i][j] = row.charAt(j) - '0';
				}
			}
			
			System.out.println("#"+testCase +" "+ find(maze, 1, 1));
		}
		
	}
	
	static int find(int[][] maze, int i, int j) {
		if (maze[i][j] == FINISH) {
			return 1;
		}
		
		int canFinish = 0;
		
		maze[i][j] = VISITED;
		
		if(isRoad(maze, i-1, j)) {						// 위쪽 길이 있고, 들렀던 길이 아니면 탐색
			canFinish = find(maze, i-1, j);
			if (canFinish == 1) return 1;
		}
		if(isRoad(maze, i, j+1)) {						// 오른쪽 길이 있고, 들렀던 길이 아니면 탐색
			canFinish = find(maze, i, j+1);
			if (canFinish == 1) return 1;
		}
		if(isRoad(maze, i+1, j)) {						// 아래쪽 길이 있고, 들렀던 길이 아니면 탐색
			canFinish = find(maze, i+1, j);
			if (canFinish == 1) return 1;
		}
		if(isRoad(maze, i, j-1)) {						// 왼쪽 길이 있고, 들렀던 길이 아니면 탐색
			canFinish = find(maze, i, j-1);
			if (canFinish == 1) return 1;
		}

		
		return 0;
	}
	
	static boolean isRoad(int[][] maze, int i, int j) {
		
		return (maze[i][j] == 0 || maze[i][j] == FINISH);
	}
}

/*
 * 해당 칸이 벽(1)이면 뒤로 돌아가기
 * 해당 칸이 길(0)이면 길 탐색
 * 해당 칸이 도착점(3)이면 1 리턴
 * 모든 길 탐색해도 도착점 없으면 0 리턴
 * 
 * 4 방향 탐색, 가려는 방향이 from(i, j)과 같은 값이면 탐색 x -> 3 방향
 */


/*
 * find(maze, i, j) {
 * 		maze[i][j] = 999 			왔던 길 표시
 * 		if maze[i-1][j] == 0
 * 			return find(maze, i-1, j)	현재 칸 위
 * 		if maze[i][j+1] == 0
 * 			return find(maze, i, j+1)	오른쪽
 * 		if maze[i+1][j] == 0
 * 			return find(maze, i+1, j)	아래
 * 		if maze[i][j-1] == 0
 * 			return find(maze, i, j-1)	왼쪽
 * 
 * 		return 0					모든 길 탐색해도 길이 없는 경우
 * }
 * 
 * 
 */
