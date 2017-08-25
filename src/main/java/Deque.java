import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (first == null) {
            first = new Node<>(item, null, null);
            last = first;
        } else {
            Node<Item> next = first;
            first = new Node<>(item, null, next);
            next.prev = first;
        }
        size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        if (last == null) {
            first = new Node<>(item, null, null);
            last = first;
        } else {
            Node<Item> prev = last;
            last = new Node<>(item, prev, null);
            prev.next = last;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {

        if (first == null) {
            throw new NoSuchElementException();
        }

        Item item = first.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
            if (first == last) {
                last.prev = null;
                first.next = null;
            }
        }
        size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException();
        }

        Item item = last.item;
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
            if (first == last) {
                last.prev = null;
                first.next = null;
            }
        }

        size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator<>(first);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        Node<Item> current = first;
        while (current != null) {
            builder.append(current.item);
            current = current.next;
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("Hello");
        deque.addLast(" World");
        deque.addFirst("Whats up? ");
        deque.addLast("!");
        deque.addLast("!");
        deque.addFirst("!");
        deque.removeFirst();
        deque.removeLast();

        System.out.println(deque);
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> prev;
        private Node<Item> next;

        Node(Item item, Node<Item> prev, Node<Item> next) {
            if (item == null) {
                throw new IllegalArgumentException();
            }
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Item getItem() {
            return item;
        }

        public Node<Item> getPrev() {
            return prev;
        }

        public Node<Item> getNext() {
            return next;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public void setPrev(Node<Item> prev) {
            this.prev = prev;
        }

        public void setNext(Node<Item> next) {
            this.next = next;
        }
    }

    private static class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> item;

        DequeIterator(Node<Item> node) {
            this.item = node;
        }

        public boolean hasNext() {
            return item != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<Item> current = item;
            item = item.next;
            return current.item;
        }
    }
}
