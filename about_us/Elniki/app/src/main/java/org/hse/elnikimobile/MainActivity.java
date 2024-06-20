package org.hse.elnikimobile;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import org.hse.elnikimobile.BaseActivity;
import org.hse.elnikimobile.CustomArrayAdapter;
import org.hse.elnikimobile.ListItemClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    public static final String URL_INSTAGRAM = "https://www.instagram.com/nellkree?igsh=b2FzczRtaWR1cXBi";
    public static final String URL_VK = "https://vk.com/eugen.isay";
    public static final String URL_Telegram = "https://t.me/NellKree";
    public static final String URL_MAP = "https://maps.app.goo.gl/3PpmMWfy7eoU2ypD9";
    private Document doc;
    private Thread secThread;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList1;
    private List<ListItemClass> arrayList2;
    private List<ListItemClass> arrayList3;
    ArrayList<String> firstColumn = new ArrayList<>();
    ArrayList<String> secondColumn = new ArrayList<>();
    ArrayList<String> fifthColumn = new ArrayList<>();
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);
        init();

        // Убедитесь, что данные загружаются и таблица обновляется после загрузки данных
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secThread = new Thread(runnable);
        secThread.start();

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
    }

    private void addRowsToTable(TableLayout tableLayout, ArrayList<String> skipassTypes, ArrayList<String> adultRates, ArrayList<String> childRates) {
        for (int i = 0; i < skipassTypes.size(); i++) {
            TableRow tableRow = new TableRow(this);


            TextView skipassTypeTextView = createFirstColumnTextView(skipassTypes.get(i));
            TextView adultRateTextView = createTextView(adultRates.get(i));
            TextView childRateTextView = createTextView(childRates.get(i));

            tableRow.addView(skipassTypeTextView);
            tableRow.addView(adultRateTextView);
            tableRow.addView(childRateTextView);

            tableLayout.addView(tableRow);
        }
    }

    private TextView createFirstColumnTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextAppearance(R.style.FirstColumnText);
        return textView;
    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        textView.setTextAppearance(R.style.TableText);
        return textView;
    }

    // для запуска второго потока
    private void init() {
        listView = findViewById(R.id.listView);
        arrayList1 = new ArrayList<>();
        arrayList2 = new ArrayList<>();
        arrayList3 = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_view_item_1, arrayList1, getLayoutInflater());
        listView.setAdapter(adapter);
    }

    private void getWeb() {
        try {
            doc = Jsoup.connect("https://elniki-glc.ru/#стоимость").get();
            Elements table = doc.getElementsByTag("tbody");
            Element our_table = table.get(0);

            for (int i = 0; i < our_table.childrenSize(); i++) {
                // Парсинг данных из нужных столбцов
                String data1 = our_table.children().get(i).child(0).text();
                String data2 =

                our_table.children().get(i).child(1).text();
                String data5 = our_table.children().get(i).child(4).text();
                // Добавление данных в соответствующие массивы
                firstColumn.add(data1);
                secondColumn.add(data2);
                fifthColumn.add(data5);
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged(); // адаптер нельзя трогать с второстепенного потока
                    addRowsToTable(tableLayout, firstColumn, secondColumn, fifthColumn);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}