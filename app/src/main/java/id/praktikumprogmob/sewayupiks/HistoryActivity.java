package id.praktikumprogmob.sewayupiks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import id.praktikumprogmob.sewayupiks.adapter.HistoryAdapter;
import id.praktikumprogmob.sewayupiks.helper.DBHelper;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList id;
    private ArrayList userId;
    private ArrayList kategori;
    private ArrayList jenisKendaraan;
    private ArrayList hargaSewaPerHari;
    private ArrayList keperluan;
    private ArrayList tanggalAwalSewa;
    private ArrayList tanggalAkhirSewa;
    private ArrayList lamaSewa;
    private ArrayList ketersediaanBensin;
    private ArrayList hargaBensinPerLiter;
    private ArrayList totalBayar;
    private TextView noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        id = new ArrayList<>();
        userId = new ArrayList<>();
        kategori = new ArrayList<>();
        jenisKendaraan = new ArrayList<>();
        hargaBensinPerLiter = new ArrayList<>();
        hargaSewaPerHari = new ArrayList<>();
        keperluan = new ArrayList<>();
        tanggalAkhirSewa = new ArrayList<>();
        tanggalAwalSewa = new ArrayList<>();
        lamaSewa = new ArrayList<>();
        ketersediaanBensin = new ArrayList<>();
        totalBayar = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclercview);
        noData = findViewById(R.id.textData);
        getData();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new HistoryAdapter(id, userId, kategori, jenisKendaraan, hargaSewaPerHari, keperluan, tanggalAwalSewa, tanggalAkhirSewa, lamaSewa, ketersediaanBensin, hargaBensinPerLiter, totalBayar);
        recyclerView.setAdapter(adapter);

        if(adapter.getItemCount() != 0){
            noData.setVisibility(View.GONE);
        }
    }


    @SuppressLint("Recycle")
    protected void getData() {
        final DBHelper dh = new DBHelper(getApplicationContext());
        Cursor cursor = dh.getHistory(LoginActivity.userId);
        cursor.moveToFirst();

        for (int count = 0; count < cursor.getCount(); count++) {

            cursor.moveToPosition(count);
            id.add(cursor.getInt(0));
            userId.add(cursor.getInt(1));
            kategori.add(cursor.getString(2));
            jenisKendaraan.add(cursor.getString(3));
            hargaSewaPerHari.add(cursor.getInt(4));
            keperluan.add(cursor.getString(5));
            tanggalAwalSewa.add(cursor.getString(6));
            tanggalAkhirSewa.add(cursor.getString(7));
            lamaSewa.add(cursor.getInt(8));
            ketersediaanBensin.add(cursor.getInt(9));
            hargaBensinPerLiter.add(cursor.getInt(10));
            totalBayar.add(cursor.getInt(11));
        }
    }

    public void backButton(View view) {
        onBackPressed();
    }
}