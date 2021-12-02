package main;

public class MSD {
	private static int R = 256;
	private static String[] aux;

	public static int charAt(String s, int d) {
		if (d < s.length()) {
			return s.charAt(d);
		} else {
			return -1;
		}
	}

	public static void MSD_sort(String[] a) {
		int N = a.length;
		aux = new String[N];
		MSD_sort1(a, 0, N - 1, 0);
	}

	private static void MSD_sort1(String[] a, int lo, int hi, int d) {
		if (lo >= hi) {
			return;
		}
		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++) {
			count[charAt(a[i], d) + 2]++;
		}
		for (int r = 0; r < R + 1; r++) {
			count[r + 1] += count[r];
		}
		for (int i = lo; i <= hi; i++) {
			aux[count[charAt(a[i], d) + 1]++] = a[i];
		}
		for (int i = lo; i <= hi; i++) {
			a[i] = aux[i - lo];
		}
		for (int r = 0; r < R; r++) {
			MSD_sort1(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
		}
	}

	// Driver Code

}