package April_week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2931_가스관 {
	
	/*
	 * 1. 시작점이나 끝점에서 출발해 경로상에서 끊어진 지점을 찾으려고 함. 찾으면 각 가스관을 모두 대입해서 끝까지 이어지나 확인 
	 * => 코드가 너무 복잡해지고 어려워짐
	 * 
	 * 다른 방법? 
	 * 1. 빈 공간 중, 사방에 가스관이 2개 이상 존재하는 경우가 끊어진 칸 
	 * 2. 들어갈 칸을 찾았다면 주변의 가스관이 빈 공간과 어떻게 이어져 있는지 파악 해 들어갈 수 있는 가스관의 모양을 파악한다.
	 */
	
	
	public static char[][] map;
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};

	
	
	public static boolean check(char pipe, int direction) {
		if(direction == 0) {
			if(pipe == '|' || pipe == '+' || pipe == '1' || pipe == '4') {
				return true;
			}
		}else if(direction == 1) {
			if(pipe == '-' || pipe == '+' || pipe == '3' || pipe == '4') {
				return true;
			}
		}else if(direction == 2) {
			if(pipe == '|' || pipe == '+' || pipe == '2' || pipe == '3') {
				return true;
			}
		}else if(direction == 3) {
			if(pipe == '-' || pipe == '+' || pipe == '1' || pipe == '2') {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		
		map = new char[R][C];
		for(int i = 0; i < R; i++) {
			String s = bf.readLine();
			for(int j = 0; j < C; j++) {
				char c = s.charAt(j);
				map[i][j] = c;
			}
		}
		
		int breakx = 0;
		int breaky = 0;
		List<Integer> direction = new ArrayList<>(); 
		boolean find = false;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '.') {
					int count = 0;
					direction = new ArrayList<>();
					for(int k = 0; k < 4; k++) {
						int newx = j + dx[k];
						int newy = i + dy[k];
						if(newx >= 0 && newx < C && newy >= 0 && newy < R) {
							if(check(map[newy][newx], k)) {
								count++;
								direction.add(k);
							}
						}
					}
					if(count >= 2) {
						breakx = j;
						breaky = i;
						find = true;
						break;
					}
				}
				if(find) {
					break;
				}
			}
			if(find) {
				break;
			}
		}
		char newc = '1';
		if(direction.size() == 4) {
			newc = '+';
		}else {
			int first = direction.get(0);
			int second = direction.get(1);
			if(first == 0) {
				if(second == 1) {
					newc = '2';
				}else if(second == 2) {
					newc = '|';
				}else if(second == 3) {
					newc = '3';
				}
			}else if(first == 1) {
				if(second == 0) {
					newc = '2';
				}else if(second == 2) {
					newc = '1';
				}else if(second == 3) {
					newc = '-';
				}
			}else if(first == 2) {
				if(second == 1) {
					newc = '1';
				}else if(second == 2) {
					newc = '|';
				}else if(second == 3) {
					newc = '4';
				}
			}else if(first == 3) {
				if(second == 0) {
					newc = '3';
				}else if(second == 1) {
					newc = '-';
				}else if(second == 2) {
					newc = '4';
				}
			}
		}
		
		
		
		System.out.println((breaky+1) + " " + (breakx+1) + " " + newc);
		bf.close();
	}
}
