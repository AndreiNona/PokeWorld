package and.andrei.pokeworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView registerNow;
    String email, password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.button_login);
        registerNow = findViewById(R.id.button_register);
        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = loginEmail.getText().toString();
                password = loginPassword.getText().toString();
                if(!(email.isEmpty() || password.isEmpty())){
                    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        //Dealing with login mistakes
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                try {
                                    throw task.getException();
                                }
                                catch(FirebaseAuthInvalidUserException e) {
                                    Toast.makeText(getApplicationContext(), "Invalid email.", Toast.LENGTH_LONG).show();
                                }
                                catch(FirebaseAuthInvalidCredentialsException e) {
                                    Toast.makeText(getApplicationContext(), "Invalid password.", Toast.LENGTH_LONG).show();
                                }
                                catch(Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please fill in all the fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Replace second with register activity
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
    }
