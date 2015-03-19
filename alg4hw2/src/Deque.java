import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Igal Israilevich
 * 
 */

public class Deque<Item> implements Iterable<Item> {

	private Node<Item> first; // beginning of queue
	private Node<Item> last; // end of queue
	private int N; // size of deque

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;
	}

	// construct an empty deque
	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return N == 0;
	}

	// return the number of items on the deque
	public int size() {
		return N;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("null item");
		}
		Node<Item> oldFirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldFirst;

		if (isEmpty()) {
			last = first;
		} else {
			oldFirst.prev = first;
		}
		N++;
	}

	// add the item to the end
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("null item");
		}
		Node<Item> oldLast = last;
		last = new Node<Item>();
		last.item = item;
		last.next = null;
		if (isEmpty()) {
			first = last;
		} else {
			oldLast.next = last;
			last.prev = oldLast;
		}
		N++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty deque");
		}
		Item retItem = first.item;

		first = first.next;
		if (first != null) {
			first.prev = null;
		} else {
			last = first;
		}
		N--;
		return retItem;
	}

	// remove and return the item from the end
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("empty deque");
		}
		Item retItem = last.item;
		last = last.prev;
		if (last != null) {
			last.next = null;
		} else {
			first = last;

		}
		N--;
		return retItem;
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node<Item> current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	private void print() {
		for (Item item : this) {
			System.out.print(item + ",");
		}
		System.out.println("-");
	}

	// unit testing
	public static void main(String[] args) {
		Deque<String> q = new Deque<String>();
		Deque<Integer> qInt = new Deque<Integer>();
		qInt.addFirst(100);
		qInt.addFirst(101);
		qInt.addFirst(102);
		qInt.addFirst(103);
		qInt.addFirst(104);
		qInt.addLast(105);
		qInt.print();
		qInt.removeLast();
		qInt.print();
		qInt.removeFirst();
		q.addFirst("b");
		q.addFirst("c");
		q.addFirst("d");
		qInt.removeLast();
		qInt.removeLast();
		qInt.removeLast();
		qInt.removeLast();
		qInt.removeLast();
		qInt.removeLast();
		qInt.print();
		q.addLast("A");
		q.addFirst("a");

		q.addLast("A");
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();

		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.removeFirst();
		q.print();
		q.addLast("B");
		q.print();
		q.addFirst("c");
		q.addFirst("d");
		q.print();
	}

}
