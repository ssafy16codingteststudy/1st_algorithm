package week10.일우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ23300웹브라우저2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stackPrev = new Stack<>();
        Stack<Integer> stackNext = new Stack<>();
        int now = 0;

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int Q = Integer.parseInt(input[1]);

        for (int i = 0; i < Q; i++) {
            input = br.readLine().split(" ");
            String command = input[0];
            if (command.equals("B")) {
                if(!stackPrev.isEmpty()) {
                    stackNext.add(now);
                    now = stackPrev.pop();
                }
            } else if(command.equals("F")) {
                if(!stackNext.isEmpty()) {
                    stackPrev.add(now);
                    now = stackNext.pop();
                }
            } else if(command.equals("A")) {
                //앞으로 가기 공간 페이지 삭제
                stackNext.clear();
                //현재 페이지를 뒤로 기기 공간에 추가하는데, 처음으로 접속한 경우(now=0)에는 추가하지 않음
                if(now != 0)
                    stackPrev.add(now);

                now = Integer.parseInt(input[1]);
            } else if (command.equals("C")) {
                if(!stackPrev.isEmpty()) {
                    Stack<Integer> newStack = new Stack<>();
                    //처음 페이지
                    newStack.add(stackPrev.get(0));
                    for(Integer n : stackPrev) {
                        // 연속된 페이지가 아닐경우에만 newStack에 추가
                        if (!newStack.peek().equals(n)) {
                            newStack.add(n);
                        }
                    }
                    stackPrev = newStack;
                }
            }
        }
        System.out.println(now);

        if(!stackPrev.isEmpty()) {
            while (!stackPrev.isEmpty()) {
                System.out.print(stackPrev.pop() + " ");
            }
            System.out.println();
        } else System.out.println(-1);

        if(!stackNext.isEmpty()) {
            while (!stackNext.isEmpty()) {
                System.out.print(stackNext.pop() + " ");
            }
            System.out.println();
        } else System.out.println(-1);
    }
}
