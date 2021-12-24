package id.praktikumprogmob.sewayupiks.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import id.praktikumprogmob.sewayupiks.EditActivity;
import id.praktikumprogmob.sewayupiks.ListActivity;
import id.praktikumprogmob.sewayupiks.MainActivity;
import id.praktikumprogmob.sewayupiks.R;
import id.praktikumprogmob.sewayupiks.YourBookingFragment;
import id.praktikumprogmob.sewayupiks.helper.DBHelper;

public class YourBookingAdapter extends RecyclerView.Adapter<YourBookingAdapter.ViewHolder>{
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
    private Context context;
    private View view;

    public YourBookingAdapter(ArrayList id, ArrayList userId, ArrayList kategori, ArrayList jenisKendaraan, ArrayList hargaSewaPerHari, ArrayList keperluan, ArrayList tanggalAwalSewa, ArrayList tanggalAkhirSewa, ArrayList lamaSewa, ArrayList ketersediaanBensin, ArrayList hargaBensinPerLiter, ArrayList totalBayar) {
        this.id = id;
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

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView historyKategori, typeKendaraan, keperluan, tanggalSewa, bensin;
        private ImageButton edit, delete, detail;
        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            historyKategori = itemView.findViewById(R.id.HistoryKategori);
            typeKendaraan = itemView.findViewById(R.id.HistoryTypeKendaraan);
            keperluan = itemView.findViewById(R.id.HistoryKeperluan);
            tanggalSewa = itemView.findViewById(R.id.HistoryTanggalSewa);
            bensin = itemView.findViewById(R.id.HistoryBensin);
            edit = itemView.findViewById(R.id.btnupdate);
            delete = itemView.findViewById(R.id.btndelete);
            detail = itemView.findViewById(R.id.btndetail);
        }
    }

    @Override
    public YourBookingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_edit_delete, parent, false);
        return new YourBookingAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(YourBookingAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Integer idView = (Integer) id.get(position);
        Integer userIdView = (Integer) userId.get(position);
        String kategoriView = (String) kategori.get(position);
        String kendaraanView = (String) jenisKendaraan.get(position);
        Integer hargaSewaView = (Integer) hargaSewaPerHari.get(position);
        String keperluanView = (String) keperluan.get(position);
        String tanggalSewaView = (String) tanggalAwalSewa.get(position) + " - " + tanggalAkhirSewa.get(position);
        Integer lamaSewaView = (Integer) lamaSewa.get(position);
        Integer bensinView = (Integer) ketersediaanBensin.get(position);
        Integer hargaBensinView = (Integer) hargaBensinPerLiter.get(position);
        Integer totalBayarView = (Integer) totalBayar.get(position);

        holder.historyKategori.setText(kategoriView);
        holder.typeKendaraan.setText(kendaraanView);
        holder.keperluan.setText(Html.fromHtml(keperluanView));
        holder.tanggalSewa.setText(tanggalSewaView);
        holder.bensin.setText(String.valueOf(bensinView)+ " Liter");

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                DBHelper dbHelper = new DBHelper(context);
                Cursor cursor = dbHelper.getUserById(userIdView);
                cursor.moveToFirst();
                String nama = cursor.getString(1);
                String noHP = cursor.getString(2);
                dbHelper.close();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Data Penyewa");
                builder.setMessage(
                        Html.fromHtml(
                                "Nama : <br>" + nama + "<br><br>" +
                                        "No. HP : <br>" + noHP + "<br><br>" +
                                        "Kategori : <br>" + kategoriView + "<br><br>" +
                                        "Jenis Kendaraan : <br>" + kendaraanView + "<br><br>" +
                                        "Harga Sewa per Hari : <br> Rp." + hargaSewaView + "<br><br>" +
                                        "Keperluan : <br>" + keperluanView + "<br><br>" +
                                        "Lama Sewa : <br>" + tanggalSewaView + "<br>" +
                                        "( " + lamaSewaView + " hari )<br><br>" +
                                        "Ketersediaan Bensin : <br>" + bensinView + " Liter<br><br>" +
                                        "Harga Bensin per Liter : <br> Rp." + hargaBensinView + "<br><br><br>" +
                                        "<h5>Total Bayar = Rp. " + totalBayarView + "</h5>"
                        )
                );
                builder.setPositiveButton("OKE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", idView);
                context.startActivity(intent);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder dialog;
                LayoutInflater inflater;
                View dialogView;
                dialog = new AlertDialog.Builder(context);
                inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
                dialogView = inflater.inflate(R.layout.modal_delete, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);
                dialog.setIcon(R.mipmap.ic_launcher);

                dialog.setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        DBHelper dbHelper = new DBHelper(context.getApplicationContext());
                        dbHelper.deleteSewayu(idView);
                        Toast.makeText(context.getApplicationContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                        dbHelper.close();
                        Intent intent = new Intent(view.getContext(), ListActivity.class);
                        context.startActivity(intent);
                    }
                });
                dialog.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return id.size();
    }
}

