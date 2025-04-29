import java.util.*;
import java.io.*;

public class BOJ_20529 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < t; i++) {
			boolean isZero = false;
			int result = Integer.MAX_VALUE;
			int n = Integer.parseInt(br.readLine());
			Map<String, Integer> map = new HashMap<>();
			List<String> list = new ArrayList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String[] mbti = new String[n];
			for(int j = 0; j < n; j++) {
				mbti[j] = st.nextToken();
				list.add(mbti[j]);
		        if (map.containsKey(mbti[j])) {
		            map.put(mbti[j], map.get(mbti[j]) + 1);
		            if(map.get(mbti[j]) == 3) {
		            	isZero = true;
		            	System.out.println(0);
		            	break;
		            }
		        } else {
		            map.put(mbti[j], 1);
		        }
			}
			
			if(!isZero) {
				for(int a = 0; a < list.size(); a++) {
					for(int b = a+1; b < list.size(); b++) {
						for(int c = b+1; c < list.size(); c++) {
							String mbtiA = list.get(a);
							String mbtiB = list.get(b);
							String mbtiC = list.get(c);
						
							int dist = getDistance(mbtiA, mbtiB) + getDistance(mbtiB, mbtiC) + getDistance(mbtiC, mbtiA);
							result = Math.min(result, dist);
						}
					}
				}
				System.out.println(result);
			}
		}
	}
	static int getDistance(String a, String b) {
        int dist = 0;
        for (int i = 0; i < 4; i++) {
            if (a.charAt(i) != b.charAt(i)) dist++;  // 글자가 다르면 1 증가
        }
        return dist;
    }

}
