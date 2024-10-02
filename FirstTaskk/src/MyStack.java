import java.util.Arrays;

public class MyStack {
    private int top;
    private Object[] stack;

    public MyStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость не может быть меньше 1");
        }

        stack = new Object[capacity];
        top = -1;
    }

    public MyStack(Object[] stack) {
        this.stack = stack;
        top = stack.length - 1;
    }

    public MyStack() {
        stack = new Object[10];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(Object element) {
        if (top + 1 >= stack.length) {
            throw new IllegalStateException("Стек переполнен");
        }

        top++;
        stack[top] = element;
    }

    public Object peek() {
        return stack[top];
    }

    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }

        Object temp = stack[top];
        stack[top] = null;
        top--;

        return temp;
    }

    public int search(Object element) {
        for (int i = top; i >= 0; i--) {
            if (stack[i].equals(element)) {
                return i;
            }
        }

        return -1;
    }

    public void print(){
        for(Object o : stack){
            System.out.printf("%s ", o);
        }

        System.out.println();
    }

    public void increaseCapacity(int capacity){
        stack = Arrays.copyOf(stack, capacity);
    }

}