package es.datastructur.synthesizer;

public interface BoundedQueue<T> {

    int capacity();     // return size of the buffer
    int fillCount();    // return number of items currently in the buffer
    void enqueue(T x);  // add item x to the end
    T dequeue();        // delete and return item from the front
    T peek();           // return item from the front

    /** default method to check whether the buffer is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** default method to check whether the buffer is full. */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}
