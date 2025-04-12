import java.io.*;
import java.util.*;

public class BOJ_4195친구네트워크 {

    /**
     * 4195  친구 네트워크
     * 정수 값을 사용한 일반적인 유니온 파인드 문제와 달리, 문자열을 사용해야 함 -> Map 자료구조 사용
     * 친구 네트워크에 포함된 친구 수를 지속적으로 추적해야 함 -> 대표자를 기준으로 친구 수를 관리할 Map 사용
     */
    private static Map<String, String> parents;     // 대표자 관리 Map
    private static Map<String, Integer> friendSize; // 각 대표의 친구 수 관리 Map
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            parents = new HashMap<String, String>();
            friendSize = new HashMap<String, Integer>();

            int f = Integer.parseInt(br.readLine());
            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();

                // 처음 들어온 친구라면, 초기 설정 해줌
                // 초기 설정 - 대표는 자기 자신, 자신의 친구 수는 1
                if(!parents.containsKey(a)) {
                    parents.put(a, a);
                    friendSize.put(a, 1);
                }

                if(!parents.containsKey(b)) {
                    parents.put(b, b);
                    friendSize.put(b, 1);
                }

                // 두 친구에 대해 유니온 연산
                union(a, b);                
                // 두 친구의 대표를 기준으로, 그 네트워크의 친구의 수를 구함
                sb.append(friendSize.get(find(a))).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static String find(String node) {
        if(parents.get(node) == node) {
            return node;
        }

        // return parents[node] = find(parents[node]);
        parents.put(node, find(parents.get(node)));        
        return parents.get(node);
    }

    private static boolean union(String a, String b) {
        String aRoot = find(a);
        String bRoot = find(b);

        if(aRoot == bRoot) {
            return false;
        }

        int aRootSize = friendSize.get(aRoot);
        int bRootSize = friendSize.get(bRoot);

        if(aRootSize > bRootSize) {
            // 대표자의 친구 수 갱신 -> 각대표자끼리의 친구의 수를 더해 갱신해야 함
            friendSize.put(aRoot, aRootSize + bRootSize);     
            // parents[bRoot] = aRoot;    			      
            parents.put(bRoot, aRoot);
        } else {
            friendSize.put(bRoot, aRootSize + bRootSize);                       
            parents.put(aRoot, bRoot);
        }

        return true;
    }
}