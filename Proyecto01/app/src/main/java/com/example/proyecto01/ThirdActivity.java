package com.example.proyecto01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    // Aqui se crean las variables

    private EditText edittextPhone;
    private EditText edittextWeb;
    private ImageButton imagebtnphone;
    private ImageButton imagebtnweb;
    private ImageButton imagebtncamera;

    private final int PHONE_CALL_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Aqui se crean los metodos para que busquen la vista por "Id"

        edittextPhone = (EditText) findViewById(R.id.editTextPhone);
        edittextWeb = (EditText) findViewById(R.id.editTextWeb);
        imagebtnphone = (ImageButton) findViewById(R.id.imageButtonPhone);
        imagebtnweb = (ImageButton) findViewById(R.id.imageButtonWeb);
        imagebtncamera = (ImageButton) findViewById(R.id.imageButtonCamera);

        //Aqui llamas a la funcion "OnClickListener"
        imagebtnphone.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phoneNumber = edittextPhone.getText().toString();
                if (phoneNumber != null || phoneNumber.isEmpty()) {

                    //Comprobar version de android corriendo la aplicacion
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, PHONE_CALL_CODE);
                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
                else{
                    Toast.makeText(ThirdActivity.this, "You declined the Access", Toast.LENGTH_LONG).show();
                }
            }

            //Se crean dos metodos para versiones nuevas y antiguas (Para la llamada del telefono)

            private void OlderVersions(String phoneNumber) {
                Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phoneNumber));
                if (checkPermission(Manifest.permission.CALL_PHONE)) {
                    startActivity(intentcall);
                } else {
                    Toast.makeText(ThirdActivity.this, "You declined the Access", Toast.LENGTH_LONG).show();
                }
            }

        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Estamos en el caso del Telefono
        //if( requestCode == PHONE_CALL_CODE)
        switch (requestCode) {

            case PHONE_CALL_CODE:
                ;

                String permission = permissions[0];
                int result = grantResults[0];
                if (permission.equals(Manifest.permission.CALL_PHONE)) {

                    // Comprobar si ha sido aceptado o denegado la peticion de permiso
                    if (result == PackageManager.PERMISSION_GRANTED) {

                        //Concedio su Permiso
                        String phonenumber = edittextPhone.getText().toString();
                        Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phonenumber));
                        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) return;
                        startActivity(intentcall);

                        }
                            else{
                                // No se concedio el Permiso
                                Toast.makeText(ThirdActivity.this,"You declined the Access",Toast.LENGTH_LONG).show();
                        }
                }
                break;

            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }

    }

    // Este metodo sirve para comprobar permisos

        private boolean checkPermission(String permission){
           int result = this.checkCallingOrSelfPermission(permission);
           return result == PackageManager.PERMISSION_GRANTED;
        }
}
