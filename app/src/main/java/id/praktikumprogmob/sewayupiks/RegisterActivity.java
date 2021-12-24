package id.praktikumprogmob.sewayupiks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import id.praktikumprogmob.sewayupiks.helper.DBHelper;
import id.praktikumprogmob.sewayupiks.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText name, phone, email, password, confirmPassword, address, age;
    private CheckBox checkBox;
    private TextInputLayout nameField, phoneField, emailField, passwordField, confirmPasswordField, addressField, ageField;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        confirmPassword = findViewById(R.id.et_confirm_pass);
        address = findViewById(R.id.et_address);
        age = findViewById(R.id.et_age);
        checkBox = findViewById(R.id.cb);

        nameField = findViewById(R.id.name_field);
        phoneField = findViewById(R.id.phone_field);
        emailField = findViewById(R.id.email_field);
        passwordField = findViewById(R.id.pass_field);
        confirmPasswordField = findViewById(R.id.confirm_pass_field);
        addressField = findViewById(R.id.address_field);
        ageField = findViewById(R.id.age_field);

        dbHelper = new DBHelper(getApplicationContext());
    }

    public void registerButton(View view) {
        if (validate()) {
            if (!dbHelper.isEmailExists(email.getText().toString())) {
                User user = new User(
                        name.getText().toString(),
                        phone.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        address.getText().toString(),
                        Integer.valueOf(age.getText().toString())
                        );
                dbHelper.addUser(user);
                Toast.makeText(getApplicationContext(), "Register success. Please login", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "User already exists with same email", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        if (name.getText().toString().isEmpty()) {
            valid = false;
            nameField.setError("Please enter valid name!");
        }
        if (phone.getText().toString().isEmpty()) {
            valid = false;
            phoneField.setError("Please enter valid phone number!");
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            valid = false;
            emailField.setError("Please enter valid email!");
        }
        if (password.getText().toString().isEmpty()) {
            valid = false;
            passwordField.setError("Please enter valid password!");
        }
        if (password.getText().toString().length() < 6) {
            valid = false;
            passwordField.setError("Password is too short!");
        }
        if (confirmPassword.getText().toString().isEmpty()) {
            valid = false;
            passwordField.setError("Please re-enter password!");
        }
        if (!password.getText().toString().equals(confirmPassword.getText().toString())){
            valid = false;
            passwordField.setError("Password didn't match!");
            confirmPasswordField.setError("Password didn't match!");
        }
        if (address.getText().toString().isEmpty()) {
            valid = false;
            addressField.setError("Please enter valid address!");
        }
        if (age.getText().toString().isEmpty()) {
            valid = false;
            ageField.setError("Please enter valid age!");
        }
        if(!checkBox.isChecked()){
            valid = false;
            Toast.makeText(getApplicationContext(), "Please accept our Privacy Policy and Term of Use", Toast.LENGTH_SHORT).show();
        }

        return valid;
    }

    public void loginButton(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}