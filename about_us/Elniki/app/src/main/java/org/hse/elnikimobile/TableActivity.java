package org.hse.elnikimobile;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
    }

    //для запуска второго потока
    private void init() {
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_view_item_1, arrayList,getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();

        /*ListItemClass items = new ListItemClass();
        items.setData_1("Type 1");
        items.setData_2("100");
        items.setData_3("200");
        items.setData_4("50");
        items.setData_5("0");
        items.setData_6("150");
        items.setData_7("100");
        arrayList.add(items);
        items = new ListItemClass();
        items.setData_1("Type 2");
        items.setData_2("10");
        items.setData_3("0");
        items.setData_4("500");
        items.setData_5("45");
        items.setData_6("150");
        items.setData_7("100");
        arrayList.add(items);
        adapter.notifyDataSetChanged();*/



    }

    private void getWeb(){
        try {
            doc = Jsoup.connect("https://elniki-glc.ru/#стоимость").get();
            Elements table = doc.getElementsByTag("tbody");
            Element our_table = table.get(0);
            for (int i = 0; i < our_table.childrenSize(); i++){
                ListItemClass items = new ListItemClass();
                items.setData_1(our_table.children().get(i).child(0).text());
                items.setData_2(our_table.children().get(i).child(1).text());
                items.setData_3(our_table.children().get(i).child(2).text());
                items.setData_4(our_table.children().get(i).child(3).text());
                items.setData_5(our_table.children().get(i).child(4).text());
                items.setData_6(our_table.children().get(i).child(5).text());
                items.setData_7(our_table.children().get(i).child(6).text());
                arrayList.add(items);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged(); //адаптер нельзя трогать с второстепенного потока
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}