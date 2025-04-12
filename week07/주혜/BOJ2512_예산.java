import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException {
		
		List<Integer> nums = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		int big = 0;
		int test = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			nums.add(Integer.parseInt(st.nextToken()));
			if (big < nums.get(i)) big =nums.get(i);
			test += nums.get(i);
		}
		
		st = new StringTokenizer(br.readLine());
		int max = Integer.parseInt(st.nextToken());
		
		if (test <= max) {
			System.out.println(big);
			return;
		}

		int left = 0;
		int right = max;
		int mid = max/2 + 1;
			
		while (left <= right) {
			mid = (left + right) / 2;
			int check = 0;
			for (int i = 0; i < nums.size(); i++) {
				if(nums.get(i) < mid) check += nums.get(i);
				else check += mid;
			}
			
			
			if (check < max) left = mid + 1;
			else if (check > max) right = mid - 1;
			else break;
		}
		
		
		int check = 0;
		for (int i = 0; i < nums.size(); i++) {
			if(nums.get(i) < mid) check += nums.get(i);
			else check += mid;
		}
		if (check > max) mid--;

		System.out.println(mid);
		
	}
}
