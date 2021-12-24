package id.praktikumprogmob.sewayupiks;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.praktikumprogmob.sewayupiks.adapter.HistoryAdapter;
import id.praktikumprogmob.sewayupiks.helper.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnProgressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnProgressFragment extends Fragment {

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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnProgressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnProgressFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnProgressFragment newInstance(String param1, String param2) {
        OnProgressFragment fragment = new OnProgressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_on_progress, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
        recyclerView = view.findViewById(R.id.recyclercview);
        noData = view.findViewById(R.id.textData);
        getData();
        layoutManager = new LinearLayoutManager(this.getContext());
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
        final DBHelper dh = new DBHelper(this.getContext());
        Cursor cursor = dh.getOnProgress(LoginActivity.userId);
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
}