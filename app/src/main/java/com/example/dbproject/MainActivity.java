package com.example.dbproject;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.select.Elements;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb = null;
    TextView NoItemsTV = null;
    ArrayList<ItemObject> Objects = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        ab.setTitle("My shopping app");

        pb = findViewById(R.id.MainProgressBar);
        pb.setVisibility(View.GONE);

        TextView TV = findViewById(R.id.resTV);
        TV.setMovementMethod(new ScrollingMovementMethod());
Objects.add(new ItemObject("name", 0.25f, 255.20f, "descr"));
Objects.add(new ItemObject("name2", 2.00f, 22.04f, "descr2"));
        ListView lv = findViewById(R.id.LoadedList);
        CustomAdapter adapter = new CustomAdapter(this, Objects);
        lv.setAdapter(adapter);

        NoItemsTV = findViewById(R.id.NoItemsText);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar_menu, menu);
        MenuItem favoritesButton = menu.findItem(R.id.favButton);
        favoritesButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, FavActivity.class);
                startActivity(intent);
                return true;
            }
        });
        return true;
    }

    public void onSearchClick(View view)
    {
        pb.setVisibility(View.VISIBLE);
        testFunc();
    }

    void testFunc()
    {

        Parser p = new Parser(MainActivity.this);
        p.execute("телефон");//запуск задачи парсера;

    }
}
