package com.example.entregable;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private ImageView logo;
    private EditText editTextNombre;
    private Button botonEnviar;
    private Button botonCambiar;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NAME = "userName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar las vistas
        textView = findViewById(R.id.texto);
        logo = findViewById(R.id.logo);
        editTextNombre = findViewById(R.id.editTextNombre);
        botonEnviar = findViewById(R.id.botonEnviar);
        botonCambiar = findViewById(R.id.botonCambiar);

        // Inicializar SharedPreferences y se muestra el nombre guardado si existe
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedName = sharedPreferences.getString(KEY_NAME, "");
        if (!savedName.isEmpty()) {
            textView.setText("Hola, " + savedName);
            editTextNombre.setText(savedName);
        }

        // Botón para enviar el nombre
        botonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextNombre.getText().toString().trim();
                if (!name.isEmpty()) {
                    // Guardarel nombre en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_NAME, name);
                    editor.apply();

                    // Actualizar el TextView con "Hola, [nombre]"
                    textView.setText("Hola, " + name);

                    // Mostramos un Snackbar con el nombre enviado
                    Snackbar.make(v, "Nombre enviado: " + name, Snackbar.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(v, "Por favor, ingresa un nombre", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        // Botón para cambiar estilo
        botonCambiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cambiar el fondo a un color aleatorio
                int randomColor = getRandomColor();
                View root = findViewById(R.id.rootLayout);
                root.setBackgroundColor(randomColor);

                // Cambiar el logo a la versión blanca
                logo.setImageResource(R.drawable.logosblanco);

                // Cambiar el color del texto
                textView.setTextColor(getRandomDarkColor());
            }
        });
    }

    // Metodo para generar un color aleatorio
    private int getRandomColor(){
        Random rnd = new Random();
        int red = 100 + rnd.nextInt(156);
        int green = 100 + rnd.nextInt(156);
        int blue = 100 + rnd.nextInt(156);
        return Color.rgb(red, green, blue);
    }

    // Metodo para generar un color aleatorio para el texto
    private int getRandomDarkColor(){
        Random rnd = new Random();
        int red = rnd.nextInt(100);
        int green = rnd.nextInt(100);
        int blue = rnd.nextInt(100);
        return Color.rgb(red, green, blue);
    }
}
