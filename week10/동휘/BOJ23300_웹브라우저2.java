package april_week2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ23300_웹브라우저2 {
	
	public static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		Stack<Integer> backStack = new Stack<>();
		Stack<Integer> frontStack = new Stack<>();
		int curpage = -1;
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(bf.readLine());
			String s = st.nextToken();
			if(s.equals("B")) {
				if(!backStack.isEmpty()) {
					frontStack.add(curpage);
					curpage = backStack.peek();
					backStack.pop();
				}
			}else if(s.equals("F")) {
				if(!frontStack.isEmpty()) {
					backStack.add(curpage);
					curpage = frontStack.peek();
					frontStack.pop();
				}
			}else if(s.equals("A")) {
				int newPage = Integer.parseInt(st.nextToken());
				if(curpage != -1) {
					backStack.add(curpage);
				}
				curpage = newPage;
				frontStack = new Stack<>();
			}else if(s.equals("C")) {
				Stack<Integer> temp = new Stack<>();
				if(backStack.isEmpty()) {
					continue;
				}
				int nowPage = backStack.peek();
				int previousPage;
				backStack.pop();
				temp.add(nowPage);
				while(!backStack.isEmpty()) {
					previousPage = nowPage;
					nowPage = backStack.peek();
					backStack.pop();
					if(previousPage == nowPage) {
						continue;
					}else {
						temp.add(nowPage);
					}
				}
				while(!temp.isEmpty()) {
					int k = temp.peek();
					temp.pop();
					backStack.add(k);
				}
			}

		}

		sb.append(curpage).append("\n");
		if(backStack.isEmpty()) {
			sb.append(-1);
		}
		while(!backStack.isEmpty()) {
			int k = backStack.peek();
			backStack.pop();
			sb.append(k).append(" ");
		}
		sb.append("\n");
		if(frontStack.isEmpty()) {
			sb.append(-1);
		}else {
			while(!frontStack.isEmpty()) {
				int k = frontStack.peek();
				frontStack.pop();
				sb.append(k).append(" ");
			}
		}

		bw.write(sb.toString());
		
		bw.flush();
		bw.close();
		bf.close();
	}
}
