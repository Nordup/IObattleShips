import java.util.Arrays;

class toString {
	public static void main(String[] args) {
		int[][]	a;

		a = new int[][] {{1, 2}, {1, 2}};

		System.out.println(Arrays.toString(a[0]));
		System.out.println(a[0].toString());
	}
}