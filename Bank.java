import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Bank extends Pembayaran {

    private static int counter = 0;
    private Keranjang keranjang;
    Scanner s = new Scanner(System.in);

    public Bank() {
        keranjang = new Keranjang();
        generateTransactionId();
    }

    public Bank(String id, int totalHarga) {
        super(id, totalHarga);
        keranjang = new Keranjang();
        generateTransactionId();
    }

    private void generateTransactionId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        id = timestamp + "_" + counter;
        counter++;
    }

    @Override
    public void metodePembayaran() {
        totalHarga = this.keranjang.totalHarga();
        System.out.println("Cara pembayaran melalui transaksi bank");
        System.out.println("No rek/virtual account: 720 313 0 222");
        System.out.println(" ");
        System.out.println("1. Masuk ke menu mobile mbanking, kemudian pilih pembayaran");
        System.out.println("2. Masukkan no rek / virtual account 720 313 0 222");
        System.out.println("3. Jumlah pembayaran sebesar " + totalHarga);
        System.out.println("4. Periksa apakah jumlah pembayaran anda telah sesuai");
        System.out.println("5. Masukkan PIN anda dan kemudian pilih SEND.");
        System.out.println(" ");
        System.out.println("Tekan 1 jika anda telah selesai proses transaksi");
        int input = s.nextInt();

        if (input == 1) {
            System.out.println("Menunggu transaksi diterima oleh admin");
            System.out.println("Transaction ID : " + id);
            this.writeTransaksi(totalHarga);
            this.cekTransaksi();
        }
    }

    public void writeTransaksi(int totalHarga) {
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

    public void cekTransaksi() {
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
