package id.praktikumprogmob.sewayupiks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addNewOrder(View view) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivity(intent);
    }

    public void orderList(View view) {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
    }
}