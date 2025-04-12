import java.util.*;

public class Main {
	static String[] result = new String[13];
	
	public static String getString(int count) {
		if (result[count] != null)
			return result[count];
		else {
			String str = getString(count - 1);
			int len = str.length();
			StringBuilder blanks = new StringBuilder();
			for (int i = 0; i< len; i++) {
				blanks.append(" ");
			}
			result[count] = str + blanks + str;
			return result[count];
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		result[0] = "-";
		
		while (true) {
			if (!sc.hasNext())
				break;
			int N = sc.nextInt();
			
			System.out.println(getString(N));
		}
	}	
}



