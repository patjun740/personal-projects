package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> givenComparator;

    public MaxArrayDeque(Comparator<T> c) {
        givenComparator = c;
    }
    public T max() {
        if (this.size() == 0) {
            return null;
        } else {
            T returnItem = this.get(0);
            int pointer = 1;
            while (pointer < this.size()) {
                T compareItem = this.get(pointer);
                int comparableResult = givenComparator.compare(compareItem, returnItem);
                if (comparableResult > 0) {
                    returnItem = compareItem;
                }
                pointer += 1;
            }
            return returnItem;
        }
    }

    public T max(Comparator<T> c) {
        if (this.size() == 0) {
            return null;
        } else {
            T returnItem = this.get(0);
            int pointer = 1;
            while (pointer < this.size()) {
                T compareItem = this.get(pointer);
                int comparableResult = c.compare(compareItem, returnItem);
                if (comparableResult > 0) {
                    returnItem = compareItem;
                }
                pointer += 1;
            }
            return returnItem;
        }
    }
}
