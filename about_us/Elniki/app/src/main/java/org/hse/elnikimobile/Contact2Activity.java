package org.hse.elnikimobile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Contact2Activity extends BaseActivity {

    private EditText messageEditText;
    private EditText emailEditText;
    private CheckBox robotCheckBox;
    private CheckBox rodoCheckBox;
    private CheckBox copyCheckBox;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact2);

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

        messageEditText = findViewById(R.id.messageEditText);
        emailEditText = findViewById(R.id.emailEditText);
        robotCheckBox = findViewById(R.id.robotCheckBox);
        rodoCheckBox = findViewById(R.id.rodoCheckBox);
        copyCheckBox = findViewById(R.id.copyCheckBox);
        buttonSend = findViewById(R.id.buttonSend);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString().trim();
                String email = emailEditText.getText().toString().trim();
                boolean isRobotChecked = robotCheckBox.isChecked();

                if (message.isEmpty() || email.isEmpty() || !isRobotChecked) {
                    Toast.makeText(Contact2Activity.this, R.string.fill_in_all_required_fields, Toast.LENGTH_SHORT).show();
                } else {
                    // Clear all fields
                    messageEditText.setText("");
                    emailEditText.setText("");
                    robotCheckBox.setChecked(false);
                    rodoCheckBox.setChecked(false);
                    copyCheckBox.setChecked(false);

                    Toast.makeText(Contact2Activity.this, R.string.form_submitted_successfully, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}