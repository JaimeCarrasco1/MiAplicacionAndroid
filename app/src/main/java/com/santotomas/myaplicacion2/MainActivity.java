package com.santotomas.myaplicacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView nombre;
int id=0;
Usuario u;
DaoUsuario dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre=(TextView) findViewById(R.id.NombreUsuario);

        Bundle b=getIntent().getExtras();
        id=b.getInt("Id");
        dao=new DaoUsuario(this);
        u=dao.getUsuarioById(id);
        nombre.setText("Bienvenido "+u.getNombre());
    }
    public void IrAgendar(View view){
        Intent i = new Intent(this, Agendar.class);
        startActivity(i);
    }

    public void AbrirMapa(View view){
        Intent i2 = new Intent(this,Maps.class);
        startActivity(i2);
    }
}