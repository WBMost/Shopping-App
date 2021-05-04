package com.example.shoppinglist;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppinglist.ListOLists.NewItem;
import com.example.shoppinglist.ListOLists.RecyclerDisplay;
import com.example.shoppinglist.ListOLists.gItem;
import com.example.shoppinglist.ListOLists.gList;
import com.example.shoppinglist.ListOLists.groLists;
import com.example.shoppinglist.ListOLists.NewList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

public class Activity extends AppCompatActivity {
    boolean signIn = false;


    //region variables for lists
    public groLists listOlists;
    public gList gListSelected;
    public static int tempPosition=-1;
    //endregion



    public Activity(){
        super(R.layout.main_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOlists = new groLists();

        /*listOlists.addItem(new gList("Grocery","Weekly stuff",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(0).addGItem(new gItem("Fruit","Banana",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(0).addGItem(new gItem("Fruit","Strawberry",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(0).addGItem(new gItem("Frozen","Supreme Pizza",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(0).addGItem(new gItem("Dairy","Gallon of Milk",Calendar.getInstance().getTime().toString()));
        listOlists.addItem(new gList("Grocery","Party",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(1).addGItem(new gItem("Fruit","Apples",Calendar.getInstance().getTime().toString()));
        listOlists.addItem(new gList("Grocery","Birthday",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(2).addGItem(new gItem("Bakery","Cake",""));
        listOlists.G_LISTS.get(2).addGItem(new gItem("Bakery","Candles",""));
        listOlists.G_LISTS.get(2).addGItem(new gItem("Drink","Soda",""));
        listOlists.addItem(new gList("Furniture","Stuff for apartment",Calendar.getInstance().getTime().toString()));
        listOlists.G_LISTS.get(3).addGItem(new gItem("Furniture","Couch",""));
        listOlists.G_LISTS.get(3).addGItem(new gItem("Electronic","Laptop",""));*/
        try {
            readListsFile();
        }catch (Exception e) {};
        tempPosition=-1;

        setContentView(R.layout.main_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
        }
    }

    public void writeListsFile()
    {
        /*File path = getApplicationContext().getFilesDir();
        File file = new File(path,"listOlists.txt");
        FileOutputStream stream = null;
        try { stream = new FileOutputStream(file); } catch (FileNotFoundException e) { e.printStackTrace(); }*/
        try {
            FileWriter out = new FileWriter(new File(getApplicationContext().getFilesDir(),"listOlists.txt"));
            for(int x = 0; x < listOlists.G_LISTS.size(); x++)
            {
                out.write("L" + listOlists.G_LISTS.get(x).toString() + "\n");

                for(int i = 0; i < listOlists.G_LISTS.get(x).ITEMS.size(); i++)
                {
                    out.write("I" + listOlists.G_LISTS.get(x).ITEMS.get(i).toString() + "\n");
                }
            }
            out.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void readListsFile()
    {
        Context context = getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), "listOlists.txt")));

            while ((line = in.readLine()) != null)
            {
                if(line.charAt(0) == 'L') {
                    listOlists.addItem(new gList(line.substring(1,line.indexOf("\\1")), line.substring(line.indexOf("\\1")+2, line.indexOf("\\2")),line.substring(line.indexOf("\\2")+2)));
                }
                if(line.charAt(0) == 'I')
                {
                    listOlists.G_LISTS.get(listOlists.G_LISTS.size()-1).addGItem(new gItem(line.substring(1,line.indexOf("\\1")), line.substring(line.indexOf("\\1")+2, line.indexOf("\\2")),line.substring(line.indexOf("\\2")+2)));
                }
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Failed to locate readable file.", Toast.LENGTH_SHORT).show();;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void setTempPostion(int num){
        tempPosition=num;
    }

    //region Login functions
    public void appLogin(View view) {
        EditText User = findViewById(R.id.Username);
        EditText Pass = findViewById(R.id.Password);
        if (User.getText().toString().equals("admin") && Pass.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Welcome, " + User.getText().toString(), Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
        }
        else
            Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
    }
    //endregion

    //region Store bits
    public void openManageStores(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
    }
    //endregion

    //region Lists and all their bits
    public void openManageLists(View view) {
        tempPosition=-1;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, RecyclerDisplay.newInstance(listOlists)).commitNow();
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

    public void addList(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, NewList.newInstance()).commitNow();
    }

    public void createList(View view) {
        EditText Type = findViewById(R.id.newListType);
        EditText Name = findViewById(R.id.newListName);
        gList li;
        li = new gList(Type.getText().toString(),Name.getText().toString(), Calendar.getInstance().getTime().toString());
        listOlists.addItem(li);
        writeListsFile();
        openManageLists(view);
    }



    public void deleteList(View view) {
        if(tempPosition != -1) {
            listOlists.G_LISTS.remove(tempPosition);
            tempPosition=-1;
            writeListsFile();
            openManageLists(view);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No list selected to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void editList(View view) {
        if(tempPosition != -1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, RecyclerDisplay.newInstance(listOlists.G_LISTS.get(tempPosition))).commitNow();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No list selected to edit", Toast.LENGTH_SHORT).show();
        }
    }

    public void editedList(View view) {
        EditText Type = findViewById(R.id.newItemType);
        EditText Name = findViewById(R.id.newItemName);
        gList li;
        li = new gList(Type.getText().toString(),Name.getText().toString(),Name.getText().toString());
        listOlists.G_LISTS.set(tempPosition, li);
        openManageLists(view);
    }

    public void selectList(View view) {
        if(tempPosition != -1) {
            gListSelected = listOlists.G_LISTS.get(tempPosition);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
            Toast.makeText(getApplicationContext(), "List " + gListSelected.name+ " selected", Toast.LENGTH_SHORT).show();
            //selectedList.setText(gListSelected.name);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No list selected", Toast.LENGTH_SHORT).show();
        }
    }
    //endregion

    //region Items
    public void addItem(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, NewItem.newInstance()).commitNow();
    }
    public void createItem(View view){
        EditText Type = findViewById(R.id.newItemType);
        EditText Name = findViewById(R.id.newItemName);
        gItem li;
        li = new gItem(Type.getText().toString(),Name.getText().toString(),Calendar.getInstance().getTime().toString());
        listOlists.G_LISTS.get(tempPosition).ITEMS.add(li);
        writeListsFile();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, RecyclerDisplay.newInstance(listOlists.G_LISTS.get(tempPosition))).commitNow();
    }
    public void deleteItem(View view) {
        writeListsFile();
    }
    public void editItem(View view) {
        writeListsFile();
    }
    //endregion

}