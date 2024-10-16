import java.util.Arrays;

public static void main(String[] args) {
    int x = 12;
    int sum = 0;
    int result = 1;

    System.out.println("Сумма цифр числа: " + sum(x, sum));
    System.out.println("Факториал числа: " + factorial(x, 1, result));
    System.out.println("Сумма цифр от 1 до числа: " + sumFromOneToX(sum, x));

    printFrom1ToX(x);
    System.out.println();
    printFromXTo1(x, 1);
    System.out.println();
    printFromMinusXToX(x, -x);
    System.out.println();

    System.out.println(sqrtFrom1ToX(x, sum, 1));

    long startTime1 = System.nanoTime();
    System.out.println(fibonacciRecursive(x));
    long endTime1 = System.nanoTime();
    long result1 = endTime1 - startTime1;
    System.out.println("Без оптимизации: " + result1);

    fibonacciCashMemory fibonacciCash = new fibonacciCashMemory();

    long startTime2 = System.nanoTime();
    System.out.println(fibonacciCash.fibonacciCash(12));
    long endTime2 = System.nanoTime();
    long result2 = endTime2 - startTime2;
    System.out.println("С кэшом: " + result2);

    long startTime3 = System.nanoTime();
    fibonacciTempArray(x);
    long endTime3 = System.nanoTime();
    long result3 = endTime3 - startTime3;
    System.out.println("С Временным массивом: " + result3);

    long startTime4 = System.nanoTime();
    fibonacciNaLety(x);
    long endTime4 = System.nanoTime();
    long result4 = endTime4 - startTime4;
    System.out.println("С методом на лету: " + result4);
}

public static int sum(int x, int sum) {
    if (x >= 1) {
        sum += x % 10;
        return sum(x / 10, sum);
    }

    return sum;
}


public static int factorial(int x, int currentNumber, int result) {
    if (x == 1 || x == 0) {
        return result;
    }

    if (currentNumber != x + 1) {
        result *= currentNumber;
        return factorial(x, currentNumber + 1, result);
    }

    return result;
}

public static int sumFromOneToX(int sum, int x) {
    if (x != 0) {
        return sumFromOneToX(sum + x, x - 1);
    }

    return sum;
}

public static void printFrom1ToX(int x) {
    if (x > 1) {
        printFrom1ToX(x - 1);
    }

    System.out.printf("%d ", x);
}

public static void printFromXTo1(int x, int currentNumber) {
    if (currentNumber < x) {
        printFromXTo1(x, currentNumber + 1);
    }

    System.out.printf("%d ", currentNumber);
}

public static void printFromMinusXToX(int x, int minusX) {
    if (x > minusX) {
        printFromMinusXToX(x - 1, minusX);
    }

    System.out.printf("%d ", x);
}

public static int sqrtFrom1ToX(int x, int sum, int currentNumber) {
    if (currentNumber != x + 1) {
        return sqrtFrom1ToX(x, sum + currentNumber * currentNumber, currentNumber + 1);
    }

    return sum;
}

public static int fibonacciRecursive(int x) {
    return x < 2 ? x : fibonacciRecursive(x - 1) + fibonacciRecursive(x - 2);
}

public static class fibonacciCashMemory {
    int[] cache = new int[10000];

    public fibonacciCashMemory() {
        cache[1] = 1;
    }

    public int fibonacciCash(int n) {
        if (cache[n] != 0) {
            return cache[n];
        }

        if (n <= 0) {
            return 0;
        }

        int result = fibonacciCash(n - 1) + fibonacciCash(n - 2);
        cache[n] = result;

        return result;
    }
}

public static void fibonacciTempArray(int n) {
    int[] f = new int[n + 1];
    f[0] = 0;
    f[1] = 1;

    for (int i = 2; i <= n; ++i) {
        f[i] = f[i - 1] + f[i - 2];
    }

    System.out.println(f[n]);
}

public static void fibonacciNaLety(int x) {
    int f0 = 0;
    int f1 = 1;

    for (int i = 2; i < x + 1; i++) {
        int f2 = f0 + f1;
        f0 = f1;
        f1 = f2;
    }

    System.out.println(f1);
}