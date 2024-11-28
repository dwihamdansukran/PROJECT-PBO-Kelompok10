import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Class untuk mengelola daftar barang dan melakukan operasi seperti menambah, mengedit, dan menghapus barang.
public class ListBarang {
    private ArrayList<Barang> barang;      // Daftar barang di inventaris
    private ArrayList<Barang> keranjang;   // Daftar barang di keranjang belanja

    // Konstruktor untuk menginisialisasi daftar barang.
    public ListBarang() {
        this.barang = new ArrayList<Barang>();
        this.keranjang = new ArrayList<Barang>();
    }

    // Metode untuk membaca data barang dari file database.
    private void bacaDatabase() {
        BufferedReader databaseBarang = null;
        String bacaDatabeseBarang;
        String pathDatabase = "List Barang.txt";
        try {
            databaseBarang = new BufferedReader(new FileReader(pathDatabase));
            while ((bacaDatabeseBarang = databaseBarang.readLine()) != null) {
                Barang barang1 = new Barang();
                String[] token = bacaDatabeseBarang.split(" ");

                barang1.setKodeBarang(token[0]);
                barang1.setNamaBarang(token[1]);
                barang1.setStok(Integer.parseInt(token[2]));
                barang1.setHarga(Integer.parseInt(token[3]));

                this.barang.add(barang1);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (databaseBarang != null) {
                    databaseBarang.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Metode untuk menampilkan daftar barang.
    public void tampilkanListBarang() {
        this.bacaDatabase();

        barang.forEach((barang1) -> {
            System.out.println("kode barang : " + barang1.getKodeBarang());
            System.out.println("nama barang : " + barang1.getNamaBarang());
            System.out.println("stok barang : " + barang1.getStok());
            System.out.println("harga barang : " + barang1.getHarga());
            System.out.println(" ");
        });

        this.barang.clear();
    }

    // Metode untuk menambah barang ke inventaris.
    public void tambahBarang() {
        this.bacaDatabase();

        Scanner s = new Scanner(System.in);

        System.out.print("masukkan kode barang : ");
        String kodeBarang = s.nextLine();

        System.out.print("masukkan nama barang : ");
        String namaBarang = s.nextLine();

        System.out.print("masukkan  stok : ");
        int stok = s.nextInt();

        System.out.print("masukkan harga : ");
        int harga = s.nextInt();

        Barang newBarang = new Barang();

        newBarang.setKodeBarang(kodeBarang);
        newBarang.setNamaBarang(namaBarang);
        newBarang.setStok(stok);
        newBarang.setHarga(harga);

        this.barang.add(newBarang);
        this.writeDatabase();
        this.barang.clear();
    }

    // Metode untuk menulis data barang ke file database.
    private void writeDatabase() {
        BufferedWriter databaseBarang = null;
        String pathDatabase = "List Barang.txt";

        try {
            databaseBarang = new BufferedWriter(new FileWriter(pathDatabase));
            for (Barang barang1 : this.barang) {
                String line = String.format("%s %s %d %d",
                        barang1.getKodeBarang(),
                        barang1.getNamaBarang(),
                        barang1.getStok(),
                        barang1.getHarga());

                databaseBarang.write(line);
                databaseBarang.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (databaseBarang != null) {
                    databaseBarang.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Metode untuk mengedit data barang di inventaris.
    public void editBarang() {
        this.bacaDatabase();
        BufferedReader bacaDatabase = null;
        Scanner s = new Scanner(System.in);
        Barang barangEdit = new Barang();
        String kodeBarang;
        System.out.println("----[ EDIT BARANG ]----");
        System.out.print("masukkan kode barang : ");
        kodeBarang = s.nextLine();

        try {

            for (int i = 0; i < this.barang.size(); i++) {
                if (kodeBarang.equals(this.barang.get(i).getKodeBarang())) {
                    System.out.println("edit barang " + this.barang.get(i).getNamaBarang());
                    System.out.print("masukkan stok : ");
                    String newStok = s.nextLine();
                    System.out.print("masukkan harga : ");
                    String newHarga = s.nextLine();

                    barangEdit.setKodeBarang(kodeBarang);
                    barangEdit.setNamaBarang(this.barang.get(i).getNamaBarang());
                    barangEdit.setStok(Integer.parseInt(newStok));
                    barangEdit.setHarga(Integer.parseInt(newHarga));

                    this.barang.set(i, barangEdit);

                }
            }
            this.writeDatabase();
            this.barang.clear();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (bacaDatabase != null) {
                try {
                    bacaDatabase.close();
                } catch (IOException e) {
                    System.out.println((e));
                }
            }
        }
    }

    // Metode untuk menghapus barang dari inventaris.
    public void hapusBarang() {
        this.bacaDatabase();
        Scanner s = new Scanner(System.in);
        String kodeBarang;
        System.out.println("----[ HAPUS BARANG ]----");
        System.out.print("masukkan kode barang : ");
        kodeBarang = s.nextLine();

        for (int i = 0; i < this.barang.size(); i++) {
            if (kodeBarang.equals(this.barang.get(i).getKodeBarang())) {
                this.barang.remove(i);
            }
        }
        this.writeDatabase();
        this.barang.clear();
    }

    // Metode untuk membaca data barang dari file database keranjang.
    public void bacaDatabaseKeranjang() {
        BufferedReader databaseKeranjang = null;
        String bacaDatabaseKeranjang;
        String pathReaderKeranjang = "Keranjang.txt";

        try {
            databaseKeranjang = new BufferedReader(new FileReader(pathReaderKeranjang));
            while ((bacaDatabaseKeranjang = databaseKeranjang.readLine()) != null) {
                Barang keranjang1 = new Barang();
                String[] token = bacaDatabaseKeranjang.split(" ");

                keranjang1.setKodeBarang(token[0]);
                keranjang1.setNamaBarang(token[1]);
                keranjang1.setStok(Integer.parseInt(token[2]));
                keranjang1.setHarga(Integer.parseInt(token[3]));

                this.keranjang.add(keranjang1);
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (databaseKeranjang != null) {
                    databaseKeranjang.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    // Metode untuk melakukan transaksi barang.
    public void transaksiBarang() {
        this.bacaDatabase();
        this.bacaDatabaseKeranjang();

        for (Barang barangTransaksi : this.barang) {
            for (Barang keranjangItem : this.keranjang) {
                if (barangTransaksi.getKodeBarang().equals(keranjangItem.getKodeBarang())) {
                    Barang barangTransaksiBaru = new Barang();
                    barangTransaksiBaru.setKodeBarang(barangTransaksi.getKodeBarang());
                    barangTransaksiBaru.setNamaBarang(barangTransaksi.getNamaBarang());

                    // Hitung sisa stok setelah transaksi
                    int sisaStok = barangTransaksi.getStok() - keranjangItem.getStok();

                    // Pastikan sisa stok tidak negatif
                    if (sisaStok >= 0) {
                        barangTransaksiBaru.setStok(sisaStok);
                        barangTransaksiBaru.setHarga(barangTransaksi.getHarga());

                        this.barang.set(this.barang.indexOf(barangTransaksi), barangTransaksiBaru);
                    } else {
                        System.out.println("Stok tidak cukup untuk produk dengan kode " + barangTransaksiBaru.getKodeBarang());
                    }
                }
            }
        }

        // Perbarui database inventaris
        this.writeDatabase();
    }
}
