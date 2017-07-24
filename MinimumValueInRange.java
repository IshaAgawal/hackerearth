import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*To find min value in each range 
 * Problem statement - https://www.hackerearth.com/problem/algorithm/everything-falling-apart-0f44f3df/description/
 * */
class Pair {

	int max;
	int maxIndex;
	int s;
	int e;

	Pair(int max, int maxIndex, int s, int e) {
		this.max = max;
		this.maxIndex = maxIndex;
		this.s = s;
		this.e = e;
	}

	@Override
	public String toString() {
		return "Pair [max=" + max + ", maxIndex=" + maxIndex + ", s=" + s
				+ ", e=" + e + "]";
	}

}

public class MinimumValueInRange {

	static void find(Pair[][] mat, int s, int e, int n) {

		Queue<Pair> q = new LinkedList<Pair>();

		q.add(mat[s][e]);

		int level = 1, curLevel = 0, sum = 0;

		while (!q.isEmpty()) {

			Pair p = q.poll();

			int index = p.maxIndex;

			if (index > 0 && index < n && p.s < index) {
				q.add(mat[s][index - 1]);
				curLevel++;
			}

			if (index >= 0 && index < n - 1 && index < p.e) {
				q.add(mat[index + 1][e]);
				curLevel++;
			}

			level--;

			sum += p.max;
			if (level == 0) {
				System.out.println(sum);
				level = curLevel;
				curLevel = 0;
				sum = 0;
			}
		}

	}

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		int a[] = new int[n];

		for (int i = 0; i < n; i++) {

			a[i] = in.nextInt();
		}

		Pair mat[][] = new Pair[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {

				if (i == j) {
					mat[i][j] = new Pair(a[i], i, i, j);
					continue;
				}
				if (mat[i][j - 1].max > a[j]) {
					mat[i][j] = new Pair(mat[i][j - 1].max,
							mat[i][j - 1].maxIndex, i, j);
				} else {
					mat[i][j] = new Pair(a[j], j, i, j);
				}
			}
		}

		find(mat, 0, n - 1, n);

	}
}
