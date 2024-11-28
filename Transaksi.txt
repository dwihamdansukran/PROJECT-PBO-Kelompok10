import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaksi {
    private Customer akun;
    private ArrayList<Barang> barang;
    private boolean accept;
    private ArrayList<String> transaksi;

    // Konstruktor default
    public Transaksi() {
        this.akun = new Customer();
        this.barang = new ArrayList<Barang>();
        this.transaksi = new ArrayList<>();
    }

    // Konstruktor dengan parameter customer
    public Transaksi(Customer customer) {
        this.akun = new Customer(customer.getId(), customer.getPassword());
    }

    // Metode untuk mendapatkan informasi customer
    public Customer getCustomer() {
        return this.akun;
    }

    // Metode untuk membaca database transaksi dari file "transaksi.txt"
    public void bacaDatabaseTransaksi() {
        BufferedReader databaseTransaksi = null;

        try {
            File fileTransaksi = new File("transaksi.txt");
            databaseTransaksi = new BufferedReader(new FileReader(fileTransaksi));
            String line;

            while ((line = databaseTransaksi.readLine()) != null) {
                transaksi.add(line);
            }

        } catch (IOException e) {
            System.out.println("Error reading transaction database: " + e.getMessage());
        } finally {
            try {
                if (databaseTransaksi != null) {
                    databaseTransaksi.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing BufferedReader: " + e.getMessage());
            }
        }
    }

    // Metode untuk menandai bahwa transaksi telah diterima oleh admin
    public void cekTransaksi() {
        try {
            FileWriter myWriter = new FileWriter("cek.txt");
            String line = "true";
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    // Metode untuk menandai bahwa belum ada transaksi yang diterima oleh admin
    public void cekSudahTransaksi() {
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

    // Metode untuk membaca status transaksi dari file "cek.txt"
    public String bacaCekTransaksi() {
        StringBuilder data = new StringBuilder();

        try {
            File myObj = new File("cek.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                data.append(myReader.nextLine());
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return data.toString();
    }

    // Metode untuk mendapatkan informasi transaksi
    public ArrayList<String> getTransaksi() {
        return this.transaksi;
    }

    // Metode untuk menampilkan informasi transaksi ke layar
    public void tampilkanTransaksi() {
        this.bacaDatabaseTransaksi();

        int index = 0;
        // Menulis data transaksi ke layar
        for (String tr : this.transaksi) {
            String token = this.transaksi.get(index);
            String[] token1 = token.split(" ");
            System.out.println("ID Transaksi : " + token1[0] + "\n");
            System.out.println("Total Harga : " + token1[1] + "\n");
            index++;
        }
    }
}
