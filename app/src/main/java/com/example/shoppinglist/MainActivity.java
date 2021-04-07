package com.example.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppinglist.Login;
import com.example.shoppinglist.R;

public class MainActivity extends AppCompatActivity {
    public MainActivity(){
        super(R.layout.main_activity);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, Login.newInstance()).commitNow();
        }
    }

    public void appLogin(View view){
        Button login = (Button)findViewById(R.id.login);
        EditText User = (EditText)findViewById(R.id.Username);
        EditText Pass = (EditText)findViewById(R.id.Password);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (User.getText().toString().equals("admin") && Pass.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...", Toast.LENGTH_SHORT).show();
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, MainApp.newInstance()).commitNow();
                } else
                    Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }
        });
    }
}