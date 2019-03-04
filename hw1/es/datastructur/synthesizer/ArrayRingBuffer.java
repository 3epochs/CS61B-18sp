package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        //  Create new array with capacity elements.
        //  first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        //  Enqueue the item. Don't forget to increase fillCount and update
        //  last.
        if (fillCount == capacity) {
            throw new RuntimeException("Ring buffer overflow");
        }
        // update last pointer
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        //  Dequeue the first item. Don't forget to decrease fillCount and
        //  update first.
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T tmp = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount -= 1;

        return tmp;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    public Iterator<T> iterator() {
        return new bufferIterator();
    }

    private class bufferIterator implements Iterator<T> {
        private int count = 0;
        @Override
        public boolean hasNext() {
            if (count == capacity()) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            count += 1;
            return dequeue();
        }
    }
}
/*    public Iterator<T> iterator() {
        return new bufferIterator();
    }

    private class bufferIterator implements Iterator<T> {
        private int count = 0;

        @Override
        public boolean hasNext() {
            if (count == capacity()) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            count += 1;
            return dequeue();
        }
    }
}*/

