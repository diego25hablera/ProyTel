package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    private Button btn;
//test push
    private final String GREETER = "Hello from the other side!! ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.buttonMain);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Acceder al Segundo Activity y mandarle un String

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("greeter",GREETER);
                startActivity(intent);


            //Toast.makeText(MainActivity.this,"Otra Vez Hiciste Click al Boton!!",Toast.LENGTH_LONG).show();
            }
        });

       }

}
