package org.hse.elnikimobile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends BaseActivity {

    public static final String URL_INSTAGRAM = "https://www.instagram.com/nellkree?igsh=b2FzczRtaWR1cXBi";
    public static final String URL_VK = "https://vk.com/eugen.isay";
    public static final String URL_Telegram = "https://t.me/NellKree";
    public static final String URL_MAP = "https://maps.app.goo.gl/3PpmMWfy7eoU2ypD9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        Button buttonServices = findViewById(R.id.buttonServices);
        Button buttonAboutTheResort = findViewById(R.id.buttonAboutTheResort);

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

        buttonServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ServicesActivity.class);
                startActivity(intent);
            }
        });

        buttonAboutTheResort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
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
    private void openSkipassActivity() {
        Intent intent = new Intent(this, org.hse.elnikimobile.Skipass.class);
        startActivity(intent);
    }

}