import java.util.ArrayList;

public class Customer extends Akun {
    private Keranjang keranjang;
    private ArrayList<Invoice> invoiceSelesai;

    public Customer() {
        super("user", "user123");
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    public Customer(String id, String password) {
        super(id, password);
        this.keranjang = new Keranjang();
        this.invoiceSelesai = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public Keranjang getKeranjang() {
        return this.keranjang;
    }

    public ArrayList<Invoice> getInvoiceSelesai() {
        return this.invoiceSelesai;
    }
}