import java.io.*;
import java.util.*;

public class bufan {
  private static InputReader in;
  private static PrintWriter out;
  private static Wahana[] daftarWahana;
  private static Pengunjung[] daftarPengunjung;
  private static Deque<Pengunjung> daftarKeluar = new ArrayDeque<>();
  private static int[][][] dp;
  private static ArrayList<ArrayList<Pengunjung>> antre = new ArrayList<ArrayList<Pengunjung>>();
  private static int[][][] bk;

  public static int A(Pengunjung pengunjung, Wahana wahana) {
    if (pengunjung.uang < wahana.harga)
      return -1;

    Pengunjung copyPengunjung = new Pengunjung(pengunjung.id, pengunjung.totalBermain);
    if (pengunjung.tipe.equals("R")) {
      wahana.antreanReguler.add(copyPengunjung);
    } else {
      wahana.antreanFastTrack.add(copyPengunjung);
    }
    printAntrian(wahana);
    return wahana.antreanReguler.size() + wahana.antreanFastTrack.size();

  }

  public static void printAntrian(Wahana wahana) {
    PriorityQueue<Pengunjung> salinanAntreanFastTrack = new PriorityQueue<>(wahana.antreanFastTrack);
    PriorityQueue<Pengunjung> salinanAntreanReguler = new PriorityQueue<>(wahana.antreanReguler);
    System.out.println("Yang lagi ngantri: ");
    System.out.println("Fast track:");
    while (!salinanAntreanFastTrack.isEmpty()) {
      Pengunjung p = salinanAntreanFastTrack.poll();
      System.out.println(p.id);
    }
    System.out.println("Reguler: ");
    while (!salinanAntreanReguler.isEmpty()) {
      Pengunjung p = salinanAntreanReguler.poll();
      System.out.println(p.id);
    }
    System.out.println("------------------");
  }

  public static String E(Wahana wahana) {
    int kapasitasMain = wahana.kapasitasPengunjung;
    int jumlahFT = wahana.kapasitasPengunjung * wahana.persentasePengunjungFT / 100;
    String hasil = "";

    while (kapasitasMain > 0 && jumlahFT > 0 && !wahana.antreanFastTrack.isEmpty()) {
      Pengunjung p = wahana.antreanFastTrack.poll();
      main(p, wahana);
      cekKeluar(p);
      jumlahFT--;
      kapasitasMain--;
      hasil += Integer.toString(p.id);
    }
    while (kapasitasMain > 0 && !wahana.antreanReguler.isEmpty()) {
      Pengunjung p = wahana.antreanFastTrack.poll();
      main(p, wahana);
      cekKeluar(p);
      kapasitasMain--;
    }
    while (kapasitasMain > 0 && !wahana.antreanFastTrack.isEmpty()) {
      Pengunjung p = wahana.antreanFastTrack.poll();
      main(p, wahana);
      cekKeluar(p);
      kapasitasMain--;
    }

    return "hey";
  }

  public static void main(Pengunjung p, Wahana wahana) {
    p.point += wahana.point;
    p.totalBermain++;
    p.uang -= wahana.harga;
  }

  public static void cekKeluar(Pengunjung p) {
    if (p.uang <= 0) {
      daftarKeluar.add(p);
    }
  }

  public static int S(Pengunjung pengunjung, Wahana wahana) {
    return 1;
  }

  public static int F(int p) {
    return 2;
  }

  // public static List<Integer> O(int id_pengunjung) {
  // return
  // }

  public static void main(String[] args) {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    // Initialize Wahana
    int M = in.nextInt();
    daftarWahana = new Wahana[M];
    int harga, point, kapasitasPengunjung, persentasePengunjungFT;
    for (int i = 0; i < M; i++) {
      harga = in.nextInt();
      point = in.nextInt();
      kapasitasPengunjung = in.nextInt();
      persentasePengunjungFT = in.nextInt();

      daftarWahana[i] = new Wahana(harga, point, kapasitasPengunjung, persentasePengunjungFT);

    }

    // Initialize Pengunjung
    int N = in.nextInt();
    daftarPengunjung = new Pengunjung[N];
    String tipe;
    int uang, maxUang = 0;
    for (int i = 0; i < N; i++) {
      tipe = in.next();
      uang = in.nextInt();

      maxUang = Math.max(maxUang, uang);
      daftarPengunjung[i] = new Pengunjung(tipe, uang);
    }

    // Precompute DP

    // Query
    int T = in.nextInt();
    String query;
    for (int tc = 0; tc < T; tc++) {
      query = in.next();

      if (query.equals("A")) {
        int id_pengunjung = in.nextInt();
        int id_wahana = in.nextInt();
        out.println(A(daftarPengunjung[id_pengunjung - 1], daftarWahana[id_wahana - 1]));
      }

      if (query.equals("E")) {
        int id_wahana = in.nextInt();
        String result = E(daftarWahana[id_wahana - 1]);
        if (result.equals("")) {
          out.println(-1);
        } else {
          out.println(result.trim()); // menghilangkan spasi ekstra di akhir
        }
      }

      if (query.equals("S")) {
        int id_pengunjung = in.nextInt();
        int id_wahana = in.nextInt();
        out.println(S(daftarPengunjung[id_pengunjung - 1], daftarWahana[id_wahana - 1]));
      }

      if (query.equals("F")) {
        int p = in.nextInt();
        out.println(F(p));
      }

      // if (query.equals("O")) {
      // int id_pengunjung = in.nextInt();
      // List<Integer> result = O(id_pengunjung);
      // for (int i = 0; i < result.size(); i++) {
      // out.print(result.get(i));
      // out.print(i + 1 == result.size() ? '\n' : ' ');
      // }
      // }
    }

    out.close();
  }

  static class Wahana {
    private static int idInc = 1;
    int id, harga, point, kapasitasPengunjung, persentasePengunjungFT, antrianSekarang;
    PriorityQueue<Pengunjung> antreanReguler = new PriorityQueue<>();
    PriorityQueue<Pengunjung> antreanFastTrack = new PriorityQueue<>();

    Wahana(int harga, int point, int kapasitasPengunjung, int persentasePengunjungFT) {
      this.id = idInc++;
      this.harga = harga;
      this.point = point;
      this.kapasitasPengunjung = kapasitasPengunjung;
      this.persentasePengunjungFT = persentasePengunjungFT;
      this.antrianSekarang = 0;
    }
  }

  static class Pengunjung implements Comparable<Pengunjung> {

    private static int idInc = 1;
    int id, uang;
    String tipe;
    int point, totalBermain = 0;
    boolean isExit = false;

    Pengunjung(String tipe, int uang) {
      this.id = idInc++;
      this.tipe = tipe;
      this.uang = uang;
    }

    Pengunjung(int id, int totalBermain) {
      this.id = id;
      this.totalBermain = totalBermain;
    }

    @Override
    public int compareTo(Pengunjung other) {
      if (this.totalBermain < other.totalBermain)
        return -1;
      else if (this.totalBermain == other.totalBermain) {
        if (this.id < other.id)
          return -1;
        return 1;
      }
      return 1;
    }
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