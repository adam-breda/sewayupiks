package id.praktikumprogmob.sewayupiks.model;

public class Sewayu {
    private Integer id;
    private Integer userId;
    private String kategori;
    private String jenisKendaraan;
    private Integer hargaSewaPerHari;
    private String keperluan;
    private String tanggalAwalSewa;
    private String tanggalAkhirSewa;
    private Integer lamaSewa;
    private Integer ketersediaanBensin;
    private Integer hargaBensinPerLiter;
    private Integer totalBayar;

    public Sewayu(Integer userId, String kategori, String jenisKendaraan, Integer hargaSewaPerHari, String keperluan, String tanggalAwalSewa, String tanggalAkhirSewa, Integer lamaSewa, Integer ketersediaanBensin, Integer hargaBensinPerLiter, Integer totalBayar) {
        this.userId = userId;
        this.kategori = kategori;
        this.jenisKendaraan = jenisKendaraan;
        this.hargaSewaPerHari = hargaSewaPerHari;
        this.keperluan = keperluan;
        this.tanggalAwalSewa = tanggalAwalSewa;
        this.tanggalAkhirSewa = tanggalAkhirSewa;
        this.lamaSewa = lamaSewa;
        this.ketersediaanBensin = ketersediaanBensin;
        this.hargaBensinPerLiter = hargaBensinPerLiter;
        this.totalBayar = totalBayar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJenisKendaraan() {
        return jenisKendaraan;
    }

    public void setJenisKendaraan(String jenisKendaraan) {
        this.jenisKendaraan = jenisKendaraan;
    }

    public Integer getHargaSewaPerHari() {
        return hargaSewaPerHari;
    }

    public void setHargaSewaPerHari(Integer hargaSewaPerHari) {
        this.hargaSewaPerHari = hargaSewaPerHari;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public String getTanggalAwalSewa() {
        return tanggalAwalSewa;
    }

    public void setTanggalAwalSewa(String tanggalAwalSewa) {
        this.tanggalAwalSewa = tanggalAwalSewa;
    }

    public String getTanggalAkhirSewa() {
        return tanggalAkhirSewa;
    }

    public void setTanggalAkhirSewa(String tanggalAkhirSewa) {
        this.tanggalAkhirSewa = tanggalAkhirSewa;
    }

    public Integer getLamaSewa() {
        return lamaSewa;
    }

    public void setLamaSewa(Integer lamaSewa) {
        this.lamaSewa = lamaSewa;
    }

    public Integer getKetersediaanBensin() {
        return ketersediaanBensin;
    }

    public void setKetersediaanBensin(Integer ketersediaanBensin) {
        this.ketersediaanBensin = ketersediaanBensin;
    }

    public Integer getHargaBensinPerLiter() {
        return hargaBensinPerLiter;
    }

    public void setHargaBensinPerLiter(Integer hargaBensinPerLiter) {
        this.hargaBensinPerLiter = hargaBensinPerLiter;
    }

    public Integer getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(Integer totalBayar) {
        this.totalBayar = totalBayar;
    }
}
