package com.example.hostelhomes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Adminhomeactivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    LinearLayout addUser;
    private static final String PREFS_NAME = "LoginPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminhomeactivity);

        //Get id from intent
        Intent intent = getIntent();
        String ID = intent.getStringExtra("ID");
        addUser = (LinearLayout) findViewById(R.id.AddUser);

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Adminhomeactivity.this, AddUserActivity.class);
                startActivity(i1);
            }
        });

        // Set up logout button
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Button logoutbtn = findViewById(R.id.logoutbtn);
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Adminhomeactivity.this, "You are logged out", Toast.LENGTH_SHORT).show();

                // Clear login status in SharedPreferences

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("loggedIn", false);
                editor.apply();

                // Redirect to MainActivity
                Intent hometomain = new Intent(Adminhomeactivity.this, MainActivity.class);
                hometomain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(hometomain);
                finish();
            }
        });
    }
}