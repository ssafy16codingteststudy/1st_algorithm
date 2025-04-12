import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Tree {
	Tree left;
	char value;
	Tree right;

	Tree(char value) {
		this.value = value;
	}
}

public class Main {
	private static Tree[] trees = new Tree[27];
	private static Queue<Character> answer = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < 26; i++) {
			trees[i] = new Tree((char)(i + 'A'));
		}

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String parent = st.nextToken();
			String leftChild = st.nextToken();
			String rightChild = st.nextToken();

			if (!leftChild.equals("."))
				trees[parent.charAt(0) - 'A'].left = trees[leftChild.charAt(0) - 'A'];
			if (!rightChild.equals("."))
				trees[parent.charAt(0) - 'A'].right = trees[rightChild.charAt(0) - 'A'];
		}

		pre(0);
		answer.offer('\n');
		in(0);
		answer.offer('\n');
		post(0);
		answer.offer('\n');
		
		while (!answer.isEmpty()) {
			bw.write(answer.poll());
		}
		bw.flush();

		bw.close();
	}

	public static void pre(int index) {
		answer.offer(trees[index].value);
		if (trees[index].left != null)
			pre(trees[index].left.value - 'A');
		if (trees[index].right != null)
			pre(trees[index].right.value - 'A');
	}

	public static void in(int index) {
		if (trees[index].left != null)
			in(trees[index].left.value - 'A');
		answer.offer(trees[index].value);
		if (trees[index].right != null)
			in(trees[index].right.value - 'A');
	}

	public static void post(int index) {
		if (trees[index].left != null)
			post(trees[index].left.value - 'A');
		if (trees[index].right != null)
			post(trees[index].right.value - 'A');
		answer.offer(trees[index].value);
	}
}