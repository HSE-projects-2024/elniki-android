package org.hse.elnikimobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Skipass extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skipass);

        drawerLayout = findViewById(R.id.drawerLayout);
        buttonDrawerToggle = findViewById(R.id.buttonDrawerToggle);
        navigationView = findViewById(R.id.navigationView);
        Button buttonZakaz = findViewById(R.id.buttonZakaz);


        buttonDrawerToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });

        buttonZakaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Показать Toast сообщение при нажатии на кнопку
                Toast.makeText(getApplicationContext(), "Покупка", Toast.LENGTH_SHORT).show();
            }
        });


        super.setupMenuItems(); // Вызов родительского метода
    }
}