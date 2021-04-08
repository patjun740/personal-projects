package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 2;
        nextLast = 3;
    }

    private void resize(int newSize) {
        T[] resizeArray = (T[]) new Object[newSize];
        int tempPosition = nextFirst;
        if (tempPosition == items.length - 1) {
            tempPosition = 0;
        } else {
            tempPosition += 1;
        }
        for (int i = 0; i < size; i++) {
            resizeArray[i] = items[tempPosition];
            if (tempPosition == items.length - 1) {
                tempPosition = 0;
            } else {
                tempPosition += 1;
            }
        }
        items = resizeArray;
        nextLast = size;
        nextFirst = items.length - 1;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int pointer = nextFirst;
        if (nextFirst == items.length - 1) {
            pointer = 0;
        } else {
            pointer += 1;
        }
        while (pointer != nextLast) {
            System.out.print(items[pointer] + " ");
            if (pointer == items.length - 1) {
                pointer = 0;
            } else {
                pointer += 1;
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        T copy;
        if (this.isEmpty()) {
            return null;
        }
        if (nextFirst + 1 == items.length) {
            copy = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            copy = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = nextFirst + 1;
        }
        size -= 1;
        double usagePercent = (double) size / items.length;
        if (items.length >= 16 && usagePercent < 0.25) {
            resize(items.length / 2);
        }
        return copy;
    }

    @Override
    public T removeLast() {
        T copy;
        if (this.isEmpty()) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
            copy = items[nextLast];
            items[nextLast] = null;

        } else {
            nextLast = nextLast - 1;
            copy = items[nextLast];
            items[nextLast] = null;

        }
        size -= 1;
        double usagePercent = (double) size / items.length;
        if (items.length >= 16 && usagePercent < 0.25) {
            resize(items.length / 2);
        }
        return copy;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        int counter = 0;
        int copy = nextFirst;
        if (nextFirst == items.length - 1) {
            copy = 0;
        } else {
            copy += 1;
        }
        while (counter != index) {
            if (copy == items.length - 1) {
                copy = 0;
            } else {
                copy += 1;
            }
            counter++;
        }
        return items[copy];
    }

    @Override
    public boolean equals(Object o) {
        if ((!(o instanceof Deque)) || this.size() != ((Deque<T>) o).size()) {
            return false;
        }  else {
            for (int i = 0; i < this.size(); i++) {
                if (!(this.get(i).equals(((Deque<T>) o).get(i)))) {
                    return false;
                }
            }
            return true;
        }
    }

    public Iterator<T> iterator() {

        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T returnItem = get(index);
            index += 1;
            return returnItem;
        }
    }
}
