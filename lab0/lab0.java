import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import static java.lang.Math.abs;

public class lab0 {
    private static InputReader in = new InputReader(System.in);
    private static PrintWriter out = new PrintWriter(System.out);

    private static int N;
    private static int K;
    private static List<Integer> arr = new ArrayList<Integer>();

    // this algorithm run in O(N^2)
    // with N up to 100,000 this algorithm will take roughly 100,000^2 =
    // 10,000,000,000 operations
    // it's higher than 10^8, which is roughly how many operations a regular
    // computer can run in one second
    static boolean existPairWithSumKN2() {
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int x = arr.get(i);
                int y = arr.get(j);
                if (x + y == K)
                    return true;
            }
        }
        return false;
    }

    // this algorithm run in O(N)
    // with N up to 100,000 this algorithm will take 100,000 operations, less than
    // 10^8
    // it should pass the time limit but will runtime error due to a small bug
    static boolean existPairWithSumKRTE() {
        boolean[] exist = new boolean[10001];
        for (int i = 0; i < N; i++) {
            int x = arr.get(i);
            if (exist[K - x])
                return true;
            exist[x] = true;
        }
        return false;
    }

    // this algorithm run in O(N)
    // with N up to 100,000 this algorithm will take 100,000 operations, less than
    // 10^8
    // it should pass the time limit and does not get runtime error but will get
    // wrong answer for some cases
    static boolean existPairWithSumKWA() {
        boolean[] exist = new boolean[10001];
        for (int i = 0; i < N; i++) {
            int x = arr.get(i);
            if (exist[abs(K - x)])
                return true;
            exist[x] = true;
        }
        return false;
    }

    // TODO: modify the methods above or implement your own to get correct answer
    // for all cases
    static boolean existPairWithSumKAC() {
        boolean[] exist = new boolean[10001];
        for (int i = 0; i < N; i++) {
            int x = arr.get(i);
            if (K - x > 0) {
                if (exist[K - x])
                    return true;
                exist[x] = true;
            }

        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        N = in.nextInt();
        K = in.nextInt();

        for (int i = 0; i < N; i++) {
            int var = in.nextInt();
            arr.add(var);
        }

        // TODO: read the array and call method existPairWithSumK() you want to try

        String ans = existPairWithSumKAC() ? "Ya" : "Tidak";
        out.println(ans);

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