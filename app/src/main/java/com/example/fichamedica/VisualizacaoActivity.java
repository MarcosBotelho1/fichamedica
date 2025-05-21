package com.example.fichamedica;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class VisualizacaoActivity extends AppCompatActivity {

    private TextView tvNome, tvIdade, tvPeso, tvAltura, tvPressao, tvImc, tvInterpretacao;
    private FichaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizacao);

        dbHelper = new FichaDbHelper(this);

        tvNome = findViewById(R.id.tvNome);
        tvIdade = findViewById(R.id.tvIdade);
        tvPeso = findViewById(R.id.tvPeso);
        tvAltura = findViewById(R.id.tvAltura);
        tvPressao = findViewById(R.id.tvPressao);
        tvImc = findViewById(R.id.tvImc);
        tvInterpretacao = findViewById(R.id.tvInterpretacao);

        Intent intent = getIntent();
        int fichaId = intent.getIntExtra("FICHA_ID", -1);

        if(fichaId != -1) {
            carregarUltimaFicha(fichaId);
        }
        else {
            carregarUltimaFicha();
        }

    }

    private void carregarUltimaFicha() {
        int total = dbHelper.getTotalFichas();

        if (dbHelper.getTotalFichas() > 0) {
            FichaSaude ficha = dbHelper.getFicha(total);

            tvNome.setText("Nome: " + ficha.getNome());
            tvIdade.setText("Idade: " + ficha.getIdade() + " anos");
            tvPeso.setText("Peso: " + ficha.getPeso() + " kg");
            tvAltura.setText("Altura: " + ficha.getAltura() + " m");
            tvPressao.setText("Pressão Arterial: " + ficha.getPressaoArterial());

            double imc = ficha.calcularIMC();
            tvImc.setText("IMC: " + String.format("%.2f", imc));
            tvInterpretacao.setText("Classificação: " + ficha.interpretarIMC());
        } else {
            tvNome.setText("Nenhuma ficha cadastrada");
        }
    }
    private void carregarUltimaFicha(int numero) {

        if (dbHelper.getTotalFichas() > 0) {
            FichaSaude ficha = dbHelper.getFicha(numero);

            tvNome.setText("Nome: " + ficha.getNome());
            tvIdade.setText("Idade: " + ficha.getIdade() + " anos");
            tvPeso.setText("Peso: " + ficha.getPeso() + " kg");
            tvAltura.setText("Altura: " + ficha.getAltura() + " m");
            tvPressao.setText("Pressão Arterial: " + ficha.getPressaoArterial());

            double imc = ficha.calcularIMC();
            tvImc.setText("IMC: " + String.format("%.2f", imc));
            tvInterpretacao.setText("Classificação: " + ficha.interpretarIMC());
        } else {
            tvNome.setText("Nenhuma ficha cadastrada");
        }
    }
}
