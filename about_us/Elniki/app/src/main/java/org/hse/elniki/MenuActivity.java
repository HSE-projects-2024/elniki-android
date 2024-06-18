package org.hse.elniki;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        View buttonClose = findViewById(R.id.btnClose);
        TextView homePage = findViewById(R.id.homePage);
        TextView services = findViewById(R.id.services);
        TextView accommodation = findViewById(R.id.accommodation);
        TextView resort = findViewById(R.id.resort);
        TextView contacts = findViewById(R.id.contacts);


        homePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Код для перехода на определенную страницу
            }
        });

        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Код для перехода на определенную страницу
            }
        });

        accommodation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Код для перехода на определенную страницу
            }
        });

        resort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Код для перехода на определенную страницу
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Код для перехода на определенную страницу
            }
        });


        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
