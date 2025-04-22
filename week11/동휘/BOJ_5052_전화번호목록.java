package April_week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_5052_전화번호목록 {
	
	public static class Node{
		HashMap<Character, Node> child;
		boolean isEnd;
		Node(){
			this.child = new HashMap<>();
			this.isEnd = false;
		}
		
		HashMap<Character, Node> getChildNode(){
			return this.child;
		}
		
		boolean isLastChar() {
			return this.isEnd;
		}
	}
	
	public static class Trie{
		Node rootNode;
		Trie(){
			rootNode = new Node();
		}
		
		public void insert(String word) {
			Node newNode = this.rootNode;
			for(int i = 0; i < word.length(); i++) {
				newNode = newNode.getChildNode().computeIfAbsent(word.charAt(i), chr -> new Node());
			}
			newNode.isEnd = true;
		}
		
		public boolean contains(String word) {
			Node curNode = this.rootNode;
			
			for(int i = 0; i < word.length(); i++) {
				char c = word.charAt(i);
				Node node = curNode.getChildNode().get(c);
				if(node == null) {
					return false;
				}
				curNode = node;
				if(node.isEnd) {
					return true;
				}
			}
			return !curNode.getChildNode().isEmpty();
		}
	}
	

	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(bf.readLine());
		for(int test = 0; test < t; test++) {
			int n = Integer.parseInt(bf.readLine());
			List<String> list = new ArrayList<>();
			for(int i = 0; i < n; i++) {
				String num = bf.readLine();
				list.add(num);
			}
			Collections.sort(list);
			Trie trie = new Trie();
			boolean hasWord = false;
			for(String s : list) {
				if(trie.contains(s)) {
					sb.append("NO").append("\n");
					hasWord = true;
					break;
				}
				trie.insert(s);
			}
			if(!hasWord) {
				sb.append("YES").append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		bf.close();
	}
}


