package com.example.shoppinglist;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.shoppinglist.ListOLists.NewItem;
import com.example.shoppinglist.ListOLists.gItem;
import com.example.shoppinglist.ListOLists.gItemEdit;
import com.example.shoppinglist.ListOLists.gList;
import com.example.shoppinglist.ListOLists.groLists;
import com.example.shoppinglist.ListOLists.NewList;
import com.example.shoppinglist.ShopList.ItemEdit;
import com.example.shoppinglist.ShopList.ShoppingRecyclerDisplay;
import com.example.shoppinglist.Store.CreateStore;
import com.example.shoppinglist.Store.Store;
import com.example.shoppinglist.Store.StoreCanvas;
import com.example.shoppinglist.Store.StoreInterior;
import com.example.shoppinglist.Store.StoreRecyclerDisplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class Activity extends AppCompatActivity{
    boolean signIn = false;


    //region variables

    //overarching lists
    public static groLists listOlists;
    public static ArrayList<Store> listOstores;

    //selection vars
    public static int tempPosition=-1;
    public static int listTempPosition = -1;
    public static int storeTempPosition = -1;
    public static gList gListSelected;
    public static gList tempgListSelected;//second gList for temp edits to shopping list
    public static Store gStoreSelected;

    //xml shit
    public EditText changeListName;
    public EditText changeListType;
    //endregion

    public Activity(){
        super(R.layout.main_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tempPosition=-1;
        listOstores = new ArrayList<>();

        try {
            readListsFile();
            readStoresFile();
        }catch (Exception e) {e.printStackTrace();}

        setContentView(R.layout.main_container);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
            TextView selectedList = findViewById(R.id.selectedListText);
            TextView selectedStore = findViewById(R.id.selectedStore);
            if(selectedList!=null && gListSelected!=null)
            {
                selectedList.setText(gListSelected.name);
            }
            if(selectedStore!=null && gStoreSelected!=null)
            {
                selectedStore.setText(gStoreSelected.name);
            }
        }
    }

    //region overarching app stuff

    //region File read and write
    public void writeListsFile()
    {
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
        } catch (IOException e) { Toast.makeText(getApplicationContext(), "Failed to write file.", Toast.LENGTH_SHORT).show(); e.printStackTrace(); }
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

    public void writeStoresFile()
    {
        try {
            FileWriter out = new FileWriter(new File(getApplicationContext().getFilesDir(),"listOStores.txt"));
            for(int x = 0; x < listOstores.size(); x++)
            {
                out.write("S" + listOstores.get(x).toString() + "\n");
                for(int i = 0; i < listOstores.get(x).Layout.size(); i++)
                {
                    out.write("A" + listOstores.get(x).Layout.get(i).toString() + "\n");
                }
            }
            out.close();
        } catch (IOException e) { Toast.makeText(getApplicationContext(), "Failed to write file.", Toast.LENGTH_SHORT).show(); e.printStackTrace(); }
    }
    public void readStoresFile()
    {
        Context context = getApplicationContext();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(new File(context.getFilesDir(), "listOStores.txt")));

            while ((line = in.readLine()) != null)
            {
                if(line.charAt(0) == 'S') {
                    listOstores.add(new Store(line.substring(1,line.indexOf("\\1")), line.substring(line.indexOf("\\1")+2, line.indexOf("\\2")),line.substring(line.indexOf("\\2")+2)));
                }
                if(line.charAt(0) == 'A')
                {
                    listOstores.get(listOstores.size()-1).Layout.add(new Store.Aisle(line.substring(1,line.indexOf("\\1")), line.substring(line.indexOf("\\1")+2, line.indexOf("\\2")),line.substring(line.indexOf("\\2")+2, line.indexOf("\\3")),line.substring(line.indexOf("\\3")+2, line.indexOf("\\4")),line.substring(line.indexOf("\\4")+2, line.indexOf("\\5")),line.substring(line.indexOf("\\5")+2)));
                }
                stringBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Failed to locate readable file.", Toast.LENGTH_SHORT).show();;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //endregion

    public static void setTempPosition(int num){
        tempPosition = num;
    }

    public void returnToMenu(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, MainMenu.newInstance()).commitNow();
        TextView selectedList = findViewById(R.id.selectedListText);
        TextView selectedStore = findViewById(R.id.selectedStore);
        if(selectedList!=null && gListSelected!=null)
        {
            selectedList.setText(gListSelected.name);
        }
        if(selectedStore!=null && gStoreSelected!=null)
        {
            selectedStore.setText(gStoreSelected.name);
        }
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    //endregion


    //region Shopping
    public void openEnterStore(View view) {
        tempPosition = -1;
        if(tempgListSelected!=null)
        {
            if(gStoreSelected==null)
            {
                Toast.makeText(getApplicationContext(), "No store selected. Sorting by Type.", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(tempgListSelected)).commitNow();
            }
            else
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(tempgListSelected)).commitNow();
        }
        else {
            if (gListSelected == null) {
                Toast.makeText(getApplicationContext(), "No list selected", Toast.LENGTH_SHORT).show();
                return;
            }
            if (gStoreSelected == null) {
                Toast.makeText(getApplicationContext(), "No store selected. Sorting by Type.", Toast.LENGTH_SHORT).show();
                gListSelected.ITEMS = gListSelected.alphaSortName();
                gListSelected.ITEMS = gListSelected.alphaSortType();
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(gListSelected)).commitNow();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, ShoppingRecyclerDisplay.newInstance(gListSelected)).commitNow();
            }
        }
        TextView selectedList = findViewById(R.id.shopping_ListSelect);
        TextView selectedStore = findViewById(R.id.shopping_StoreSelect);
        if(selectedList!=null && gListSelected!=null)
        {
            selectedList.setText(gListSelected.name);
        }
        if(selectedStore!=null && gStoreSelected!=null)
        {
            selectedStore.setText(gStoreSelected.name);
        }
    }

    public void shoppingEditItem(View view) {
        if(tempPosition!=-1)
            new ItemEdit(gListSelected.ITEMS.get(tempPosition)).show(getSupportFragmentManager(),"ItemEditDia");
        else
            Toast.makeText(getApplicationContext(), "No item selected to edit", Toast.LENGTH_SHORT).show();
    }

    //endregion

    //region Store bits
    public void openManageStores(View view) {
        tempPosition=-1;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, StoreRecyclerDisplay.newInstance(listOstores)).commitNow();
    }

    public void addStore(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, CreateStore.newInstance()).commitNow();
    }
    public void createStore(View view) {
        EditText Type = findViewById(R.id.newStoreType);
        EditText Name = findViewById(R.id.newStoreName);
        if(TextUtils.isEmpty(Type.getText()))
        {
            Type.setText("Grocery");
        }
        if(TextUtils.isEmpty(Name.getText()))
        {
            Name.setText("Store " + (listOstores.size()+1));
        }
        Store store;
        store = new Store(Name.getText().toString(),Type.getText().toString());
        listOstores.add(store);
        tempPosition=-1;
        writeStoresFile();
        openManageStores(view);
    }

    public void deleteStore(View view) {
        if(tempPosition != -1) {
            listOstores.remove(tempPosition);
            tempPosition=-1;
            writeStoresFile();
            openManageStores(view);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No store selected to delete", Toast.LENGTH_SHORT).show();
        }
    }

    public void editStore(View view) {
        if(tempPosition!=-1) {
            storeTempPosition = tempPosition;
            getSupportFragmentManager().beginTransaction().replace(R.id.container, StoreInterior.newInstance(listOstores.get(storeTempPosition))).commitNow();
            ConstraintLayout canvas = findViewById(R.id.store_StoreCanvas);
            canvas.addView(new StoreCanvas(getApplicationContext()));
            tempPosition = -1;
        }
        else
            Toast.makeText(getApplicationContext(), "No Store selected", Toast.LENGTH_SHORT).show();
    }

    public void selectStore(View view) {
        if(tempPosition != -1) {
            gStoreSelected = listOstores.get(tempPosition);
            returnToMenu(view);
            Toast.makeText(getApplicationContext(), "Store " + gStoreSelected.name+ " selected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Store selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void storeAddAisle(View view) {
        EditText posX = findViewById(R.id.aisle_PosX);
        EditText posY = findViewById(R.id.aisle_PosY);
        EditText width = findViewById(R.id.aisle_Width);
        EditText length = findViewById(R.id.aisle_Length);
        if(TextUtils.isEmpty(posX.getText()))
        {
            posX.setText("0");
        }
        if(TextUtils.isEmpty(posY.getText()))
        {
            posY.setText("0");
        }
        if(TextUtils.isEmpty(width.getText()))
        {
            width.setText("50");
        }
        if(TextUtils.isEmpty(length.getText()))
        {
            length.setText("150");
        }
        listOstores.get(storeTempPosition).Layout.add(new Store.Aisle("fruit","name", posX.getText().toString(), posY.getText().toString(), length.getText().toString(), width.getText().toString()));
        tempPosition=storeTempPosition;
        editStore(view);
    }

    //gets rectangle touch to highlight and then destroy drawn rectangle
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(findViewById(R.id.store_StoreCanvas)!=null)
            if(event.getAction() == MotionEvent.ACTION_DOWN) {
                String text = "You click at x = " + event.getX() + " and y = " + (event.getY()-(findViewById(R.id.store_StoreCanvas).getTop()+getStatusBarHeight()));
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            }
        return super.onTouchEvent(event);
    }

    //endregion

    //region Lists and all their bits
    public void openManageLists(View view) {
        tempPosition=-1;
        listTempPosition =-1;
        getSupportFragmentManager().beginTransaction().replace(R.id.container, RecyclerDisplay.newInstance(listOlists)).commitNow();
    }

    public void addList(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, NewList.newInstance()).commitNow();
    }

    public void createList(View view) {
        EditText Type = findViewById(R.id.newListType);
        EditText Name = findViewById(R.id.newListName);
        if(TextUtils.isEmpty(Type.getText()))
        {
            Type.setText("Grocery");
        }
        if(TextUtils.isEmpty(Name.getText()))
        {
            Name.setText("List " + (listOlists.G_LISTS.size()+1));
        }
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
            changeListName = findViewById(R.id.currentListName);
            changeListType = findViewById(R.id.currentListType);
            if(changeListName!=null)
            {
                changeListName.setText(listOlists.G_LISTS.get(tempPosition).name);
            }
            if(changeListType!=null)
            {
                changeListType.setText(listOlists.G_LISTS.get(tempPosition).type);
            }
            listTempPosition =tempPosition;
            tempPosition=-1;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No list selected to edit", Toast.LENGTH_SHORT).show();
        }
    }

    public void editedList(View view) {
        changeListName = findViewById(R.id.currentListName);
        changeListType = findViewById(R.id.currentListType);
        if(changeListName!=null && changeListType!=null)
        {
            if(!changeListName.getText().equals(listOlists.G_LISTS.get(listTempPosition).name) || !changeListType.getText().equals(listOlists.G_LISTS.get(listTempPosition).type))
            {
                gList li;
                li = new gList(changeListType.getText().toString(),changeListName.getText().toString(),Calendar.getInstance().getTime().toString());
                li.ITEMS = listOlists.G_LISTS.get(listTempPosition).ITEMS;
                listOlists.G_LISTS.set(listTempPosition, li);
            }
        }
        writeListsFile();
        openManageLists(view);
    }

    public void selectList(View view) {
        if(tempPosition != -1) {
            gListSelected = new gList();
            tempgListSelected = null;
            gListSelected = listOlists.G_LISTS.get(tempPosition);
            returnToMenu(view);
            Toast.makeText(getApplicationContext(), "List " + gListSelected.name+ " selected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No list selected", Toast.LENGTH_SHORT).show();
        }
    }

        //region Items
        public void addItem(View view) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, NewItem.newInstance()).commitNow();
        }
        public void createItem(View view){
            EditText Type = findViewById(R.id.newItemType);
            EditText Name = findViewById(R.id.newItemName);
            if(TextUtils.isEmpty(Type.getText()))
            {
                Type.setText("Fruit");
            }
            if(TextUtils.isEmpty(Name.getText()))
            {
                Name.setText("Item " + (listOlists.G_LISTS.get(listTempPosition).ITEMS.size()+1));
            }
            gItem li;
            li = new gItem(Type.getText().toString(),Name.getText().toString(),Calendar.getInstance().getTime().toString());
            listOlists.G_LISTS.get(listTempPosition).ITEMS.add(li);
            writeListsFile();
            tempPosition=-1;
            tempPosition = listTempPosition;
            editList(view);
        }

        public void deleteItem(View view) {
            if(tempPosition != -1) {
                listOlists.G_LISTS.get(listTempPosition).ITEMS.remove(tempPosition);
                tempPosition=-1;
                writeListsFile();
                tempPosition = listTempPosition;
                editList(view);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "No item selected to delete", Toast.LENGTH_SHORT).show();
            }
        }

    public void editItem(View view) {
        if(tempPosition!=-1)
            new gItemEdit(listOlists.G_LISTS.get(listTempPosition).ITEMS.get(tempPosition)).show(getSupportFragmentManager(),"gItemEditDia");
        else
            Toast.makeText(getApplicationContext(), "No item selected to edit", Toast.LENGTH_SHORT).show();
    }
        //endregion

    //endregion

    //region Login functions
    public void openDownloadUpload(View view) {
        if(!signIn)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, Login.newInstance()).commitNow();
    }

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
}