//MainActivity.java
package com.example.myapplication;

import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MyReceiver myReceiver;
    EditText editTextPhone, editTextContent;
    Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        button.setOnClickListener(v -> {
            String phone = editTextPhone.getText().toString();
            String content = editTextContent.getText().toString();
            myReceiver = new MyReceiver();
            myReceiver.setPhone(phone);
            myReceiver.setContent(content);
            IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.MY_RECEIVER");
            intentFilter.setPriority(1000);
            registerReceiver(myReceiver, intentFilter);
        });
        button2.setOnClickListener(v -> unregisterReceiver(myReceiver));
    }

    private void initView() {
        editTextContent = findViewById(R.id.editTextContent);
        editTextPhone = findViewById(R.id.editTextPhone);
        button = findViewById(R.id.button);
        button = findViewById(R.id.button2);
    }
}