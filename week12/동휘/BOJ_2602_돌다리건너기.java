package April_week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2602_돌다리건너기 {
	
	// count[][][] => [다리의 y좌표][다리의 x좌표][현재까지 완성한 비밀번호의 길이(앞에서부터)]
	// count[n][m][k] => 다리의(m,n)의 위치해 있을 때 비밀번호를 k번째 자리까지 만든 경우의 수
	// 경우 1 - 현재 위치의 문자와 내가 필요한 문자가 일치하지 않을 때 -> 필요없는 문자기 때문에 앞으로 직진
	//	count[j][i][k] = count[j][i-1][k]
	// 경우 2 - 현재 위치의 문자와 내가 필요한 문자가 일치할 때 -> 필요한 문자기 때문에 대각선에서 넘어올 필요 있음
	// 경우 2-1 - 근데 그 문자가 비밀번호의 첫 문자라면 -> 새로 시작해야 함
	// count[j][i][k] = count[j][i-1][k] + 1;
	// 경우 2-2 - 아니라면 대각선에서 전꺼까지 만든 경우의 수 더해야 함
	// count[j][i][k] = count[j][i-1][k] + count[(j+1)%2][i-1][k-1]; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String word = bf.readLine();
		List<String> words = new ArrayList<>();
		words.add(bf.readLine());
		words.add(bf.readLine());
		int size = words.get(0).length();
		int[][][] count = new int[2][size+1][word.length()];
		for(int i = 1; i <= size; i++) {
			for(int j = 0; j < 2; j++) {
				for(int k = 0; k < word.length(); k++) {
					if(words.get(j).charAt(i-1) == word.charAt(k)) {
						if(k == 0) {
							count[j][i][k] = count[j][i-1][k] + 1; 
						}else {
							count[j][i][k] = count[j][i-1][k] + count[(j+1)%2][i-1][k-1]; 
						}
					}else {
						count[j][i][k] = count[j][i-1][k];
					}
				}
			}
		}
		int answer = count[0][size][word.length()-1] + count[1][size][word.length()-1];

		System.out.println(answer);
		
		bf.close();
	}
}
