package id.praktikumprogmob.sewayupiks.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import id.praktikumprogmob.sewayupiks.model.Sewayu;
import id.praktikumprogmob.sewayupiks.model.User;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper (Context context) {
        super(context, "sewayu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, " +
                "phone_number TEXT, " +
                "email TEXT, " +
                "password TEXT," +
                "alamat TEXT," +
                "usia INTEGER)"
        );
        db.execSQL("CREATE TABLE sewayu(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "user_id INTEGER," +
                "kategori TEXT, "+
                "jenis_kendaraan TEXT, " +
                "harga_sewa_per_hari INTEGER, "+
                "keperluan TEXT, " +
                "tanggal_awal_sewa DATE, " +
                "tanggal_akhir_sewa DATE, " +
                "lama_sewa INTEGER, " +
                "ketersediaan_bensin INTEGER, " +
                "harga_bensin_per_liter, " +
                "total_bayar INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS sewayu");
    }

    //================================user================================//
    public boolean addUser (User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", user.getName());
        cv.put("phone_number", user.getPhoneNumber());
        cv.put("email", user.getEmail());
        cv.put("password", user.getPassword());
        cv.put("alamat", user.getAlamat());
        cv.put("usia", user.getUsia());

        return db.insert("user", null, cv) > 0;
    }

    public Cursor getUserIdByEmail (String email) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select id from " + "user" + " where email = '"+email+"' limit 1", null);
    }

    public Cursor getUserById (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "user" + " where id = "+id, null);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "name", "phone_number", "email", "password", "alamat", "usia"},
                "email" + "=?",
                new String[]{user.email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            User user1 = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getInt(6));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user",
                new String[]{"id", "name", "phone_number", "email", "password", "alamat", "usia"},//Selecting columns want to query
                "email" + "=?",
                new String[]{email},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            return true;
        }
        return false;
    }

    //================================sewayu================================//
    public boolean addSewayu (Sewayu sewayu) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", sewayu.getUserId());
        cv.put("kategori", sewayu.getKategori());
        cv.put("jenis_kendaraan", sewayu.getJenisKendaraan());
        cv.put("harga_sewa_per_hari", sewayu.getHargaSewaPerHari());
        cv.put("keperluan", sewayu.getKeperluan());
        cv.put("tanggal_awal_sewa", sewayu.getTanggalAwalSewa());
        cv.put("tanggal_akhir_sewa", sewayu.getTanggalAkhirSewa());
        cv.put("lama_sewa", sewayu.getLamaSewa());
        cv.put("ketersediaan_bensin", sewayu.getKetersediaanBensin());
        cv.put("harga_bensin_per_liter", sewayu.getHargaBensinPerLiter());
        cv.put("total_bayar", sewayu.getTotalBayar());

        return db.insert("sewayu", null, cv) > 0;
    }

    public boolean updateSewayu (Sewayu sewayu, Integer id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user_id", sewayu.getUserId());
        cv.put("kategori", sewayu.getKategori());
        cv.put("jenis_kendaraan", sewayu.getJenisKendaraan());
        cv.put("harga_sewa_per_hari", sewayu.getHargaSewaPerHari());
        cv.put("keperluan", sewayu.getKeperluan());
        cv.put("tanggal_awal_sewa", sewayu.getTanggalAwalSewa());
        cv.put("tanggal_akhir_sewa", sewayu.getTanggalAkhirSewa());
        cv.put("lama_sewa", sewayu.getLamaSewa());
        cv.put("ketersediaan_bensin", sewayu.getKetersediaanBensin());
        cv.put("harga_bensin_per_liter", sewayu.getHargaBensinPerLiter());
        cv.put("total_bayar", sewayu.getTotalBayar());

        return db.update("sewayu", cv, "id" + "=" + id,
                null) > 0;
    }

    public void deleteSewayu (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        db.delete("sewayu", "id" + "=" + id, null);
    }

    public Cursor getAllSewayu () {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu", null);
    }

    public Cursor getSewayuById (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu where id = "+id, null);
    }

    public Cursor getAllSewayuByUser (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu where user_id = "+id, null);
    }

    public Cursor getHistory (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu where user_id = "+id+" and tanggal_akhir_sewa < strftime('%d-%M-%Y', date('now','localtime')) order by tanggal_akhir_sewa DESC", null);
    }

    public Cursor getYourBooking (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu where user_id = "+id+" and tanggal_awal_sewa > strftime('%d-%M-%Y', date('now','localtime')) order by tanggal_akhir_sewa DESC", null);
    }

    public Cursor getOnProgress (Integer id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from " + "sewayu where user_id = "+id+" and tanggal_awal_sewa <= strftime('%d-%M-%Y', date('now','localtime')) and tanggal_akhir_sewa >= strftime('%d-%M-%Y', date('now','localtime')) order by tanggal_akhir_sewa DESC", null);
    }
}
