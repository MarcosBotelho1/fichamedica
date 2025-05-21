package com.example.fichamedica;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class EstatisticasActivity extends AppCompatActivity {

    private TextView tvTotal, tvMediaIdade, tvMediaImc;
    private FichaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        dbHelper = new FichaDbHelper(this);

        tvTotal = findViewById(R.id.tvTotal);
        tvMediaIdade = findViewById(R.id.tvMediaIdade);
        tvMediaImc = findViewById(R.id.tvMediaImc);

        carregarEstatisticas();
    }

    private void carregarEstatisticas() {
        int total = dbHelper.getTotalFichas();
        double mediaIdade = dbHelper.getMediaIdade();
        double mediaImc = dbHelper.getMediaIMC();

        tvTotal.setText("Total de fichas: " + total);
        tvMediaIdade.setText("Média de idade: " + String.format("%.1f", mediaIdade) + " anos");
        tvMediaImc.setText("Média de IMC: " + String.format("%.1f", mediaImc));
    }
}