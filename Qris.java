import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Kelas Qris yang merupakan turunan dari kelas Pembayaran
public class Qris extends Pembayaran {
    
    // Variabel statis untuk menghitung jumlah transaksi
    private static int counter = 0;
    
    // Instance Keranjang untuk mengakses totalHarga dari keranjang
    private Keranjang keranjang;
    
    // Scanner untuk input pengguna
    Scanner s = new Scanner(System.in);

    // Konstruktor default
    public Qris() {
        keranjang = new Keranjang();
        generateTransactionId();
    }

    // Konstruktor dengan parameter id dan totalHarga
    public Qris(String id, int totalHarga){
        super(id, totalHarga);
        keranjang = new Keranjang();
        generateTransactionId();
    }

    // Metode untuk menghasilkan ID transaksi dengan format timestamp dan counter
    private void generateTransactionId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        id = timestamp + "_" + counter;
        counter++;
    }

    // Implementasi metodePembayaran() dari kelas Pembayaran
    @Override
    public void metodePembayaran(){
        totalHarga = this.keranjang.totalHarga();
        System.out.println("Cara pembayaran melalui QRIS");
        System.out.println(" ");
        System.out.println("₪₪ ₪₪  ₪₪₪");
        System.out.println(" ₪₪ ₪ ₪₪₪₪");
        System.out.println("  ₪ ₪₪₪  ₪");
        System.out.println(" ₪ ₪₪   ₪₪");
        System.out.println("  ₪₪₪    ₪");
        System.out.println("");
        System.out.println("Scan QR code di atas ini");
        System.out.println("Tekan 1 jika Anda telah selesai proses pembayaran");
        int input = s.nextInt();

        if(input == 1){
            System.out.println("Menunggu transaksi diterima oleh admin");
            System.out.println("Transaction ID : "+ id);
            this.writeTransaksi(totalHarga);
            this.cekTransaksi();
        }
    }

    // Metode untuk menulis data transaksi ke file "transaksi.txt"
    public void writeTransaksi(int totalHarga){
        try {
            FileWriter myWriter = new FileWriter("transaksi.txt");
            String line = String.format("%s %d", id, totalHarga);
            myWriter.write(line);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

    // Metode untuk menulis status transaksi ke file "cek.txt"
    public void cekTransaksi(){
        try {
            FileWriter myWriter = new FileWriter("cek.txt");
            String line = "false";
            myWriter.write(line);
            myWriter.close();
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } 
}
