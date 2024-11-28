import java.util.Scanner;

// Kelas utama yang berfungsi sebagai titik masuk program
public class Main{

    // Variabel statis untuk menyimpan informasi akun
    public static Akun akun;
    public static Akun password;

    // Instance Driver untuk manajemen akun
    public Driver driverAkun;

    // Metode untuk menangani proses login
    public static void login(Akun akun, Akun password){
        // Membuat instansi kelas Admin dan Customer
        Admin admin1 = new Admin();
        Customer customer1 = new Customer();

        // Memeriksa apakah kredensial akun yang dimasukkan cocok dengan kredensial admin
        if((akun.getId().equals(admin1.getId())) && (akun.getPassword().equals(admin1.getPassword()))){
            System.out.println("Berhasil login sebagai admin");
            // Membuat instansi AdminDriver untuk fungsi khusus admin
            Driver driverAkun = new AdminDriver();
            driverAkun.menu();
        }

        // Memeriksa apakah kredensial akun yang dimasukkan cocok dengan kredensial customer
        if((akun.getId().equals(customer1.getId())) && (akun.getPassword().equals(customer1.getPassword()))){
            System.out.println("Berhasil login sebagai customer");
            // Membuat instansi CustomerDriver untuk fungsi khusus customer
            Driver driverAkun = new CustomerDriver(customer1);
            driverAkun.menu();
        }
    }

    // Metode utama di mana eksekusi program dimulai
    public static void main(String[] args) {
        // Scanner untuk input pengguna
        Scanner s = new Scanner(System.in);

        // Menginisialisasi objek Akun untuk informasi akun
        akun = new Akun();

        // Menampilkan pesan selamat datang dan prompt login
        System.out.println("Selamat datang di Sistem Perbelanjaan Online!");
        System.out.println("Silakan login untuk melanjutkan.");
        System.out.println("------[ LOGIN ]------");
        System.out.print("Masukkan Username: ");
        akun.setId(s.nextLine());
        System.out.print("Masukkan Password: ");
        akun.setPassword(s.nextLine());

        // Memanggil metode login untuk memverifikasi kredensial yang dimasukkan
        login(akun, password);

        // Menutup Scanner untuk mencegah kebocoran sumber daya
        s.close();
    }
}
