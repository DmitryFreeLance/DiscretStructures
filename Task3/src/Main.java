public class Main {
    public static void main(String[] args) {
        System.out.printf("%10s | %10s | %10s | %10s | %10s |\n", "Входные данные", "Время (нс) от 2 до N", "Время (нс) от 2 до N Odd", "Время (нс) от 2 до sqrt(N)", "Время (нс) от 2 до sqrt(N)Odd");

        for(int i = 1000; i<=10000; i+=1000){
            Experements experements = new Experements(i);

            long startTime1 = System.nanoTime();
            experements.from2ToN();
            long endTime1 = System.nanoTime();
            long result1 = endTime1 - startTime1;

            long startTime2 = System.nanoTime();
            experements.from2ToNOdd();
            long endTime2 = System.nanoTime();
            long result2 = endTime2 - startTime2;

            long startTime3 = System.nanoTime();
            experements.from2ToNSqrt();
            long endTime3 = System.nanoTime();
            long result3 = endTime3 - startTime3;

            long startTime4 = System.nanoTime();
            experements.from2ToNSqrtOdd();
            long endTime4 = System.nanoTime();
            long result4 = endTime4 - startTime4;

            System.out.printf("%14d | %20d | %24d | %26d | %29d |\n", i, result1, result2, result3, result4);
        }

        System.out.printf("%s\n", "-------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%15s | %15s | %15s | %15s |\n", "Входные данные", "Время (нс) НОД от 1 до N", "Время (нс) НОД от N до 1", "Время (нс) Евклид");

        for(int i = 5000; i<=15000; i+=1000){
            for(int j = 15000; j >= 1000; j=-1000){
                Experements experements = new Experements(i);

                long startTime1 = System.nanoTime();
                experements.findNOD(j);
                long endTime1 = System.nanoTime();
                long result1 = endTime1 - startTime1;

                long startTime2 = System.nanoTime();
                experements.findNODReversed(j);
                long endTime2 = System.nanoTime();
                long result2 = endTime2 - startTime2;

                long startTime3 = System.nanoTime();
                experements.euclidAlgorithm(j);
                long endTime3 = System.nanoTime();
                long result3 = endTime3 - startTime3;

                System.out.printf("%15d | %24d | %24d | %17d |\n", i, result1, result2, result3);
            }
        }
    }
}