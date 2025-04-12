import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N; //원소의 개수, 1~200,000
	static int M; //처음 등장한 위치를 찾을 숫자의 개수, 1~200,000
	static int[] arr; //N개의 원소, -1,000,000,000~1,000,000,000
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);
//		for(int i=0; i<N; i++) {
//			System.out.print(arr[i] + " ");
//		}
		for(int i=0; i<M; i++) {
			int t = Integer.parseInt(br.readLine());
			sb.append(solve(0, N-1, t)).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
	public static int solve(int start, int end, int target) {
		if(start > end) return -1;
		
		int mid = (start + end)/2;
		if(target == arr[mid]  ) { //미드에 있어도 더 왼쪽에 존재할 가능성.
			int left = solve(start, mid-1, target); //미드보다 왼쪽을 무조건 찾고.
			return (left == -1) ? mid : left;
		} else if (target < arr[mid]){
			return solve(start, mid-1, target);
		} else {
			return solve(mid + 1, end, target);
		}
	}
}
