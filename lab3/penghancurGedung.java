// package lab3;

import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;

public class penghancurGedung {
    private static InputReader in;
    private static PrintWriter out;

    private static boolean arahKanan = true;
    private static long nilai = 0;
    private static int index = 0;
    private static boolean win = false;
    public static int X;
    public static LinkedList<LinkedList<Integer>> gedung = new LinkedList<LinkedList<Integer>>();

    // Metode GA
    static String GA() {
        arahKanan = !arahKanan;
        if (arahKanan)
            return "KANAN";
        return "KIRI";
    }

    // Metode S
    static void S(int Si, long T) {
        if (win) {
            out.println("MENANG");
        } else {
            int temp = 0;
            if (Si >= gedung.get(index).size()) {
                Si = gedung.get(index).size();
                for (int i = 0; i < Si; i++) {
                    temp += gedung.get(index).pop();
                }
                gedung.remove(index);
                X--;
                // System.out.println("Nilai X adalah " + X);
                if (!arahKanan && X != 0) {
                    index = (index + X - 1) % X;
                } else if (X != 0) {
                    index = index % X;
                }
                // System.out.println("Nilai index sekarang " + index);
            } else {
                for (int i = 0; i < Si; i++) {
                    temp += gedung.get(index).pop();
                }

                if (arahKanan) {
                    index = (index + 1) % X;
                } else {
                    index = (index + X - 1) % X;
                }
                // System.out.println("Nilai index sekarang " + index);
            }

            nilai += temp;
            if (nilai >= T || X == 0) {
                win = true;
                out.println("MENANG");
            } else {
                out.println(temp);
            }

        }

    }

    // Template
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new InputReader(inputStream);
        OutputStream outputStream = System.out;
        out = new PrintWriter(outputStream);

        // Read input
        long T = in.nextLong();

        // buat list
        X = in.nextInt();

        int C = in.nextInt();
        int Q = in.nextInt();

        for (int i = 0; i < X; i++) {
            LinkedList<Integer> lantai = new LinkedList<Integer>();
            // Insert into ADT
            for (int j = 0; j < C; j++) {
                int Ci = in.nextInt();

                lantai.add(Ci);
            }
            gedung.add(lantai);
        }

        // Process the query
        for (int i = 0; i < Q; i++) {
            String perintah = in.next();
            if (perintah.equals("GA")) {
                out.println(GA());
            } else if (perintah.equals("S")) {
                int Si = in.nextInt();
                S(Si, T);
            }
        }

        // don't forget to close the output
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

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}
