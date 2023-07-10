package and.andrei.pokeworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    EditText Name, Email, Password, confirmPassword;
    Button registerButton, loginButton;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Data
        Name = findViewById(R.id.name);
        Email = findViewById(R.id.email);

        //Password
        Password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirm_password);

        //Buttons
        registerButton = findViewById(R.id.button_register);
        loginButton = findViewById(R.id.button_login);


        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameText = Name.getText().toString();
                String registerEmailText = Email.getText().toString();
                String registerPasswordText = Password.getText().toString();
                String repeatPasswordText = confirmPassword.getText().toString();

                if (!(fullNameText.isEmpty() || registerEmailText.isEmpty() || registerPasswordText.isEmpty() || repeatPasswordText.isEmpty())) {
                    if (registerPasswordText.equals(repeatPasswordText)) {

                        createAccount(registerEmailText, registerPasswordText, fullNameText);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Please complete all required  fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void createAccount(String email, String password, String fullName) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(fullName)
                            .build();

                    user.updateProfile(profileUpdates)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
                else {
                    try {
                        throw task.getException();
                    }
                    catch(FirebaseAuthWeakPasswordException e) {
                        Toast.makeText(RegisterActivity.this, "Password is to weak", Toast.LENGTH_LONG).show();
                    }
                    catch(FirebaseAuthUserCollisionException e) {
                        Toast.makeText(RegisterActivity.this, "Email already in use", Toast.LENGTH_LONG).show();
                    }
                    catch(FirebaseAuthInvalidCredentialsException e) {
                        Toast.makeText(RegisterActivity.this, "Email is invalid", Toast.LENGTH_SHORT).show();
                    }
                    catch(Exception e) {
                        Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}