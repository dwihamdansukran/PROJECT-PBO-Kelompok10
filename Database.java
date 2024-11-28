import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Kelas Tes untuk mengelola daftar barang, membaca dan menulis ke database
public class Database {
    ArrayList<Barang> list;

    // Konstruktor untuk inisialisasi ArrayList
    public Database() {
        this.list = new ArrayList<Barang>();
    }

    // Metode untuk membaca database dan memasukkan barang ke dalam list
    private void bacaDatabase(){
        BufferedReader databaseBarang = null;
        String bacaDatabaseBarang;
        String pathDatabase = "List Barang.txt";
        try{
            databaseBarang = new BufferedReader(new FileReader(pathDatabase));
            while((bacaDatabaseBarang = databaseBarang.readLine())!=null){
                Barang barang = new Barang();
                String[] token = bacaDatabaseBarang.split(" ");

                barang.setKodeBarang(token[0]);
                barang.setNamaBarang(token[1]);
                barang.setStok(Integer.parseInt(token[2]));
                barang.setHarga(Integer.parseInt(token[3]));

                this.list.add(barang);
            }
        }catch(IOException e){
            System.err.println(e);
        }finally{
            try{
                if(databaseBarang!=null){
                    databaseBarang.close();
                }
            }catch(IOException e){
                System.err.println(e);
            }
        }
    }

    // Metode untuk menampilkan daftar barang dari list
    public void tampilkanListBarang(){
        this.bacaDatabase();

        list.forEach((barang)->{
            System.out.println("Kode Barang : " +barang.getKodeBarang());
            System.out.println("Nama Barang : " +barang.getNamaBarang());
            System.out.println("Stok Barang : " +barang.getStok());
            System.out.println("Harga Barang : " +barang.getHarga());
            System.out.println(" ");
        });

        this.list.clear();
    }

    // Metode untuk menambah barang ke list dan menulis ke database
    public void tambahBarang(){
        this.bacaDatabase();

        Scanner s = new Scanner(System.in);

        System.out.print("Masukkan Kode Barang : ");
        String kodeBarang = s.nextLine();
        
        System.out.print("Masukkan Nama Barang : ");
        String namaBarang = s.nextLine();
        
        System.out.print("Masukkan Stok : ");
        int stok = s.nextInt();
        
        System.out.print("Masukkan Harga : ");
        int harga = s.nextInt();

        Barang newBarang = new Barang();

        newBarang.setKodeBarang(kodeBarang);
        newBarang.setNamaBarang(namaBarang);
        newBarang.setStok(stok);
        newBarang.setHarga(harga);

        this.list.add(newBarang);
        this.writeDatabase();
    }

    // Metode untuk menulis daftar barang ke database
    private void writeDatabase(){
        BufferedWriter databaseBarang = null;
        String pathDatabase = "List Barang.txt";

        try{
            databaseBarang = new BufferedWriter(new FileWriter(pathDatabase));
            for(Barang barang : this.list){
                String line = String.format("%s %s %d %d",
                barang.getKodeBarang(),
                barang.getNamaBarang(),
                barang.getStok(),
                barang.getHarga());

                databaseBarang.write(line);
                databaseBarang.newLine();
           }
        } catch(IOException e){
                System.out.println(e);
        }finally{
            try{
                if(databaseBarang != null){
                    databaseBarang.close();
                }
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    // Metode untuk mengedit barang dalam list dan menulis ke database
    public void editBarang(){
        this.bacaDatabase();
        Scanner s = new Scanner(System.in);
        Barang barangEdit = new Barang();
        String kodeBarang;
        System.out.println("---[ EDIT BARANG ]---");
        System.out.print("Masukkan Kode Barang : ");
        kodeBarang = s.nextLine();

        try {
            for(int i=0 ; i<this.list.size();i++){
                if(kodeBarang.equals(this.list.get(i).getKodeBarang())){
                    System.out.println("Edit barang" + this.list.get(i).getNamaBarang());
                    System.out.print("Masukkan Stok : ");
                    String newStok = s.nextLine();
                    System.out.print("Masukkan Harga : ");
                    String newHarga = s.nextLine();

                    barangEdit.setKodeBarang(kodeBarang);
                    barangEdit.setNamaBarang(this.list.get(i).getNamaBarang());
                    barangEdit.setStok(Integer.parseInt(newStok));
                    barangEdit.setHarga(Integer.parseInt(newHarga));

                    this.list.set(i, barangEdit);
                }
            }
            this.writeDatabase();
            this.list.clear();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Metode untuk menghapus barang dari list dan menulis ke database
    public void hapusBarang(){
        this.bacaDatabase();
        Scanner s = new Scanner(System.in);
        String kodeBarang;
        System.out.println("---[ HAPUS BARANG ]---");
        System.out.print("Masukkan Kode Barang : ");
        kodeBarang = s.nextLine();

        for(int i = 0 ; i<this.list.size();i++){
            if(kodeBarang.equals(this.list.get(i).getKodeBarang())){
                this.list.remove(i);
            }
        }
        this.writeDatabase();
        this.list.clear();
    }
}
