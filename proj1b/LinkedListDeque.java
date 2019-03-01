public class LinkedListDeque<T> implements Deque<T> {

    private class ListNode {
        private ListNode prev, next;
        private T val;

        private ListNode(T val, ListNode prev, ListNode next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private ListNode sentinel;

    /** create an empty LinkedListDeque. */
    public LinkedListDeque() {
        sentinel = new ListNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /** create a deep copy of 'other'. */
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new ListNode(null, null, null);
        ListNode ptr1 = sentinel;
        ListNode ptr2 = other.sentinel;
        while (ptr2.next != other.sentinel) {
            ptr2 = ptr2.next;
            ptr1.next = new ListNode(ptr2.val, ptr1, sentinel);
            ptr1 = ptr1.next;
        }
        size = other.size;
    }

    /** return size of the list. */
    @Override
    public int size() {
        return size;
    }

    /** check whether a list is empty. */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**  add an item to the front of the linked list. */
    @Override
    public void addFirst(T val) {
        size += 1;
        ListNode tmp = new ListNode(val, sentinel, sentinel.next);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
    }

    /** add an item to the back of the linked list. */
    @Override
    public void addLast(T val) {
        size += 1;
        ListNode tmp = new ListNode(val, sentinel.prev, sentinel);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
    }

    /** remove first item in the linked list. */
    @Override
    public T removeFirst() {
        size = (size == 0) ? 0 : size - 1;
        ListNode tmp = sentinel.next;
        tmp.next.prev = sentinel;
        sentinel.next = tmp.next;
        return tmp.val;
    }

    /** remove last item in the linked list. */
    @Override
    public T removeLast() {
        size = (size == 0)? 0 : size - 1;
        ListNode tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        return tmp.val;
    }


    /** get the indexTH item in the linked list. */
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        ListNode ptr = sentinel.next;
        while (index > 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.val;
    }

    /** helper function. */
    public T getRecursive (ListNode ptr, int index) {
        if (index == 0) {
            return ptr.val;
        }
        return getRecursive(ptr.next, index - 1);
    }

    /** get method with recursive implementation. */
    public T getRecursive (int index) {
        if (index > size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

    /** prints all items in the deque from first to last. */
    @Override
    public void printDeque() {
        ListNode ptr = sentinel;
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.val + " ");
        }
    }
}
