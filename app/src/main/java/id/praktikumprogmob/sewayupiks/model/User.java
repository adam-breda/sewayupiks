package id.praktikumprogmob.sewayupiks.model;

public class User {
    public Integer id;
    public String name;
    public String phoneNumber;
    public String email;
    public String password;
    public String alamat;
    public Integer usia;

    public User(Integer id, String name, String phoneNumber, String email, String password, String alamat, Integer usia) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.usia = usia;
    }

    public User(String name, String phoneNumber, String email, String password, String alamat, Integer usia) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.alamat = alamat;
        this.usia = usia;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Integer getUsia() {
        return usia;
    }

    public void setUsia(Integer usia) {
        this.usia = usia;
    }
}
