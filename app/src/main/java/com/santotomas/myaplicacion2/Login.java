package com.santotomas.myaplicacion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity  {
EditText correo, pass;
Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo=(EditText)findViewById(R.id.editTextText2);
        pass=(EditText)findViewById(R.id.editTextTextPassword2);
        btnLogin=(Button) findViewById(R.id.button);


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