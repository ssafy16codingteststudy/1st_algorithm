import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ5052_전화번호목록 {

    // 5052 전화번호 목록
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Trie trie = new Trie();
            boolean flag = true;

            for (int i = 0; i < n; i++) {
                String phone = br.readLine();
                if (flag) {
                    if (!trie.insertAndCheck(phone)) {
                        flag = false;
                    }
                }
            }

            sb.append(flag ? "YES\n" : "NO\n");
        }

        System.out.println(sb);
    }

    private static class Trie {
        // 숫자 0~9 → 총 10개
        private static final int SIZE = 10;
        // 문자 인덱스 계산용 오프셋
        private static final char OFFSET = '0';

        private static class TrieNode {
            private final TrieNode[] children = new TrieNode[SIZE];
            // 이 노드까지 완전한 번호가 존재함을 표시
            private boolean isTerminal= false;
        }

        // 트라이 전체를 대표하는 루트 노드
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public boolean insertAndCheck(String str) {
            TrieNode node = root;

            // 1) 문자열을 한 글자씩 따라 내려가며
            for (char c : str.toCharArray()) {
                int idx = c - OFFSET;

                // (A) 내려오는 도중에 이미 어떤 번호가 끝났다면
                //     그 번호가 현재 번호의 접두사 → 충돌
                if(node.isTerminal) {
                    return false;
                }

                // 자식이 없으면 새로 생성
                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }
                node = node.children[idx];
            }

            // 2) 삽입 지점까지 다 왔는데, 이 지점에 이미 자식이 있다면
            //    현재 번호가 이전에 삽입된 번호의 접두사 → 충돌
            for (TrieNode child : node.children) {
                if (child != null) {
                    return false;
                }
            }

            node.isTerminal = true;
            return true;
        }
    }
}