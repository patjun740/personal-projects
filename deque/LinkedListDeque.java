package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private class ObjNode {
        private T item;
        private ObjNode next;
        private ObjNode previous;

        ObjNode(T obj, ObjNode n) {
            item = obj;
            next = n;
            previous = null;
        }

        ObjNode(T obj, ObjNode n, ObjNode p) {
            item = obj;
            next = n;
            previous = p;
        }
    }

    private ObjNode sentinel = new ObjNode(null, null);
    private int size = 0;

    public LinkedListDeque() {
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new ObjNode(item, sentinel.next, sentinel);
        sentinel.next.next.previous = sentinel.next;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        sentinel.previous = new ObjNode(item, sentinel, sentinel.previous);
        sentinel.previous.previous.next = sentinel.previous;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        ObjNode position = sentinel.next;
        while (position != sentinel) {
            System.out.print(position.item + " ");
            position = position.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        ObjNode firstElem = sentinel.next;
        if (this.isEmpty()) {
            return null;
        } else {
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            size -= 1;
        }
        return firstElem.item;
    }

    @Override
    public T removeLast() {
        ObjNode lastElem = sentinel.previous;
        if (this.isEmpty()) {
            return null;
        } else {
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size -= 1;
        }
        return lastElem.item;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        ObjNode position = sentinel.next;
        for (int i = 0; i < index; i++) {
            position = position.next;
        }
        return position.item;
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return (getRecursionHelper(sentinel.next, 0, index));
        }
    }

    private T getRecursionHelper(ObjNode theLinkedList, int pointer, int index) {
        if (index == pointer) {
            return theLinkedList.item;
        } else {
            return getRecursionHelper(theLinkedList.next, pointer + 1, index);
        }
    }


    @Override
    public boolean equals(Object o) {
        if ((!(o instanceof Deque)) || this.size() != ((Deque<T>) o).size()) {
            return false;
        } else {
            for (int i = 0; i < this.size(); i++) {
                if (!(this.get(i).equals(((Deque<T>) o).get(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private ObjNode position;

        private LinkedListDequeIterator() {
            position = sentinel.next;
        }

        public boolean hasNext() {
            return position != sentinel;
        }

        public T next() {
            T returnItem = position.item;
            position = position.next;
            return returnItem;
        }
    }
}
