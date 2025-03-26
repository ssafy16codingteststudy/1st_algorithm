import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		String [] title = new String[N];
		int [] score = new int[N];
		int check = 0;
		// 시간 줄이기
		StringBuilder sb = new StringBuilder();
		
		// 중복 값 안 받기 
		while(check < N) {
			str = br.readLine().split(" ");
			if(check != 0) {
				if(Integer.parseInt(str[1]) == score[check-1]) {
					N--;
				} else {
					title[check] = str[0];
					score[check] = Integer.parseInt(str[1]);
					check++;
				}
			} else {
				title[check] = str[0];
				score[check] = Integer.parseInt(str[1]);
				check++;
			}
		}
		
		
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 이분탐색
			int left = 0;
			int right = N - 1;
			int mid = (N-1)/2;

			while (left <= right) {
				mid = (left + right) / 2;
				if (score[mid] < num) left = mid + 1;
				else if (score[mid] > num) right = mid - 1;
				else break;
			}
			mid = (score[mid] < num) ? mid+1 : mid;
			sb.append(title[mid]).append("\n");
			
		}
		System.out.println(sb.toString());
	}
}

