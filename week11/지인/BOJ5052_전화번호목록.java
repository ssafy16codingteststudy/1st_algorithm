import java.io.*;
import java.util.*;


class Node {
	int key;
	String data;
	List<Node> children = new ArrayList<>();
	
	public Node(int key) {
		this.key = key;
	}
	public Node(int key, String data) {
		this.key = key;
		this.data = data;
	}
	
	public void addData(String data) {
		this.data = data;
	}
	
	public void addChild(Node next) {
		children.add(next);
	}
}

public class BOJ5052_전화번호목록 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int t = Integer.parseInt(st.nextToken());
    	
    	for (int loop=0;loop<t;loop++) {
    		st = new StringTokenizer(br.readLine());
    		int N = Integer.parseInt(st.nextToken());
    		
    		Map<Integer, Node> roots = new HashMap<>();
    		boolean ilgwansung = true;
    		
    		for (int i=0;i<N;i++) {
    			st = new StringTokenizer(br.readLine());
        		String input = st.nextToken();
        		Node now = null;
        		
        		int key = input.charAt(0) - '0';
				roots.putIfAbsent(key, new Node(key));
				now = roots.get(key);
        		
        		for (int num = 0; num < input.length(); num++) {
        			//다음거 다음거 넣기.
        			boolean put = false;
        			if (now.data != null) {
        				ilgwansung = false;
        				break;
        			}
        			
        			if (num != 0) {
        				for (Node next: now.children) {
            				if (next.key == input.charAt(num) - '0') {
            					now = next;
            					put = true;
            					break;
            				}
            			}
        				if (!put) {
        				Node next = new Node(input.charAt(num) - '0');
        				now.children.add(next);
        				now = next;
        				}
        			}
        		}
        		if (ilgwansung) {
        			if (now.data != null)
        				ilgwansung = false;
        			now.addData(input);
        			if (now.children.size() != 0)
        				ilgwansung = false;
        		}
    		}
    		
    		
    		if (ilgwansung)
    			System.out.println("YES");
    		else
    			System.out.println("NO");
    		
    	}

    }
}