public class Backend {

    // declaring all the global variables
    static int m, n, m1, n1;
    static int p1[][], p2[][];
    static int nashP1[][], nashP2[][];
    static int diff1[][];
    static int p1oddments[];
    static int diff2[][];
    static int p2oddments[];
    static int hcfforpl1[];
    static int hcfforpl2[];
    static int oddmenttotal1 = 0;
    static int oddmenttotal2 = 0;
    static String resultsP1[] = new String[3];
    static String resultsP2[] = new String[3];
    static String disply1[] = new String[3];
    static String disply2[] = new String[3];

    // constructor that takes the input and initializes all the data structures
    // appropriately
    Backend() {
        // Scanner ob = new Scanner(System.in);
        // System.out.println("Enter the order of the payoff matrix");
        // m = ob.nextInt();
        // n = ob.nextInt();
        m1 = m;
        n1 = n;
        p1 = new int[m][n];
        p2 = new int[m][n];
//        diff1 = new int[m][n - 1];
//        diff2 = new int[m - 1][n];
        p1oddments = new int[m];
        p2oddments = new int[n];
        hcfforpl1 = new int[m];
        hcfforpl2 = new int[n];
        resultsP1 = new String[m];
        resultsP2 = new String[n];

        int i, j;
        // System.out.println("Enter the payoff matrix for player 1: ");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (i == 0) {
                    p1[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    p1[i][j] = i;
                    continue;
                }
                // p1[i][j] = ob.nextInt();
            }
        }
        // System.out.println("Enter the payoff matrix for player 2: ");
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (i == 0) {
                    p2[i][j] = j;
                    continue;
                }
                if (j == 0) {
                    p2[i][j] = i;
                    continue;
                }
                // p2[i][j] = ob.nextInt();
            }
        }
    }

    // function to check for any cases of pure dominance in the payoff matrices
    public void checkDominance() {

        // checking Dominance for Player 1
        int i, j, k;
        int flag;

        for (i = 1; i < m; i++) {
            for (j = 1; j < m; j++) {
                if (i == j) {
                    continue;
                }
                flag = 0;
                for (k = 1; k < n; k++) {
                    if (p1[i][k] <= p1[j][k]) {
                        flag *= 1;
                    } else {
                        flag += 1;
                    }
                }
                if (flag == 0) {
                    if (m == 3 && n == 3) {
                        return;
                    }
                    ejectStrat(i, 'r');
                    i = 1;
                    j = 1;
                }

            }
        }
        if (m == 3 && n == 3) {
            return;
        }

        // checking Dominance for Player 2
        boolean changes = false;
        for (i = 1; i < n; i++) {
            for (j = 1; j < n; j++) {
                flag = 0;
                if (i == j) {
                    continue;
                }
                for (k = 1; k < m; k++) {
                    if (p2[k][i] <= p2[k][j]) {
                        flag *= 1;
                    } else {
                        flag += 1;
                    }
                }
                if (flag == 0) {
                    if (m == 3 && n == 3) {
                        return;
                    }
                    ejectStrat(j, 'c');
                    i = 1;
                    j = 1;
                    changes = true;
                }

            }
        }

        if (changes == true) {
            checkDominance();
        }
    }

    // function to discard a dominated strategy form the payoff matrices
    public void ejectStrat(int i, char ch) {
        if (ch == 'r') {
            if (i == m) {
                m--;
                return;
            }
            for (; i < m - 1; i++) {
                for (int j = 0; j < n; j++) {
                    p1[i][j] = p1[i + 1][j];
                    p2[i][j] = p2[i + 1][j];
                }
            }
            m -= 1;
        }
        if (ch == 'c') {
            if (i == n) {
                n--;
                return;
            }
            for (; i < n - 1; i++) {
                for (int j = 0; j < m; j++) {
                    p1[j][i] = p1[j][i + 1];
                    p2[j][i] = p2[j][i + 1];
                }
            }
            n -= 1;
        }

    }

    // function that calculates the nash eqm of the payoff matrices after they have
    // been reduced to order 2
    public void calcNashEqmO2() {
        nashP1 = new int[2][2];
        nashP2 = new int[2][2];
        nashP1[0][0] = Math.abs(p2[2][2] - p2[2][1]);
        nashP1[1][0] = Math.abs(p2[1][1] - p2[2][1] - p2[1][2] + p2[2][2]);

        nashP1[0][1] = nashP1[1][0] - nashP1[0][0];
        nashP1[1][1] = nashP1[1][0];

        nashP2[0][0] = Math.abs(p1[2][2] - p1[1][2]);
        nashP2[1][0] = Math.abs(p1[1][1] - p1[1][2] - p1[2][1] + p1[2][2]);

        nashP2[0][1] = nashP2[1][0] - nashP2[0][0];
        nashP2[1][1] = nashP2[1][0];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                nashP1[i][j] = Math.abs(nashP1[i][j]);
                nashP2[i][j] = Math.abs(nashP2[i][j]);
            }
        }

        reduceFractions();
    }

    // function that calculates the nash eqm of the payoff matrices after they have
    // been reduced to order 3
    public void calcNashEqmO3() {
        diff1 = new int[m][n - 1];
        diff2 = new int[m - 1][n];
        // SOlVING FOR PLAYER1
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n - 1; j++) {
                diff1[i][j] = p2[i][j] - p2[i][j + 1];

            }
        }
        p1oddments[0] = detoddments(diff1[2][1], diff1[2][2], diff1[3][1], diff1[3][2]);
        p1oddments[1] = detoddments(diff1[1][1], diff1[1][2], diff1[3][1], diff1[3][2]);
        p1oddments[2] = detoddments(diff1[1][1], diff1[1][2], diff1[2][1], diff1[2][2]);

        for (int i = 0; i < m - 1; i++) {
            oddmenttotal1 += p1oddments[i];
        }

        for (int i = 0; i < m - 1; i++) {
            hcfforpl1[i] = hcf(p1oddments[i], oddmenttotal1);

        }

        // SOLVING FOR PLAYER 2
        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n; j++) {
                diff2[i][j] = p2[i][j] - p2[i + 1][j];

            }
        }
        p2oddments[0] = detoddments(diff2[1][2], diff2[1][3], diff2[2][2], diff2[2][3]);
        p2oddments[1] = detoddments(diff2[1][1], diff2[1][3], diff2[2][1], diff2[2][3]);
        p2oddments[2] = detoddments(diff2[1][1], diff2[1][2], diff2[2][1], diff2[2][2]);

        for (int i = 0; i < n - 1; i++) {
            oddmenttotal2 += p2oddments[i];
        }

        for (int i = 0; i < n - 1; i++) {
            hcfforpl2[i] = hcf(p2oddments[i], oddmenttotal2);

        }

    }

    // function that simplifies the probabilities to their smallest fraction
    public void reduceFractions() {
        int hcf;
        hcf = hcf(nashP1[0][0], nashP1[1][0]);
        while (hcf != 1) {
            nashP1[0][0] /= hcf;
            nashP1[1][0] /= hcf;
            hcf = hcf(nashP1[0][0], nashP1[1][0]);
        }
        hcf = hcf(nashP1[0][1], nashP1[1][1]);
        while (hcf != 1) {
            nashP1[0][1] /= hcf;
            nashP1[1][1] /= hcf;
            hcf = hcf(nashP1[0][1], nashP1[1][1]);
        }
        hcf = hcf(nashP2[0][0], nashP2[1][0]);
        while (hcf != 1) {
            nashP2[0][0] /= hcf;
            nashP2[1][0] /= hcf;
            hcf = hcf(nashP2[0][0], nashP2[1][0]);
        }
        hcf = hcf(nashP2[0][1], nashP2[1][1]);
        while (hcf != 1) {
            nashP2[0][1] /= hcf;
            nashP2[1][1] /= hcf;
            hcf = hcf(nashP2[0][1], nashP2[1][1]);
        }
    }

    // function that calculates the hcf of two numbers
    public int hcf(int a, int b) {
        if (a == 0) {
            return Math.abs(b);
        }
        if (b == 0) {
            return Math.abs(a);
        }
        int i, hcf = 1;
        for (i = 1; i < a || i < b; i++) {
            if ((a % i == 0) && (b % i == 0)) {
                hcf = i;
            }
        }
        return hcf;
    }

    // function to find the determinant of a matrix of order 2
    public int detoddments(int A, int B, int C, int D) {
        return Math.abs((A * D) - (B * C));
    }

    // function that displays the nash eqm of a payoff matrix of order 2
    public static void displayNashEqmO2() {
        int count0 = 0;
        int res = 0;
        float flag = 0;
        if (m1 == 4) {
            for (int i = 1, j = 0; i < m1; i++) {
                if (p1[i][0] != i) {
                    disply1[res++] = "0";
                    count0++;
                    i++;
                }
                if (j < 2) {
                    flag += (float)nashP1[0][j] / nashP1[1][j];
                    disply1[res++] = nashP1[0][j] + "/" + nashP1[1][j];
                    j++;
                }
            }
            for (int i = (2 + count0); i < m1 - 1; i++) {
                disply1[res++] = "0";
            }
            if( flag != 1.0)
                throw new ArithmeticException();
            res = 0;
            flag = 0;
            for (int i = 1, j = 0; i < n1; i++) {
                if (p1[0][i] != i) {
                    disply2[res++] = "0";
                    count0++;
                    i++;
                }

                if (j < 2) {
                    flag += (float)nashP2[0][j] / nashP2[1][j];
                    disply2[res++] = nashP2[0][j] + "/" + nashP2[1][j];
                    j++;
                }
            }
            for (int i = (2 + count0); i < n1 - 1; i++) {
                disply2[res++] = "0";
            }
            if( flag != 1.0)
                throw new ArithmeticException();
            return;
        }

        res = 0;
        count0 = 0;
        flag = 0;
        for (int i = 1, j = 0; i < m1; i++) {
            if (p1[i][0] != i) {
                resultsP1[res++] = "0";
                count0++;
                i++;
            }
            if (j < 2) {
                flag += (float)nashP1[0][j] / nashP1[1][j];
                resultsP1[res++] = nashP1[0][j] + "/" + nashP1[1][j];
                j++;
            }
        }
        for (int i = (2 + count0); i < m1 - 1; i++) {
            resultsP1[res++] = "0";
        }
        if( flag != 1.0)
            throw new ArithmeticException();
        res = 0;
        flag = 0;
        for (int i = 1, j = 0; i < n1; i++) {
            if (p1[0][i] != i) {
                resultsP2[res++] = "0";
                count0++;
                i++;
            }

            if (j < 2) {
                flag += (float)nashP2[0][j] / nashP2[1][j];
                resultsP2[res++] = nashP2[0][j] + "/" + nashP2[1][j];
                j++;
            }
        }
        for (int i = (2 + count0); i < n1 - 1; i++) {
            resultsP2[res++] = "0";
        }
        if( flag != 1.0)
            throw new ArithmeticException();
    }

    // function that displays the nash eqm of a payoff matrix of order 3
    public static void displayNashEqmO3() {
        float flag = 0;
        for (int i = 0; i < m - 1; i++) {
            flag += ((float)(p1oddments[i] / hcfforpl1[i]) )/ ((float) (oddmenttotal1 / hcfforpl1[i]));
            if(oddmenttotal1 / hcfforpl1[i] == 0 || p1oddments[i] / hcfforpl1[i] == 0)
            {
                disply1[i] = "0";
            }
            else
            {
                disply1[i] = p1oddments[i] / hcfforpl1[i] + "/" + oddmenttotal1 / hcfforpl1[i];
            }
            if(disply1[i] == null)
            {
                disply1[i] = "0";
            }
        }
        if( flag != 1.0)
            throw new ArithmeticException();
        flag = 0;
        for (int i = 0; i < m - 1; i++) {
            flag += ((float)(p2oddments[i] / hcfforpl2[i]) )/ ((float) (oddmenttotal2 / hcfforpl2[i]));
            if(oddmenttotal2 / hcfforpl2[i] == 0 || p2oddments[i] / hcfforpl2[i] == 0)
            {
                disply2[i] = "0";
            }
            else
            {
                disply2[i] = p2oddments[i] / hcfforpl2[i] + "/" + oddmenttotal2 / hcfforpl2[i];
            }
            if(disply1[i] == null)
            {
                disply2[i] = "0";
            }
        }
        if( flag != 1.0)
            throw new ArithmeticException();
    }
}