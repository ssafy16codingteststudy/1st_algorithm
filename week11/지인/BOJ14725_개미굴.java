import java.io.*;
import java.util.*;


class Node {
	String data;
	int depth = 0;
	List<Node> children = new ArrayList<>();
	
	public Node(String data) {
		this.data = data;
	}
	public Node(String data ,int depth) {
		this.data = data;
		this.depth = depth;
	}
	
	public void addData(String data) {
		this.data = data;
	}
	
	public void addChild(Node next) {
		if (next != null)
			children.add(next);
	}

	
}


public class BOJ14725_개미굴 {
	static StringBuilder print(Node now) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<now.depth;i++)
			sb.append("--");
		sb.append(now.data).append("\n");
		if (now.children.size() == 0)
			return sb;
		else {
			List<Node> sortedChildren = now.children;
			Collections.sort(sortedChildren, Comparator.comparing(a -> String.valueOf(a.data)));
			for (Node next: sortedChildren) {
				if (next != null)
					sb.append(print(next));
			}
		}
		return sb;
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int t = Integer.parseInt(st.nextToken());
    	Map<String, Node> nodes = new HashMap<>();
    	
    	for (int loop=0;loop<t;loop++) {
    		st = new StringTokenizer(br.readLine());
    		int len = Integer.parseInt(st.nextToken());
    		
    		Node now = null;
    		for (int i=0;i<len;i++) {
    			String data = st.nextToken();
    			if (i == 0) {
    				nodes.putIfAbsent(data, new Node(data, 0));
    				now = nodes.get(data);
    			} else {
    				boolean put = false;
    				for (Node next: now.children) {
    					if (next.data.equals(data)) {
    						now = next;
    						put = true;
    						break;
    					}
    				}
    				if (!put) {
    					Node next = new Node(data, i);
    					now.addChild(next);
    					now = next;
    				}
    			}
    		}
    	}
    	
    	List<String> sortedKey = new ArrayList<>(nodes.keySet());
    	Collections.sort(sortedKey);
    	
    	StringBuilder sb = new StringBuilder();
    	for (String key: sortedKey) {
    		Node now = nodes.get(key);
    		sb.append(print(now));
    	}
    	System.out.println(sb);

    }
}