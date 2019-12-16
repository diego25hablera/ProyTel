package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {


    private TextView textView;
    private Button btntext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView= (TextView) findViewById(R.id.textViewMain);
        btntext = (Button) findViewById(R.id.buttonGoSharing);

        // Para tomar los datos que me envia con el Intent

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.getString("greeter")!= null) {

            String greeter = bundle.getString("greeter");
            Toast.makeText(SecondActivity.this,greeter,Toast.LENGTH_LONG).show();
            textView.setText(greeter);
            }
            else {
                Toast.makeText(SecondActivity.this,"It is Empty! ",Toast.LENGTH_LONG).show();
            }

            btntext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intenttext = new Intent(SecondActivity.this,ThirdActivity.class);
                    startActivity(intenttext);
                }
            });


    }
}
