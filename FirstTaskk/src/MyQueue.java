import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyQueue {
    private Object[] queue;
    private int size;

    public MyQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше 1");
        }

        queue = new Object[capacity];
    }

    public MyQueue(Object[] queue) {
        this.queue = queue;
        size = queue.length;
    }

    public MyQueue() {
        queue = new Object[10];
    }

    public boolean enqueue(Object element) {
        if (size >= queue.length) {
            throw new IllegalStateException("Очередь переполнена");
        }

        queue[size] = element;
        size++;
        return true;
    }

    public Object dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Очередь пуста");
        }

        Object temp = queue[0];
        for (int i = 1; i < size; i++) {
            queue[i - 1] = queue[i];
        }

        queue[size] = null;
        size--;
        return temp;
    }

    public Object first() {
        if (isEmpty()) {
            throw new IllegalStateException("Очередь пуста");
        }

        return queue[0];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print(){
        for (Object o : queue) {
            System.out.printf("%s ", o);
        }

        System.out.println();
    }

    public void increaseCapacity(int capacity) {
        queue = Arrays.copyOf(queue, capacity);
    }
}