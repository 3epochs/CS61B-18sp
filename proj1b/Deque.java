public interface Deque<T> {
    /** add item to the front of the deque. */
    public void addFirst(T item);

    /** add item to the back of the deque. */
    public void addLast(T item);

    /** check whether a deque is empty. */
    default public boolean isEmpty() {
        return size() == 0;
    };

    /** return the size of deque. */
    public int size();

    /** Prints the entire deque. */
    public void printDeque();

    /** remove the first item in the deque. */
    public T removeFirst();

    /** remove the last item in the deque. */
    public T removeLast();

    /** get the index-th item in the deque. */
    public T get(int index);
}
