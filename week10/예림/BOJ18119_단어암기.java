import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken()); // 단어 수
        int M = Integer.parseInt(st.nextToken()); // 쿼리 수

        int[] words = new int[N];  // int[] 배열로 단어 마스킹 처리
        int know = (1 << 26) - 1;  // 처음엔 알파벳 전부 암기 상태

        // 단어 입력 및 비트마스크 변환 (자음 + 모음 모두 포함)
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (char c : word.toCharArray()) {
                words[i] |= (1 << (c - 'a'));
            }
        }

        // 쿼리 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            int bit = (1 << (c - 'a'));

            if (cmd == 1) {
                know &= ~bit;  // 알파벳 잊기
            } else {
                know |= bit;   // 알파벳 기억해내기
            }

            int count = 0;
            for (int word : words) {
                if ((know & word) >= word) {  // 포함 여부 비교
                    count++;
                }
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }
}
