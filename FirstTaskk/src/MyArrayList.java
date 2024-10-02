import java.util.Arrays;

public class MyArrayList {
    private Object[] items;
    private int size;
    private int capacity;

    public MyArrayList() {
        Object[] items = new Object[10];
        capacity = 10;
    }

    public MyArrayList(int capacity) {
        Object[] items = new Object[capacity];
        this.capacity = capacity;
    }

    public MyArrayList(Object[] o) {
        items = o;
        size = items.length;
        capacity = items.length;
    }

    public int indexof(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    public void remove(Object o) {
        int index = indexof(o);

        if (index == -1 || index >= size) {
            throw new IllegalArgumentException("Такого элемента нет");
        }

        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }

        items[size - 1] = null;
        size--;
    }

    public void insert(Object o, int index) {
        if (size == capacity) {
            throw new IllegalArgumentException("Список переполнен");
        } else if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("Индекс выходит за пределы списка");
        }

        for(int i = size; i > index; i--){
            items[i] = items[i-1];
        }

        items[index] = o;
        size++;
    }

    public void print(){
        for(Object o : items){
            System.out.printf("%s ", o);
        }
    }

    public void increaseCapacity(int capacity) {
        items = Arrays.copyOf(items, capacity);
        this.capacity = capacity;
    }
}
