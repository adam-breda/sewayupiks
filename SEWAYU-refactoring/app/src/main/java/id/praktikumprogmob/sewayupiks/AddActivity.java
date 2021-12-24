package id.praktikumprogmob.sewayupiks;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.util.Arrays;
import java.util.Calendar;

import id.praktikumprogmob.sewayupiks.helper.DBHelper;
import id.praktikumprogmob.sewayupiks.model.Sewayu;
import id.praktikumprogmob.sewayupiks.service.DatePickerFragment;

public class AddActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton motor, mobil;
    AutoCompleteTextView pilihkendaraan;
    CheckBox event, jalan, ngedate, keluarga;
    EditText daritanggal, hinggatanggal;
    SeekBar seekBar;
    Calendar tglDari, tglSampai;
    TextView ketersediaan;
    CheckBox makingSure;

    Integer hargaSewa = 50000, hargaBensin = 12000, ketersediaanBensin = 0;
    String kategoriTerpilih = "Motor", kendaraanTerpilih = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        radioGroup = findViewById(R.id.RadioGroup);
        motor = findViewById(R.id.betina);
        mobil = findViewById(R.id.lakik);
        pilihkendaraan = findViewById(R.id.PilihKendaraan);
        event = findViewById(R.id.checkbox_event);
        jalan = findViewById(R.id.checkbox_jalan);
        ngedate = findViewById(R.id.checkbox_ngedate);
        keluarga = findViewById(R.id.checkbox_keluarga);
        daritanggal = findViewById(R.id.DariTanggal);
        hinggatanggal = findViewById(R.id.HinggaTanggal);
        seekBar = findViewById(R.id.seekbar);
        ketersediaan = findViewById(R.id.ketersediaan);
        makingSure = findViewById(R.id.makingsure);
        tglDari = Calendar.getInstance();
        tglSampai = Calendar.getInstance();
        motor.setChecked(true);

        String[] items = {"Honda Beat DK 1723 MS (65k)", "Honda Vario DK 1804 UR (70k)", "Nmax DK 7658 JJ (120k)", "PCX DK 5145 FCF (130k)"};
        ArrayAdapter<String> adapterItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, items);
        pilihkendaraan.setAdapter(adapterItems);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.betina:
                        kategoriTerpilih = "Motor";
                        items[0] = "Honda Beat DK 1723 MS (65k)";
                        items[1] = "Honda Vario DK 1804 UR (70k)";
                        items[2] = "Nmax DK 7658 JJ (120k)";
                        items[3] = "PCX DK 5145 FCF (130k)";
                        pilihkendaraan.setText(items[0], false);
                        seekBar.setMax(3);
                        break;
                    case R.id.lakik:
                        kategoriTerpilih = "Mobil";
                        items[0] = "Toyota Innova DK 8937 ES (450k)";
                        items[1] = "Honda Jazz DK 1518 RD (300k)";
                        items[2] = "Suzuki Ertiga DK 1004 OK (250k)";
                        items[3] = "Mitsubishi Pajero Sport DK 9952 KL (700k)";
                        pilihkendaraan.setText(items[0], false);
                        seekBar.setMax(45);
                        break;
                }
            }
        });

        pilihkendaraan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                kendaraanTerpilih = item;
                switch (kendaraanTerpilih){
                    case "Honda Beat DK 1723 MS (65k)":
                        hargaSewa = 65000;
                        break;
                    case "Honda Vario DK 1804 UR (70k)":
                        hargaSewa = 70000;
                        break;
                    case "Nmax DK 7658 JJ (120k)":
                        hargaSewa = 120000;
                        break;
                    case "PCX DK 5145 FCF (130k)":
                        hargaSewa = 130000;
                        break;
                    case "Toyota Innova DK 8937 ES (450k)":
                        hargaSewa = 450000;
                        break;
                    case "Honda Jazz DK 1518 RD (300k)":
                        hargaSewa = 300000;
                        break;
                    case "Suzuki Ertiga DK 1004 OK (250k)":
                        hargaSewa = 250000;
                        break;
                    case "Mitsubishi Pajero Sport DK 9952 KL (700k)":
                        hargaSewa = 700000;
                        break;
                }
                Toast.makeText(getApplicationContext(), "Kendaraan: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ketersediaanBensin = i;
                ketersediaan.setText(ketersediaanBensin + " Liter");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        daritanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilKalender(daritanggal, tglDari);
            }
        });

        hinggatanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilKalender(hinggatanggal, tglSampai);
            }
        });
    }

    public void tampilKalender(EditText editText, Calendar calendar) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.show(getSupportFragmentManager(), "data");
        datePickerFragment.setOnDateClickListener(new DatePickerFragment.onDateClickListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tahun = "" + datePicker.getYear();
                String bulan = "" + ( datePicker.getMonth() + 1 );
                String hari  = "" + datePicker.getDayOfMonth();
                String format = hari + "-" + bulan + "-" + tahun;

                calendar.set(datePicker.getYear(), (datePicker.getMonth() + 1), datePicker.getDayOfMonth());
                editText.setText(format);
                editText.setError(null);
            }
        });
    }

    public void addData(View view) {
        if(validasiInput()) {
            Integer userId = LoginActivity.userId;
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            Cursor cursor = dbHelper.getUserById(userId);
            cursor.moveToFirst();
            String nama = cursor.getString(1);
            String noHP = cursor.getString(2);
            dbHelper.close();

            String kategori = kategoriTerpilih;
            String jenisKendaraan = kendaraanTerpilih;
            Integer hargaSewaPerHari = hargaSewa;
            String keperluan = getKeperluan();
            String tanggalAwalSewa = daritanggal.getText().toString();
            String tanggalAkhirSewa = hinggatanggal.getText().toString();
            Integer lamaSewa = getSelisihHari();
            Integer ketersediaanBensin = this.ketersediaanBensin;
            Integer hargaBensinPerLiter = hargaBensin;
            Integer totalBayar = getTotalBayar();

            AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
            builder.setTitle("Data Penyewa");
            builder.setMessage(
                    Html.fromHtml(
                            "Nama : <br>" + nama + "<br><br>" +
                                    "No. HP : <br>" + noHP + "<br><br>" +
                                    "Kategori : <br>" + kategori + "<br><br>" +
                                    "Jenis Kendaraan : <br>" + jenisKendaraan + "<br><br>" +
                                    "Harga Sewa per Hari : <br> Rp." + hargaSewaPerHari + "<br><br>" +
                                    "Keperluan : <br>" + keperluan + "<br><br>" +
                                    "Lama Sewa : <br>" + tanggalAwalSewa + " - " + tanggalAkhirSewa + "<br>" +
                                    "( " + lamaSewa + " hari )<br><br>" +
                                    "Ketersediaan Bensin : <br>" + ketersediaanBensin + " Liter<br><br>" +
                                    "Harga Bensin per Liter : <br> Rp." + hargaBensinPerLiter + "<br><br><br>" +
                                    "<h5>Total Bayar = Rp. " + totalBayar + "</h5>"
                    )
            );
            builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    DBHelper dh = new DBHelper(getApplicationContext());
                    Sewayu sewayu = new Sewayu(
                            userId,
                            kategori,
                            jenisKendaraan,
                            hargaSewaPerHari,
                            keperluan,
                            tanggalAwalSewa,
                            tanggalAkhirSewa,
                            lamaSewa,
                            ketersediaanBensin,
                            hargaBensinPerLiter,
                            totalBayar
                            );
                    boolean input;
                    input = dh.addSewayu(sewayu);
                    if (input) {
                        Toast.makeText(getApplicationContext(), "Berhasil melakukan sewa", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Kesalahan terjadi!", Toast.LENGTH_SHORT).show();
                    }
                    dh.close();
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }


    private String getKeperluan() {
        String keperluan = "";

        if (event.isChecked()) {
            keperluan += ( "- " + event.getText().toString() + "<br>");
        }

        if (jalan.isChecked()) {
            keperluan += ( "- " + jalan.getText().toString() + "<br>");
        }

        if (ngedate.isChecked()) {
            keperluan += ( "- " + ngedate.getText().toString() + "<br>");
        }

        if (keluarga.isChecked()) {
            keperluan += ( "- " + keluarga.getText().toString() + "<br>");
        }

        if (!keperluan.isEmpty()) {
            keperluan = keperluan.substring(0, keperluan.length()-4);
        }

        return keperluan;
    }

    public Integer getSelisihHari() {
        long selisih;

        long diff = tglSampai.getTimeInMillis() - tglDari.getTimeInMillis();
        selisih = diff / (24 * 60 * 60 * 1000);

        return (int) selisih;
    }

    public Integer getTotalBayar() {
        int totalHarga;

        long total = ((long) getSelisihHari() * hargaSewa) + ((long) ketersediaanBensin * hargaBensin);
        totalHarga = (int) total;

        return totalHarga;
    }

    public boolean validasiInput() {
        boolean aman = true;
        if(kendaraanTerpilih.equals("")){
            pilihkendaraan.setError("Mohon pilih jenis kendaraan");
            aman = false;
        }

        if(ketersediaanBensin == 0){
            ketersediaan.setError("Mohon pilih minimal 1 liter");
            aman = false;
        }

        if (getKeperluan().isEmpty()) {
            event.setError("Minimal pilih 1 Keperluan !");
            event.setFocusable(true);
            event.setFocusableInTouchMode(true);
            event.requestFocus();
            aman = false;
        } else {
            event.setError(null);
            event.setFocusable(false);
            event.setFocusableInTouchMode(false);
        }

        if (daritanggal.getText().toString().trim().isEmpty()) {
            daritanggal.setError("Mohon Masukan Tanggal Awal Anda Menyewa !");
            aman = false;
        }

        if (hinggatanggal.getText().toString().trim().isEmpty()) {
            hinggatanggal.setError("Mohon Masukan Tanggal Akhir Anda Menyewa !");
            aman = false;
        }

        if(!makingSure.isChecked()){
            makingSure.setError("Konfirmasi data sudah benar");
            aman = false;
        }

        if(getSelisihHari() <= 0){
            daritanggal.setError("Mohon Masukan minimal 1 hari sewa !");
            hinggatanggal.setError("Mohon Masukan minimal 1 hari sewa !");
            aman = false;
        }

        return aman;
    }

}