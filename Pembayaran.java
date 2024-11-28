// Kelas abstrak Pembayaran yang memiliki properti id dan totalHarga
public abstract class Pembayaran {
    protected String id;
    protected int totalHarga;

    // Konstruktor default untuk inisialisasi properti
    public Pembayaran(){
        this.id = " ";
        this.totalHarga = 0;
    }

    // Konstruktor dengan parameter id untuk inisialisasi properti
    public Pembayaran(String id){
        this.id = id;
        this.totalHarga = 0;
    }

    // Konstruktor dengan parameter totalHarga untuk inisialisasi properti
    public Pembayaran(int totalHarga){
        this.totalHarga = totalHarga;
        this.id = " ";
    }

    // Konstruktor dengan parameter id dan totalHarga untuk inisialisasi properti
    public Pembayaran(String id, int totalHarga){
        this.id = id;
        this.totalHarga = totalHarga;
    }

    // Metode getter untuk mendapatkan nilai id
    public String getId(){
        return this.id;
    }

    // Metode setter untuk mengatur nilai id
    public void setId(String Id){
        this.id = id;
    }

    // Metode getter untuk mendapatkan nilai totalHarga
    public int getTotalharga(){
        return this.totalHarga; // BUG: Seharusnya "return this.totalHarga;" tanpa " = totalHarga"
    }

    // Metode setter untuk mengatur nilai totalHarga
    public void setTotalHarga(int totalHarga){
        this.totalHarga = totalHarga;
    }

    // Metode abstrak yang akan diimplementasikan oleh kelas turunan untuk menentukan metode pembayaran
    abstract void metodePembayaran();
}
