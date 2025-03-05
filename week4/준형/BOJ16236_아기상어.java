import java.io.*;
import java.util.*;

// 큰 물고기는 이동x 먹기x
// 같은 물고기는 이동o 먹기x
// 작은 물고기 이동o 먹기o

// 이동 결정
// 1. 먹을 수 없으면 엄마한테 도움 -> 도움 요청 이번엔 안함
// 2. 먹을 수 있는 물고기 1마리면 그곳으로 감
// 3. 먹을 수 있는 물고기가 1마리보다 많으면, 거리가 가장 가까운 물고기를 먹으러 감
// 3-1. 거리가 같다면, 가장 위 - > 가장 왼쪽 순으로 이동

// 성장
// 자신의 크기와 같은 수의 물고기를 먹으면 크기 1증가

//0: 빈 칸
//1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
//9: 아기 상어의 위치

public class Main {

	static int N;
	static int[][] map;
	static int sharkSize = 2;
	static int sharkY, sharkX;
	static int eaten = 0;
	static int time = 0;

	static int[] dy = {-1, 0, 0, 1};
	static int[] dx = {0, -1, 1, 0};

	static class Node {
		int y;
		int x;
		int dist;

		Node(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}
	}

	static Node bfs() {
		boolean[][] visited = new boolean[N][N];
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sharkY, sharkX, 0));
		visited[sharkY][sharkX] = true;
		int minDist = Integer.MAX_VALUE;
		ArrayList<Node> candis = new ArrayList<>();

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (cur.dist > minDist) {
				continue;
			}

			if (map[cur.y][cur.x] != 0 && map[cur.y][cur.x] < sharkSize) {
				if (cur.dist < minDist) {
					minDist = cur.dist;
					candis.clear();
					candis.add(new Node(cur.y, cur.x, cur.dist));
				} else if (cur.dist == minDist) {
					candis.add(new Node(cur.y, cur.x, cur.dist));
				}
			}

			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];

				if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if(visited[ny][nx]) continue;

				if (map[ny][nx] <= sharkSize) {
					visited[ny][nx] = true;
					q.add(new Node(ny, nx, cur.dist + 1));
				}
			}
		}

		if(candis.isEmpty()) return null;
		// 여러 후보가 있을 경우, 조건(가장 위, 그 중 왼쪽)에 따라 정렬 후 첫 번째 선택
		Collections.sort(candis, (a, b) -> {
			if(a.y == b.y) return a.x - b.x;
			return a.y - b.y;
		});
		return candis.get(0);


	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// init
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkY = i;
					sharkX = j;
					map[i][j] = 0;
				}
			}
		}

		// process
		while (true) {

			Node candidate = bfs();
			if (candidate == null) {
				break;
			}

			time += candidate.dist;
			sharkY = candidate.y;
			sharkX = candidate.x;

			map[sharkY][sharkX] = 0;
			eaten += 1;

			if(eaten == sharkSize) {
				sharkSize += 1;
				eaten = 0;
			}
		}
		System.out.println(time);
	}
}