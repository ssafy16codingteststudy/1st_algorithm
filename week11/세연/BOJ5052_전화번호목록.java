package week11.세연;

import java.util.*;
import java.io.*;

public class BOJ5052_전화번호목록 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            String answer = "YES\n";
            String[] inputs = new String[N];
            for (int n = 0; n < N; n++) {
                inputs[n] = br.readLine();
            }
            Arrays.sort(inputs);
            for (int i = 0; i < N-1; i++){
                if (inputs[i+1].startsWith(inputs[i])) {
                    answer = "NO\n";
                    break;
                }
            }
            sb.append(answer);
        }
        System.out.println(sb);
    }
}




// // NumberForat 런타임 에러 발생 -> 0으로 시작하는 전화번호
// public class BOJ5052_전화번호목록 {
//     public static void main(String[] args) throws Exception{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringBuilder sb = new StringBuilder();

//         int T = Integer.parseInt(br.readLine());

//         for (int t = 1; t <= T; t++) {
//             int N = Integer.parseInt(br.readLine());
//             String answer = "YES\n";
//             String[] inputs = new String[N];
//             for (int n = 0; n < N; n++) {
//                 inputs[n] = br.readLine();
//             }
//             Arrays.sort(inputs);
//             for (int i = 0; i < N-1; i++){
//                 if (inputs[i].length() > inputs[i+1].length()) continue;
//                 int a = Integer.parseInt(inputs[i]);
//                 int b = Integer.parseInt(inputs[i+1]) / (int)Math.pow(10, inputs[i+1].length() - inputs[i].length());
//                 if (a == b) {
//                     answer = "NO\n";
//                     break;
//                 }
//             }
//             sb.append(answer);
//         }
//         System.out.println(sb);
//     }
// }
