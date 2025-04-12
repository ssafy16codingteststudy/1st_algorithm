import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ30646_최대합순서쌍의개수 {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[] arr = new long[n + 1];						// 구간 합을 구할 합 배열 생성
		Map<Integer, List<Integer>> map = new HashMap<>();	// 해당 요소의 인덱스를 저장할 map 생성
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int value = Integer.parseInt(st.nextToken());

			arr[i + 1] = arr[i] + value;	// 구간합 구하기
			
			if(map.containsKey(value)) {
				map.get(value).add(i);
			} else {
				// map.put(value, new ArrayList<>(i)); 이상하게 이렇게 하면 데이터가 안들어갔다. 왜?
				map.put(value, new ArrayList<>());
				map.get(value).add(i);
			}
		}

		// 쭉 돌면서 최댓값 구하고, 
		long max = 0;
		for (Integer index : map.keySet()) {
			int size = map.get(index).size();
			
			int start = map.get(index).get(0);
			int end = map.get(index).get(size - 1);
			
			// 합의 최댓값 구할 땐 구간 합으로
			max = Math.max(max, arr[end + 1] - arr[start]);
		}

		// 한 번 더 돌면서 그 값과 같은 결과 카운트
		long count = 0;
		for (Integer index : map.keySet()) {
			int size = map.get(index).size();
			
			int start = map.get(index).get(0);
			int end = map.get(index).get(size - 1);

			if(max == arr[end + 1] - arr[start]) {
				count++;
			}
		}		
		
		System.out.println(max + " " + count);
	}
}
