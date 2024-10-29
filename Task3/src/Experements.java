public class Experements {
    private final int n;
    private boolean isSimple = true;

    public Experements(int n) {
        this.n = n;
    }

    public boolean from2ToN() {
        for (int i = 2; i < n; i++) {
            if(n % i == 0){
                isSimple = false;
            }
        }

        return isSimple;
    }

    public boolean from2ToNOdd() {
        for (int i = 3; i < n; i+=2) {
            if(n % i == 0){
                isSimple = false;
            }
        }

        return isSimple;
    }

    public boolean from2ToNSqrt() {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if(n % i == 0){
                isSimple = false;
            }
        }

        return isSimple;
    }

    public boolean from2ToNSqrtOdd() {
        for (int i = 3; i < Math.sqrt(n); i+=2) {
            if(n % i == 0){
                isSimple = false;
            }
        }

        return isSimple;
    }

    public int findNOD(int m){
        int minNumber = Math.min(n, m);
        int BiggestDivisor = 0;

        for(int i = 1; i <= minNumber; i++){
            if(m % i == 0 && n % i == 0){
                BiggestDivisor = i;
            }
        }

        return BiggestDivisor;
    }

    public int findNODReversed(int m){
        int minNumber = Math.min(n, m);
        int BiggestDivisor = 0;

        for(int i = minNumber; i >= 1; i--){
            if(m % i == 0 && n % i == 0){
                BiggestDivisor = i;
                break;
            }
        }

        return BiggestDivisor;
    }

    public int euclidAlgorithm(int m){
        int a = Math.max(m, n);
        int b = Math.min(m,n);

        while (b !=0) {
            int tmp = a%b;
            a = b;
            b = tmp;
        }

        return a;
    }
}
