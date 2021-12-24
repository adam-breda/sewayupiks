package id.praktikumprogmob.sewayupiks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ListActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        navigationView = findViewById(R.id.navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body, new OnProgressFragment()).commit();
        navigationView.setSelectedItemId(R.id.on_progress);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.on_progress:
                        fragment = new OnProgressFragment();
                        break;

                    case R.id.your_booking:
                        fragment = new YourBookingFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.body, fragment).commit();
                return true;
            }
        });

    }

    public void backButton(View view) {
        onBackPressed();
    }
}