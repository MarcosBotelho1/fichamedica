package com.example.fichamedica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCadastrar = findViewById(R.id.btnCadastrar);
        Button btnConsultar = findViewById(R.id.btnConsultar);
        Button btnHistorico = findViewById(R.id.btnHistorico);
        Button btnEstatisticas = findViewById(R.id.btnEstatisticas);

        btnCadastrar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
            startActivity(intent);
        });

        btnConsultar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VisualizacaoActivity.class);
            startActivity(intent);
        });

        btnHistorico.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
            startActivity(intent);
        });

        btnEstatisticas.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EstatisticasActivity.class);
            startActivity(intent);
        });
    }
}