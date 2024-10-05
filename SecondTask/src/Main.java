public class Main {
    public static void main(String[] args) {
        System.out.printf("%5s | ", "Размер массива");
        System.out.printf("%5s | ", "Практическая сложность последовательного поиска");
        System.out.printf("%5s", " Практическая сложность двоичного поиска\n");
        for(int i = 0; i<= 106; i++){
            System.out.printf("%1s", '-');
        }
        System.out.println();

        for(int i = 20; i<=500; i+=20){
            Search array = new Search(i);
            System.out.printf("%14d | %47d |", i, array.sequentialCompare(array.getRandomElement()));
            array.sort();
            System.out.printf("%41d\n", array.binaryCompare(array.getRandomElement()));
        }
    }
}
