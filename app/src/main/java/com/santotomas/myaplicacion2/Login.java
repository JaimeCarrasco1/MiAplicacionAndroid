package com.santotomas.myaplicacion2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity  {
EditText correo, pass;
Button btnLogin;
DaoUsuario dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo=(EditText)findViewById(R.id.editTextText2);
        pass=(EditText)findViewById(R.id.editTextTextPassword2);
        btnLogin=(Button) findViewById(R.id.button);
        dao= new DaoUsuario(this);

        Intent serviceIntent = new Intent(this, ProximityService.class);
        startService(serviceIntent);

    }



    public void IrRegistro(View view){
        Intent i = new Intent(this, Registrarse.class);
        startActivity(i);
    }

    public void IrMain(View view){
        String u=correo.getText().toString();
        String p=pass.getText().toString();
        if(u.equals("")&&p.equals("")){
            Toast.makeText(this,"Ingrese las credenciales",Toast.LENGTH_LONG).show();
        }else if(dao.login(u,p)==1){
            Usuario ux=dao.getUsuario(u,p);
            Toast.makeText(this,"Ingreso Correcto",Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("Id", ux.getId());
            startActivity(i);
            finish();
        }else{
            Toast.makeText(this,"Correo y/o Contraseña incorrectos",Toast.LENGTH_LONG).show();
        }

    }

}