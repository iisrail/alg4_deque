/**
 * Subset.java
 * Feb 15, 2015
 * Igal Israilevich
 */

/**
 * @author Igal Israilevich
 * comment from web
 * comment from eclipse
 */
public class Subset {

	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> ranQueue = new RandomizedQueue<>();

		String[] strings = StdIn.readAllStrings();
		for (String str : strings) {
			ranQueue.enqueue(str);
		}

		for (int i = 0; i < k; i++) {
			System.out.println(ranQueue.dequeue());
		}
	}
}
