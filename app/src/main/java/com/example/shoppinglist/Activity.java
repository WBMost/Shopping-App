package com.example.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppinglist.ListOLists.ListDisplay;
import com.example.shoppinglist.ListOLists.ListsFragment;

public class Activity extends AppCompatActivity {
    boolean signIn = false;
    public Activity(){
        super(R.layout.main_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
        }
    }

    public void appLogin(View view){
        EditText User = (EditText)findViewById(R.id.Username);
        EditText Pass = (EditText)findViewById(R.id.Password);
        if (User.getText().toString().equals("admin") && Pass.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Welcome, " + User.getText().toString(), Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
        }
        else
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
    }
    public void offlineAppLogin(View view){
        Toast.makeText(getApplicationContext(), "Accessing app offline", Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
    }

    public void openManageStores(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
    }

    public void openManageLists(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, ListDisplay.newInstance()).commitNow();
    }

    public void openEnterStore(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
    }

    public void returnToMenu(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
    }

    public void openDownloadUpload(View view) {
        if(!signIn)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, Login.newInstance()).commitNow();
    }
}