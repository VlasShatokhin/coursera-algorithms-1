import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private static final int INITIAL_CAPACITY = 10;
    private Item[] queue;

    /**
     * Number of items in the queue.
     */
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = newArray(INITIAL_CAPACITY);
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == queue.length) {
            resize(queue.length * 2);
        }
        queue[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }

        int index = StdRandom.uniform(size);
        Item dequed = queue[index];

        if (index == size - 1) {
            queue[index] = null;
        } else {
            queue[index] = queue[size - 1];
            queue[size - 1] = null;
        }
        if (size > 0 && size == queue.length / 4) resize(queue.length / 2);

        size--;
        return dequed;
    }

    // return (but do not remove) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        return queue[StdRandom.uniform(size)];
    }

    private Item[] newArray(int capacity) {
        return (Item[]) new Object[capacity];
    }

    private void resize(int capacity) {
        Item[] copy = newArray(capacity);
        int index = size - 1;
        while (index >= 0) {
            copy[index] = queue[index];
            index--;
        }
        queue = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator<>(this);
    }

    // unit testing (optional)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("Hello");
        queue.dequeue();


        StringBuilder builder = new StringBuilder();

        while (!queue.isEmpty()) {
            builder.append(queue.dequeue());
        }

        System.out.println(builder);

        queue.enqueue("Hello");

        for (String item: queue) {
            System.out.println(item);
        }
    }

    private static class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private final Item[] queue;
        private final int[] permutationIndex;
        private int index;

        private RandomizedQueueIterator(RandomizedQueue<Item> queue) {
            this.queue = queue.queue;
            this.permutationIndex = StdRandom.permutation(queue.size);
        }

        public boolean hasNext() {
            return index < permutationIndex.length;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (index > permutationIndex.length - 1) {
                throw new NoSuchElementException();
            }
            int queueIndex = permutationIndex[index++];
            return queue[queueIndex];
        }
    }
}