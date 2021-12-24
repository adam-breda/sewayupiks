package id.praktikumprogmob.sewayupiks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import id.praktikumprogmob.sewayupiks.helper.DBHelper;
import id.praktikumprogmob.sewayupiks.model.User;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private TextInputLayout emailField, passwordField;
    private DBHelper dbHelper;

    public static Integer userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        emailField = findViewById(R.id.email);
        passwordField = findViewById(R.id.pass_field);
        dbHelper = new DBHelper(getApplicationContext());
    }

    public void registerButton(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void loginButton(View view) {
        if (validate()) {
            User currentUser = dbHelper.Authenticate(new User(null, null, null, email.getText().toString(), password.getText().toString(), null, null));
            if (currentUser != null) {
                DBHelper dh = new DBHelper(getApplicationContext());
                Cursor cursor = dh.getUserIdByEmail(email.getText().toString());
                cursor.moveToFirst();
                userId = cursor.getInt(0);
                dh.close();
                Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to log in, please try again", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean validate() {
        boolean valid = true;
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            valid = false;
            emailField.setError("Please enter valid email!");
        }
        if (password.getText().toString().isEmpty()) {
            valid = false;
            passwordField.setError("Please enter valid password!");
        }
        return valid;
    }
}