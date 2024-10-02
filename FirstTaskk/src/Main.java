import java.util.Random;

public class Main {
    public static void main(String[] args) {
        /*
        Integer[] myArray2 = {1, 3, 2};
        MyArrayList realMyArrayList = new MyArrayList(myArray2);
        realMyArrayList.increaseCapacity(5);
        realMyArrayList.insert(4, 2);
        realMyArrayList.remove(3);
        realMyArrayList.print(); */

        // 1
        //цикл 100 итераций 0 или 1 (0 - push, 1 - pop), перед циклом добавить 10 элементов,после каждого вывести
        Random random = new Random();
        Object[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        MyStack checkStack = new MyStack(array);
        checkStack.increaseCapacity(100);

        for(int i = 1; i <= 100; i++){
            if(i % 2 == 0) {
                checkStack.push(random.nextInt(10));
            }
            else{
                checkStack.pop();
            }

            checkStack.print();
        }

        /* // 2
        Object[] array = {3, 5, 7};
        MyQueue realQueue1 = new MyQueue();
        MyQueue realQueue2 = new MyQueue(array);
        MyQueue realQueue3 = new MyQueue(5);

        realQueue2.increaseCapacity(8);
        realQueue2.enqueue('*');
        System.out.println(realQueue2.dequeue()); */
    }
}