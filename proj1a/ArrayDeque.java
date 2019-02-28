public class ArrayDeque<T> {
    private int size, head, tail;
    private T[] items;

    /** create an empty list. */
    public ArrayDeque() {
        items =(T[]) new Object[8];
        size = 0;
        tail = 7;
        head = 0;
    }

    /** create a deep copy of items other. */
    public ArrayDeque(ArrayDeque other) {
        items = (T []) new Object[other.items.length];
        System.arraycopy(other,0, items, 0, other.items.length);
        size = other.size;
        head = other.head;
        tail = other.tail;
    }

    /** Resize the underlying array to the target capacity.*/
    private void upResize() {
        T[] tmp = (T []) new Object[items.length * 2];
        System.arraycopy(items, head, tmp, 0, size - head);
        System.arraycopy(items, 0, tmp, size - head, head);
        tail = size - 1;
        head = 0;
        items = tmp;
    }

    private void downResize() {
        T[] tmp = (T []) new Object[items.length / 2];
        if (head + size - 1 < items.length) {
            System.arraycopy(items, head, tmp, 0, size);
        } else {
            System.arraycopy(items, head, tmp, 0, items.length - head);
            System.arraycopy(items, 0, tmp, items.length - head, tail + 1);
        }
        head = 0;
        tail = size - 1;
        items = tmp;
    }

    /** add item to the front.*/
    public void addFirst(T item) {
        if (size == items.length) {
            upResize();
        }
        head = (head + items.length -1) % items.length;
        items[head] = item;
        size += 1;
    }

    /** add items into the back of the list. */
    public void addLast(T item) {
        if (size == items.length) {
            upResize();
        }
        tail = (tail + 1) % items.length;
        items[tail] = item;
        size += 1;
    }

    /** method that check whether an array list is empty. */
    public boolean isEmpty() {
        return size == 0;
    }




/*
    * get the last item.
    public T getLast() {
        return items[size - 1];
    }
*/

    /** get method. */
    public T get(int index) {
        return items[(head + index) % items.length];
    }

    /** remove first item in the list and return the deleted item. */
    public T removeFirst() {
        if (size == items.length) {
            return null;
        }
        T tmp = items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        size -= 1;
        if (size * 4 < items.length && items.length >= 16) {
            downResize();
        }
        return tmp;
    }

    /** remove last item in the list and return the deleted item */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T tmp = items[tail];
        items[tail] = null;
        tail = (tail + items.length - 1) % items.length;
        size -= 1;
        if (size * 4 < items.length && items.length >= 16) {
            downResize();
        }
        return tmp;
    }

    /** return size of list(# of items). */
    public int size() {
        return size;
    }

    /** prints the items in the deque from first to last. TODO*/
    public void printDeque() {
        int index = 0;
        while (index < size) {
            System.out.print(items[(head + index) % items.length] + " ");
            index += 1;
        }
    }

}
