import java.util.Random;

public class Search {
    private final int[] array;
    int sequentialComparisonCount;
    int binaryComparisonCount;
    int compareInSortCount;

    public Search(int capacity) {
        array = new int[capacity];
        Random random = new Random();

        for (int i = 0; i < capacity; i++) {
            int r = random.nextInt(1000);
            array[i] = r;
        }
    }

    public int sequentialCompare(int element) {
        for (int i : array) {
            if (i == element) {
                return sequentialComparisonCount;
            }

            sequentialComparisonCount++;
        }

        return sequentialComparisonCount;
    }

    public int binaryCompare(int element) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int average = (left + right) / 2;
            if (array[average] == element) {
                return binaryComparisonCount;
            }

            if (array[average] > element) {
                right = average - 1;
                binaryComparisonCount++;
            } else {
                left = average + 1;
                binaryComparisonCount++;
            }
        }

        return binaryComparisonCount;
    }

    public int sort() {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    compareInSortCount++;
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        return compareInSortCount;
    }

    public int getRandomElement() {
        Random random = new Random();

        return array[random.nextInt(array.length)];
    }

    public void print() {
        for (int i : array) {
            System.out.printf("%d ", i);
        }

        System.out.println();
    }

}
