public class Set {
    private Object[] set;
    private int size;
    private int capacity;

    public Set(int capacity) {
        set = new Object[capacity];
        this.capacity = capacity;
    }

    public void add(Object element) {
        if (contains(element)) {
            throw new IllegalArgumentException("Элемент уже присутствует в множестве.");
        }

        if (size == capacity) {
            throw new IllegalStateException("Множество переполнено! Невозможно добавить элемент.");
        } else {
            set[size + 1] = element;
            size++;
        }
    }

    public void delete(Object element) {
        int index = indexOf(element);

        if (index == -1) {
            throw new IllegalArgumentException("Элемент не найден в множестве.");
        }

        for (int i = index; i < size - 1; i++) {
            set[i] = set[i + 1];

            set[size-1] = null;
            size--;
        }
    }

    public boolean contains(Object element) {
        return indexOf(element) != -1;
    }

    private int indexOf(Object element) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Множество пусто.");
        } else {
            for(Object o : set){
                System.out.printf("%s ", o);
            }
        }
    }
}