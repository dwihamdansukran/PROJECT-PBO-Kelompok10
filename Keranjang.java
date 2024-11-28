import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Keranjang {
    private ArrayList<Barang> barang; // List untuk menyimpan semua barang yang tersedia

    private ArrayList<Barang> listKeranjang; // List untuk menyimpan barang-barang di keranjang belanja
    private Scanner s = new Scanner(System.in);

    // Konstruktor untuk menginisialisasi list
    public Keranjang() {
        this.barang = new ArrayList<Barang>();
        this.listKeranjang = new ArrayList<Barang>();
    }

    // Membaca informasi barang dari file "List Barang.txt"
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

    // Membaca barang-barang di keranjang belanja dari file "Keranjang.txt"
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

                this.listKeranjang.add(keranjang1);
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

    // Menampilkan barang-barang di keranjang belanja
    public void lihatKeranjang() {
        this.bacaDatabaseKeranjang();
        System.out.println("DATA KERANJANG ANDA : ");
        listKeranjang.forEach((keranjang1) -> {
            System.out.println("kode barang : " + keranjang1.getKodeBarang());
            System.out.println("nama barang : " + keranjang1.getNamaBarang());
            System.out.println("stok : " + keranjang1.getStok());
            System.out.println("harga : " + keranjang1.getHarga());
            System.out.println(" ");
        });

        System.out.println("Jumlah barang dalam keranjang: " + listKeranjang.size());
        this.listKeranjang.clear();
    }

    // Menambahkan barang ke keranjang belanja
    public void tambahBarangKeKeranjang() {
        this.bacaDatabase();
        this.bacaDatabaseKeranjang();

        System.out.println("-----[ MASUKKAN BARANG KE KERANJANG ]-----");
        System.out.print("kode barang : ");
        String kodebarang = s.nextLine();
        System.out.print("jumlah barang : ");
        int jumlahBarang = Integer.parseInt(s.nextLine());

        boolean barangDitemukan = false;
        for (int i = 0; i < this.barang.size(); i++) {
            if (kodebarang.equals(this.barang.get(i).getKodeBarang())) {
                Barang barangDiList = this.barang.get(i);
                int stokTersedia = barangDiList.getStok();

                if (stokTersedia >= jumlahBarang) {
                    Barang newBarangKeranjang = new Barang();
                    newBarangKeranjang.setKodeBarang(barang.get(i).getKodeBarang());
                    newBarangKeranjang.setNamaBarang(barang.get(i).getNamaBarang());
                    newBarangKeranjang.setStok(jumlahBarang);
                    newBarangKeranjang.setHarga(barang.get(i).getHarga());

                    System.out.println("Barang " + kodebarang + " berhasil ditambahkan ke dalam keranjang.");

                    this.listKeranjang.add(newBarangKeranjang);
                    this.writeDatabasekeranjang();
                    barangDitemukan = true;
                    break;
                } else {
                    System.out.println("Stok tidak mencukupi untuk jumlah barang yang anda inginkan.");
                    System.out.println("Stok yang tersedia: " + stokTersedia);
                    barangDitemukan = true;
                    break;
                }

            }
        }

        if (!barangDitemukan) {
            System.out.println("Barang dengan kode " + kodebarang + " tidak ditemukan");
        }

        this.listKeranjang.clear();

    }

    // Menulis ulang barang-barang di keranjang belanja ke file "Keranjang.txt"
    public void writeDatabasekeranjang() {

        BufferedWriter dataBaseKeranjang = null;
        String pathDatabaseKeranjang = "Keranjang.txt";

        try {
            dataBaseKeranjang = new BufferedWriter(new FileWriter(pathDatabaseKeranjang));
            for (Barang keranjang1 : this.listKeranjang) {
                String line = String.format("%s %s %d %d", keranjang1.getKodeBarang(), keranjang1.getNamaBarang(),
                        keranjang1.getStok(), keranjang1.getHarga());

                dataBaseKeranjang.write(line);
                dataBaseKeranjang.newLine();
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            try {
                if (dataBaseKeranjang != null) {
                    dataBaseKeranjang.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
        this.listKeranjang.clear();
    }

    // Mendapatkan indeks barang di keranjang belanja berdasarkan kode barang
    private int getIndexKeranjangByKodeBarang(String kodeBarang) {
        for (int i = 0; i < this.listKeranjang.size(); i++) {
            if (kodeBarang.equals(this.listKeranjang.get(i).getKodeBarang())) {
                return i;
            }
        }
        return -1;
    }

    // Mendapatkan stok barang dari list barang berdasarkan kode barang
    private int getStokBarangFromListBarang(String kodeBarang) {
        int stok = 0;

        for (Barang barang : this.barang) {
            if (kodeBarang.equals(barang.getKodeBarang())) {
                stok = barang.getStok();
                break;
            }
        }
        return stok;
    }

    // Mengedit jumlah barang di keranjang belanja
    public void editBarangKeranjang() {
        this.bacaDatabaseKeranjang();
        BufferedWriter editkeranjang = null;
        Barang editBarang = new Barang();
        String kodeBarang;

        System.out.println("------[ EDIT BARANG DIKERANJANG ]------");
        System.out.print("kode barang: ");
        kodeBarang = s.nextLine();

        try {
            int indexBarang = getIndexKeranjangByKodeBarang(kodeBarang);

            if (indexBarang != -1) {
                System.out.println("Edit barang " + this.listKeranjang.get(indexBarang).getNamaBarang()
                        + " di keranjang");
                System.out.println("1. Edit jumlah barang");
                System.out.println("2. Tambah jumlah barang");

                int pilihan = Integer.parseInt(s.nextLine());

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan jumlah terbaru: ");
                        int newJumlah = Integer.parseInt(s.nextLine());

                        int stokBarang = getStokBarangFromListBarang(kodeBarang);

                        if (newJumlah <= stokBarang) {
                            editBarang.setKodeBarang(kodeBarang);
                            editBarang.setNamaBarang(this.listKeranjang.get(indexBarang).getNamaBarang());
                            editBarang.setStok(newJumlah);
                            editBarang.setHarga(this.listKeranjang.get(indexBarang).getHarga());

                            this.listKeranjang.set(indexBarang, editBarang);
                        } else {
                            System.out.println("Jumlah yang dimasukkan melebihi stok yang tersedia.");
                        }
                        break;

                    case 2:
                        System.out.print("Masukkan jumlah tambahan: ");
                        int tambahanJumlah = Integer.parseInt(s.nextLine());

                        int jumlahSebelumnya = this.listKeranjang.get(indexBarang).getStok();
                        int jumlahBaru = jumlahSebelumnya + tambahanJumlah;

                        int stok = getStokBarangFromListBarang(kodeBarang);

                        if (jumlahBaru <= stok) {
                            this.listKeranjang.get(indexBarang).setStok(jumlahBaru);
                        } else {
                            System.out.println("Jumlah yang dimasukkan melebihi stok yang tersedia.");
                        }
                        break;

                    default:
                        System.out.println("Pilihan tidak valid");
                        break;
                }
            } else {
                System.out.println(
                        "Barang tidak ditemukan dalam keranjang. Tambahkan barang baru.");
                System.out.print(
                        "Masukkan jumlah barang yang ingin ditambahkan: ");
                int jumlahBaru = Integer.parseInt(s.nextLine());

                for (int i = 0; i < this.barang.size(); i++) {
                    if (kodeBarang.equals(this.barang.get(i).getKodeBarang())) {
                        Barang newBarangKeranjang = new Barang();
                        newBarangKeranjang.setKodeBarang(barang.get(i).getKodeBarang());
                        newBarangKeranjang.setNamaBarang(barang.get(i).getNamaBarang());
                        newBarangKeranjang.setStok(jumlahBaru);
                        newBarangKeranjang.setHarga(barang.get(i).getHarga());

                        this.listKeranjang.add(newBarangKeranjang);
                        break;
                    }
                }
            }

            this.writeDatabasekeranjang();
            this.listKeranjang.clear();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (editkeranjang != null) {
                try {
                    editkeranjang.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    // Menghapus barang dari keranjang belanja
    public void hapusBarangDariKeranjang() {
        this.bacaDatabaseKeranjang();

        System.out.print("Masukkan kode barang yang ingin dihapus dari keranjang: ");
        String kodeBarang = s.nextLine();

        boolean found = false;

        // Iterasi melalui listKeranjang dan hapus barang jika kodeBarang sesuai
        for (int i = 0; i < this.listKeranjang.size(); i++) {
            if (kodeBarang.equals(this.listKeranjang.get(i).getKodeBarang())) {
                this.listKeranjang.remove(i);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Barang berhasil dihapus dari keranjang.");
            this.writeDatabasekeranjang(); // Menulis ulang database setelah menghapus barang
        } else {
            System.out.println("Barang dengan kode " + kodeBarang + " tidak ditemukan dalam keranjang.");
        }

        this.listKeranjang.clear();
    }

    // Menghitung total harga barang di keranjang belanja
    public int totalHarga() {
        this.bacaDatabaseKeranjang();

        int jumlahHarga = 0; // Inisialisasi jumlahHarga menjadi 0

        for (int i = 0; i < this.listKeranjang.size(); i++) {
            int jumlahStok = this.listKeranjang.get(i).getStok(); // Inisialisasi jumlahStok di dalam loop
            int harga = this.listKeranjang.get(i).getHarga(); // Inisialisasi harga di dalam loop
            jumlahHarga += jumlahStok * harga; // Akumulasi total harga
        }

        return jumlahHarga;
    }

    // Mendapatkan list barang di keranjang belanja
    public ArrayList<Barang> getListKeranjang() {
        this.bacaDatabaseKeranjang();
        return this.listKeranjang;
    }

    // Membersihkan keranjang belanja
    public void bersihkanKeranjang() {
        this.bacaDatabaseKeranjang();

        if (!this.listKeranjang.isEmpty()) {
            this.listKeranjang.clear();
            System.out.println("Keranjang berhasil dibersihkan.");
            this.writeDatabasekeranjang();

    } else {
        System.out.println("Keranjang sudah kosong.");
    }
  }

}