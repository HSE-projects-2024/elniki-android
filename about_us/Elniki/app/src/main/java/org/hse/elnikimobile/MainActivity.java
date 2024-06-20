package org.hse.elnikimobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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
    private List<ListItemClass> arrayList;
    ArrayList<String> firstColumn = new ArrayList<>();
    ArrayList<String> secondColumn = new ArrayList<>();
    ArrayList<String> fifthColumn = new ArrayList<>();
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);
        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);

        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });


        super.setupMenuItems(); // Вызов родительского метода

        ImageButton imageButtonInst = findViewById(R.id.imageButtonInst);
        ImageButton imageButtonVK = findViewById(R.id.imageButtonVK);
        ImageButton imageButtonTelegram = findViewById(R.id.imageButtonTelegram);
        ImageButton imageButtonMap = findViewById(R.id.imageButtonMap);

        imageButtonInst.setOnClickListener(v -> openUrl(URL_INSTAGRAM));

        imageButtonVK.setOnClickListener(v -> openUrl(URL_VK));

        imageButtonTelegram.setOnClickListener(v -> openUrl(URL_Telegram));

        imageButtonMap.setOnClickListener(v -> openUrl(URL_MAP));

        Button buttonContact = findViewById(R.id.buttonContact);
        buttonContact.setOnClickListener(v -> {
            openContactActivity();
        });
        Button buttonSkipass = findViewById(R.id.buttonSkipass);
        buttonSkipass.setOnClickListener(v -> {
            openSkipassActivity();
        });
        Button buttonServices = findViewById(R.id.buttonServices);
        buttonServices.setOnClickListener(v -> {
            openServicesActivity();
        });
        Button buttonAboutTheResort = findViewById(R.id.buttonAboutTheResort);
        buttonAboutTheResort.setOnClickListener(v -> {
            openAboutUsActivity();
        });

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

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void onMenuButtonClick(View view) {

        Toast.makeText(this, "MenuButton clicked!", Toast.LENGTH_SHORT).show();
    }

    private void openContactActivity() {
        Intent intent = new Intent(this, org.hse.elnikimobile.Contact2Activity.class);
        startActivity(intent);
    }
    private void openServicesActivity() {
        Intent intent = new Intent(this, org.hse.elnikimobile.ServicesActivity.class);
        startActivity(intent);
    }

    private void openAboutUsActivity() {
        Intent intent = new Intent(this, org.hse.elnikimobile.AboutUsActivity.class);
        startActivity(intent);
    }
    private void openSkipassActivity() {
        Intent intent = new Intent(this, org.hse.elnikimobile.Skipass.class);
        startActivity(intent);
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
        textView.setGravity(Gravity.LEFT);
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
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_view_item_1, arrayList, getLayoutInflater());
        listView.setAdapter(adapter);
    }

    private void getWeb() {
        try {
            doc = Jsoup.connect("https://elniki-glc.ru/#стоимость").get();
            Elements table = doc.getElementsByTag("tbody");
            Element our_table = table.get(0);

            for (int i = 1; i < our_table.childrenSize(); i++) {
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