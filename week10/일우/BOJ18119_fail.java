package week10.일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BOJ18119_fail {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        List<String> words = new ArrayList<>();
        Map<String, Boolean> memory = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            for (String c : word.split("")) {
                memory.put(c, true);
            }
            words.add(word);
        }

        for (int i = 0; i < M; i++) {
            input = br.readLine().split(" ");
            String word = input[1];
            // 잊어버리기
            if (input[0].equals("1")) {
                memory.put(word, false);
            } else if (input[0].equals("2")) { // 기억하기
                memory.put(word, true);
            }

            // 출력
            int ans = 0;
            for (String w : words) {
                boolean remember = true;
                for (Map.Entry<String, Boolean> e : memory.entrySet()) {
                    if (!e.getValue() && w.contains(e.getKey())) { //해당 단어에 잊어버린 단어가 포함되어있다면
                        remember = false; //기억못함
                        break;
                    }
                }
                if(remember) ans++;
            }
            System.out.println(ans);
        }
    }
}
