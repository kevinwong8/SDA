import java.io.*;
import java.util.StringTokenizer;

public class keuanganSofita {
    private static InputReader in;
    private static PrintWriter out;

    // static int maxOddSubSum(int[] a) {
    // int maxSum = 0;
    // int thisSum = 0;
    // for (int jj = 0; jj < a.length; jj++) {
    // thisSum += a[jj];
    // if (thisSum > maxSum) {
    // maxSum = thisSum;
    // } else if (thisSum < 0) {
    // thisSum = 0;
    // }
    // }
    // return maxSum;
    // }

    // static int maxEvenSubSum(int[] a) {
    // // TODO: Implement this method
    // int maxSum = 0;
    // int thisSum = 0;
    // for (int jj = 0; jj < a.length; jj++) {
    // thisSum += a[jj];
    // System.out.println(thisSum);
    // if (thisSum > maxSum) {
    // maxSum = thisSum;
    // } else if (thisSum < 0) {
    // thisSum = 0;
    // }
    // }
    // return maxSum;

    // }

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        // Read value of N
        long N = in.nextInt();
        long p = N % 2;
        long maxSum = 0;
        long thisSum = 0;
        long j;
        for (j = 0; j < N; j++) {
            long a = in.nextInt();
            if (a % 2 == p || a % 2 == -p) {
                maxSum = a;
                thisSum = a;
                break;
            }
        }

        for (long i = j + 1; i < N; ++i) {
            long a = in.nextInt();
            if (a % 2 == p) {
                thisSum += a;
            } else if (a % 2 == -p) {
                if (maxSum < 0) {
                    thisSum = a;
                } else {
                    thisSum += a;
                }

            } else if (maxSum < 0) {
                thisSum = maxSum;
            } else {
                thisSum = 0;
            }

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        out.println(maxSum);

        // don't forget to close/flush the output
        out.close();
    }

    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the
    // usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit
    // Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}