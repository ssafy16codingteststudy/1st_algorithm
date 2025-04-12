import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ21939_문제추천시스템V1 {

    /**
     * 21939. 문제 추천 시스템
     * 전에 용모님이 "B형 치기전에 풀어볼만한 트리셋 문제" 로 말해줬던 문제
     * 용모님 말씀대로 트리셋 api 를 활용하면 어렵지 않게 풀 수 있다.
     * Problem class 를 만들어 우선순위를 고려하도록 해주고,
     * 삭제 연산을 구현하기 위해 문제 번호와 문제를 묶어둔 map 자료 구조를 사용
     *
     * + 문제 추천 시스템 V2 도 있는데 이건 SWEA pro 문제 '성적조회' 와 비슷함
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TreeSet<Problem> treeSet = new TreeSet<>();
        Map<Integer, Problem> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            Problem problem = new Problem(id, level);
            treeSet.add(problem);
            map.put(id, problem);
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();

            if (query.equals("recommend")) {
                int a = Integer.parseInt(st.nextToken());

                if (a == 1) {
                    sb.append(treeSet.last().id);
                } else if (a == -1) {
                    sb.append(treeSet.first().id);
                }
                sb.append("\n");

            } else if (query.equals("add")) {
                int id = Integer.parseInt(st.nextToken());
                int level = Integer.parseInt(st.nextToken());
                Problem problem = new Problem(id, level);
                treeSet.add(problem);
                map.put(id, problem);

            } else if (query.equals("solved")) {
                int id = Integer.parseInt(st.nextToken());
                Problem problem = map.get(id);
                map.remove(problem);
                treeSet.remove(problem);
            }
        }
        System.out.println(sb);
    }

    private static class Problem implements Comparable<Problem> {
        int id;
        int level;

        public Problem(int id, int level) {
            this.id = id;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level) {
                return this.id - o.id;
            }

            return this.level - o.level;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Problem problem = (Problem) o;
            return id == problem.id;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }
}
