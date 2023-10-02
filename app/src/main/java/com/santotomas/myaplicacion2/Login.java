package com.santotomas.myaplicacion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void IrRegistro(View view){
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);
    }

    public void IrMain(View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}