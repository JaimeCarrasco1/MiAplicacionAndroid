package com.santotomas.myaplicacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Calendar;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.TimePicker;


public class Agendar extends AppCompatActivity {
    Button Btn_Calendario;
    TextView Fecha;
    int dia, mes, año;
    private TimePicker timePicker;
    private TextView selectedTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar);

        Spinner spinner = findViewById(R.id.Barberos);
        TextView textViewDefault = findViewById(R.id.NombreDefecto);

        timePicker = findViewById(R.id.timePicker);
        selectedTimeTextView = findViewById(R.id.selectedTimeTextView);

        // Configura un listener para el TimePicker
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                // Actualiza el TextView con la hora seleccionada
                selectedTimeTextView.setText("Hora seleccionada: " + hourOfDay + ":" + minute);
            }
        });


        // Crear un ArrayAdapter con los nombres
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.add("Matias Carrasco");
        adapter.add("Diego Armando");
        adapter.add("Brayan Cortes");

        // Establecer el adaptador para el Spinner
        spinner.setAdapter(adapter);


    Btn_Calendario = findViewById(R.id.btn_Calendario);
        Btn_Calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendario = Calendar.getInstance();
                dia = calendario.get(Calendar.DAY_OF_MONTH);
                mes = calendario.get(Calendar.MONTH);
                año = calendario.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog= new DatePickerDialog(Agendar.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int AñoSelect, int MesSelect, int DiaSelect) {
                        String diaFormateado, mesFormateado;

                        //OBTENER DIA
                        if (DiaSelect < 10){
                            diaFormateado = "0"+String.valueOf(DiaSelect);
                        }else{
                            diaFormateado = String.valueOf(DiaSelect);
                        }

                        int mes = MesSelect + 1;
                        if (mes < 10){
                            mesFormateado = "0"+String.valueOf(mes);
                        }else{
                            mesFormateado = String.valueOf(mes);
                        }


                        TextView Fecha = findViewById(R.id.Fecha); // Encuentra el TextView por su ID
                        Fecha.setText(diaFormateado + "/" + mesFormateado + "/" + AñoSelect);


                    }
                }
                ,año,dia,mes);
                datePickerDialog.show();


            }
        });
    }
}