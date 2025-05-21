package com.example.fichamedica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroActivity extends AppCompatActivity {

    private EditText etNome, etIdade, etPeso, etAltura, etPressao;
    private Button btnSalvar;
    private FichaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        dbHelper = new FichaDbHelper(this);

        etNome = findViewById(R.id.etNome);
        etIdade = findViewById(R.id.etIdade);
        etPeso = findViewById(R.id.etPeso);
        etAltura = findViewById(R.id.etAltura);
        etPressao = findViewById(R.id.etPressao);
        btnSalvar = findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(v -> salvarFicha());
    }

    private void salvarFicha() {
        String nome = etNome.getText().toString();
        String idadeStr = etIdade.getText().toString();
        String pesoStr = etPeso.getText().toString();
        String alturaStr = etAltura.getText().toString();
        String pressao = etPressao.getText().toString();

        if (nome.isEmpty() || idadeStr.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty() || pressao.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int idade = Integer.parseInt(idadeStr);
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);

            FichaSaude ficha = new FichaSaude(nome, idade, peso, altura, pressao);
            dbHelper.addFicha(ficha);

            Toast.makeText(this, "Ficha salva com sucesso!", Toast.LENGTH_SHORT).show();
            limparCampos();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Valores inválidos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        etNome.setText("");
        etIdade.setText("");
        etPeso.setText("");
        etAltura.setText("");
        etPressao.setText("");
        etNome.requestFocus();
    }
}
