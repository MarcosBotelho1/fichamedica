package com.example.fichamedica;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;


public class HistoricoActivity extends AppCompatActivity {

    private ListView lvFichas;
    private FichaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        dbHelper = new FichaDbHelper(this);
        lvFichas = findViewById(R.id.lvFichas);

        carregarFichas();
    }

    private void carregarFichas() {
        List<FichaSaude> fichas = dbHelper.getAllFichas();
        ArrayAdapter<FichaSaude> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                fichas);
        lvFichas.setAdapter(adapter);

        lvFichas.setOnItemClickListener((parent, view, position, id) -> {
            FichaSaude fichaSelecionada = fichas.get(position);
            Intent intent = new Intent(HistoricoActivity.this, VisualizacaoActivity.class);
            intent.putExtra("FICHA_ID", fichaSelecionada.getId());
            startActivity(intent);
        });
    }
}

