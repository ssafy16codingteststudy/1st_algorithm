package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ4195 {
    static int T, N;
    static Map<String, String> parents;
    static Map<String, Integer> friendSize; //각 대표의 친구수 관리
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            parents = new HashMap<>();
            friendSize = new HashMap<>();

            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                String a = st.nextToken();
                String b = st.nextToken();

                // 처음 들어온 친구라면 본인을 부모노드로 설정
                if(!parents.containsKey(a)){
                    parents.put(a, a);
                    friendSize.put(a, 1);
                }
                if(!parents.containsKey(b)){
                    parents.put(b, b);
                    friendSize.put(b, 1);
                }

                union(a,b);

                sb.append(friendSize.get(find(a))).append("\n");
            }

        }
        System.out.println(sb);
    }

    static String find(String a) {
        if(parents.get(a).equals(a)) return a;

        parents.put(a, find(parents.get(a)));
        return parents.get(a);
    }

    static boolean union(String a, String b) {
        String ar = find(a);
        String br = find(b);

        if(ar.equals(br)) return false;

        int aSize = friendSize.get(ar); //A친구의 수
        int bSize = friendSize.get(br); //B친구의 수

        if(aSize > bSize) {
            //대표자 친구의 수를 갱신하고 -> 각 대표자끼리의 친구 수를 더하기
            friendSize.put(ar, aSize+bSize);
            parents.put(br, ar);
        } else {
            friendSize.put(br, aSize+bSize);
            parents.put(ar, br);
        }

        return true;
    }
}
