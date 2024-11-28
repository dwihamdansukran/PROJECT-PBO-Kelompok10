import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Cod extends Pembayaran {
     
    private static int counter = 0;
    private Keranjang keranjang;
    Scanner s = new Scanner(System.in);

    public Cod() {
        keranjang = new Keranjang();
        generateTransactionId();
    }

    public Cod(String id, int totalHarga) {
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
    public void metodePembayaran(){
        int totalHarga = this.keranjang.totalHarga();
        System.out.println("pembayaran melalui COD");
        System.out.println(" ");
        System.out.println("Tekan 1 jika anda telah menerima barang pesanan anda");
        int input = s.nextInt();

        if(input == 1){
            System.out.println("Menunggu transaksi diterima oleh admin");
            System.out.println("Transaction ID : "+ id);
            this.writeTransaksi(totalHarga);
            this.cekTransaksi();
        }
    }

    public void writeTransaksi(int totalHarga){

        try {
            FileWriter myWriter = new FileWriter("transaksi.txt");
                String line = String.format("%s %d",id, totalHarga);
            myWriter.write(line);
            myWriter.close();
           
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }

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
