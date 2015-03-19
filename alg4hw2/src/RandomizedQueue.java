import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * RandomizedQueue.java
 * Feb 14, 2015
 * Igal Israilevich
 */

/**
 * @author Igal Israilevich
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a; // array of items
	private int N = 0; // number of elements on stack

	// construct an empty randomized queue
	public RandomizedQueue() {
		a = (Item[]) new Object[2];
	}

	// is the queue empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// return the number of items on the queue
	public int size() {
		return N;
	}

	// resize the underlying array holding the elements
	private void resize(int capacity) {
		assert capacity >= N;
		Item[] temp = (Item[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			temp[i] = a[i];
		a = temp;
	}

	/**
	 * Adds the item to this RandomizedQueue.
	 * 
	 * @param item
	 *            the item to add to this RandomizedQueue
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("null item");
		}
		if (N == a.length)
			resize(2 * a.length); // double size of array if necessary
		a[N++] = item; // add item
	}

	/**
	 * Remove and return a random item
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty Randomized Queue");
		}
		int rand = StdRandom.uniform(0, N);
		Item retItem = a[rand];
		a[rand] = a[N - 1];
		a[N - 1] = null;
		N--;
		if (N > 0 && N == a.length / 4) {
			resize(a.length / 2);
		}
		return retItem;
	}

	// return (but do not remove) a random item
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty Randomized Queue");
		}
		int rand = StdRandom.uniform(0, N);
		return a[rand];
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomIterator();
	}

	private class RandomIterator implements Iterator<Item> {
		private int i = 0;
		private Item[] randArr;

		public RandomIterator() {
			randArr = (Item[]) new Object[N];
			randArr = Arrays.copyOf(a, N);
			StdRandom.shuffle(randArr);
		}

		public boolean hasNext() {
			return i < N;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return randArr[i++];
		}
	}

	private void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(a[i] + ",");
		}
		System.out.println("-");
	}

	private void printIterate() {
		for (Item s : this) {
			System.out.print(s + ",");
		}
		System.out.println("--");
	}

	// unit testing
	public static void main(String[] args) {
		RandomizedQueue<String> q = new RandomizedQueue<String>();
		q.enqueue("A");
		q.enqueue("a");
		q.printIterate();
		q.enqueue("b");
		System.out.println("sample " + q.sample());
		q.printIterate();
		q.enqueue("c");
		q.enqueue("d");
		System.out.println("sample " + q.sample());
		q.enqueue("V");
		q.print();
		q.printIterate();
		System.out.println(q.dequeue());
		q.print();
		q.printIterate();
		System.out.println(q.dequeue());
		q.print();
		System.out.println(q.dequeue());
		q.print();
		System.out.println(q.dequeue());
		q.print();

		System.out.println(q.dequeue());
		q.print();

		q.enqueue("B");
		q.print();
		q.enqueue("c");
		q.enqueue("d");
		q.print();
		System.out.println("sample " + q.sample());
	}

}