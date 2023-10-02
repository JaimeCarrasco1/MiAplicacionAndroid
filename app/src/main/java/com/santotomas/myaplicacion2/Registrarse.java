package com.santotomas.myaplicacion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registrarse extends AppCompatActivity {
EditText cor, pas, nom, ape, num;
Button reg;
DaoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        cor=(EditText) findViewById(R.id.TextCorreo);
        pas=(EditText) findViewById(R.id.TextContraseña);
        nom=(EditText) findViewById(R.id.TextNombre);
        ape=(EditText) findViewById(R.id.TextApellido);
        num=(EditText) findViewById(R.id.TextNumero);

        reg=(Button)findViewById(R.id.registrarButton);

        dao=new DaoUsuario(this);
    }
/*
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrarButton:
                Usuario u = new Usuario();
                u.setCorreo(cor.getText().toString());
                u.setNombre(nom.getText().toString());
                u.setApellido(ape.getText().toString());
                u.setNumero(num.getText().toString());
                u.setContrasenia(pas.getText().toString());
                if (!u.isNull()) {
                    Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
                } else if (dao.insertUsuario(u)) {
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Usuario ya Registrado", Toast.LENGTH_LONG).show();
                }
        }
    }
*/

    public void Registrar(View view){
        Usuario u = new Usuario();
        u.setCorreo(cor.getText().toString());
        u.setNombre(nom.getText().toString());
        u.setApellido(ape.getText().toString());
        u.setNumero(num.getText().toString());
        u.setContrasenia(pas.getText().toString());
        if (!u.isNull()) {
            Toast.makeText(this, "ERROR: Campos vacios", Toast.LENGTH_LONG).show();
        } else if (dao.insertUsuario(u)) {
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(this, Login.class);
            startActivity(i2);
            finish();
        }else{
            Toast.makeText(this, "Usuario ya Registrado", Toast.LENGTH_LONG).show();
        }
    }
    public void IrLogin(View view){
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}