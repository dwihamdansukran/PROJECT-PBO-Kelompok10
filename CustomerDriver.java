import java.util.Scanner;

public class CustomerDriver extends Driver {
    public Customer akun;
    public Transaksi transaksi;
    public ListBarang listBarang;
    private Invoice invoice;

    public CustomerDriver(Customer customer) {
        this.akun = new Customer(customer.getId(), customer.getPassword());
        this.transaksi = new Transaksi(this.akun);
        this.listBarang = new ListBarang();
        this.invoice = new Invoice(); // Pastikan objek Invoice diinisialisasi pada saat yang benar
    }

    public void menu() {
        Scanner s = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("----[ DAFTAR MENU ]----:");
            System.out.println(" ");
            System.out.println("1. Lihat list barang");
            System.out.println("2. Masukkan barang ke keranjang");
            System.out.println("3. Lihat Keranjang");
            System.out.println("4. Edit jumlah barang");
            System.out.println("5. Hapus barang di keranjang");
            System.out.println("6. Checkout barang");
            System.out.println("7. Invoice transaksi");
            System.out.println("8. Invoice transaksi selesai");
            System.out.println("9. Logout");
            System.out.print("Masukkan pilihan: ");
            pilihan = s.nextInt();
            System.out.println(" ");

            if (pilihan == 1) {
                // Melihat barang
                listBarang.tampilkanListBarang();
            }
            if (pilihan == 2) {
                // Memasukkan barang ke keranjang
                this.akun.getKeranjang().tambahBarangKeKeranjang();
            }
            if (pilihan == 3) {
                // Melihat keranjang
                this.akun.getKeranjang().lihatKeranjang();
            }
            if (pilihan == 4) {
                // Mengedit jumlah barang di keranjang
                this.akun.getKeranjang().editBarangKeranjang();
            }
            if (pilihan == 5) {
                // Menghapus barang dari keranjang
                this.akun.getKeranjang().hapusBarangDariKeranjang();
            }
            if (pilihan == 6) {
                // Checkout barang
                this.invoice.menu();
            }
            if (pilihan == 7) {
                if (this.transaksi.bacaCekTransaksi().equalsIgnoreCase("true")) {
                    System.out.println("Invoice tercetak");
                    this.invoice.invoiceTerbaru();
                    this.invoice.bacaInvoice();
                    this.transaksi.getCustomer().getKeranjang().bersihkanKeranjang();
                } else {
                    System.out.println("Maaf, barang anda belum diterima oleh admin");
                }
            }
            if (pilihan == 8) {
                this.invoice.bacaInvoiceSelesai();
            }

        } while (pilihan != 9);
        // s.close();
    }
}
