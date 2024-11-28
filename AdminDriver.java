import java.util.ArrayList;
import java.util.Scanner;

public class AdminDriver extends Driver {
    private Admin akun;
    private ListBarang listBarang;
    private Transaksi transaksi;
    private ArrayList<Transaksi> listTransaksi;

    public void menu() {
        Scanner s = new Scanner(System.in);
        int pilihan;

        listBarang = new ListBarang();
        transaksi = new Transaksi();

        do {
            System.out.println("----[ ADMIN ]----");
            System.out.println(" ");
            System.out.println("1. Lihat list barang");
            System.out.println("2. Tambah barang");
            System.out.println("3. Edit barang");
            System.out.println("4. Hapus barang");
            System.out.println("5. Transaksi");
            System.out.println("6. Logout");
            System.out.print("Masukkan pilihan anda: ");
            pilihan = s.nextInt();
            System.out.println(" ");

            if (pilihan == 1) {
                listBarang.tampilkanListBarang();
            }

            if (pilihan == 2) {
                listBarang.tambahBarang();
            }

            if (pilihan == 3) {
                listBarang.editBarang();
            }

            if (pilihan == 4) {
                listBarang.hapusBarang();
            }

            if (pilihan == 5) {
                System.out.println("---[ TRANSAKSI ]---");
                System.out.println(" ");
                this.transaksi.tampilkanTransaksi();
                System.out.println(" ");
                System.out.println("1. Terima transaksi");
                System.out.println("2. Tolak transaksi");
                System.out.print("Masukkan pilihan anda : ");
                int choice = s.nextInt();

                if (choice == 1) {
                    this.transaksi.cekTransaksi();
                    listBarang.transaksiBarang();
                    System.out.println("Transaksi diterima");
                    System.out.println(" ");
                } else {
                    System.out.println("Transaksi ditolak");
                    System.out.println(" ");
                }
            }

        } while (pilihan != 6);

        s.close();
    }
}
