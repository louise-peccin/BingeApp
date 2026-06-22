package com.example.bingeapp;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bingeapp.database.DatabaseHelper;
import com.example.bingeapp.model.Filme;

public class CadastroFilme extends AppCompatActivity {

    private TextView txtTituloCadastro;
    private EditText editNome, editGenero, editAno;
    private Spinner spinnerStatus;
    private RatingBar ratingNota;
    private Button btnSalvar;

    private DatabaseHelper databaseHelper;
    private int filmeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_filme);

        txtTituloCadastro = findViewById(R.id.txtTituloCadastro);
        editNome = findViewById(R.id.editNome);
        editGenero = findViewById(R.id.editGenero);
        editAno = findViewById(R.id.editAno);
        spinnerStatus = findViewById(R.id.spinnerStatus);
        ratingNota = findViewById(R.id.ratingNota);
        btnSalvar = findViewById(R.id.btnSalvar);

        databaseHelper = new DatabaseHelper(this);

        configurarSpinner();

        filmeId = getIntent().getIntExtra("id", -1);

        if (filmeId != -1) {
            txtTituloCadastro.setText("Editar filme");
            carregarDadosFilme();
        }

        btnSalvar.setOnClickListener(v -> salvarFilme());
    }

    private void configurarSpinner() {
        String[] status = {"Assistido", "Quero assistir"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                status
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(adapter);
    }

    private void carregarDadosFilme() {
        Filme filme = databaseHelper.buscarFilmePorId(filmeId);

        if (filme != null) {
            editNome.setText(filme.getNome());
            editGenero.setText(filme.getGenero());
            editAno.setText(String.valueOf(filme.getAno()));
            ratingNota.setRating(filme.getNota());

            if (filme.getStatus().equals("Quero assistir")) {
                spinnerStatus.setSelection(1);
            } else {
                spinnerStatus.setSelection(0);
            }
        }
    }

    private void salvarFilme() {
        String nome = editNome.getText().toString().trim();
        String genero = editGenero.getText().toString().trim();
        String anoTexto = editAno.getText().toString().trim();
        String status = spinnerStatus.getSelectedItem().toString();
        int nota = (int) ratingNota.getRating();

        if (nome.isEmpty()) {
            editNome.setError("Informe o nome do filme");
            editNome.requestFocus();
            return;
        }

        if (genero.isEmpty()) {
            editGenero.setError("Informe o gênero");
            editGenero.requestFocus();
            return;
        }

        if (anoTexto.isEmpty()) {
            editAno.setError("Informe o ano");
            editAno.requestFocus();
            return;
        }

        int ano;

        try {
            ano = Integer.parseInt(anoTexto);
        } catch (NumberFormatException e) {
            editAno.setError("Ano inválido");
            editAno.requestFocus();
            return;
        }

        if (ano < 1900 || ano > 2100) {
            editAno.setError("Informe um ano válido");
            editAno.requestFocus();
            return;
        }

        if (nota < 1 || nota > 5) {
            Toast.makeText(this, "Escolha uma nota de 1 a 5", Toast.LENGTH_SHORT).show();
            return;
        }

        Filme filme = new Filme(nome, genero, ano, status, nota);

        if (filmeId == -1) {
            long resultado = databaseHelper.inserirFilme(filme);

            if (resultado != -1) {
                Toast.makeText(this, "Filme cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao cadastrar filme", Toast.LENGTH_SHORT).show();
            }

        } else {
            filme.setId(filmeId);

            int resultado = databaseHelper.atualizarFilme(filme);

            if (resultado > 0) {
                Toast.makeText(this, "Filme atualizado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao atualizar filme", Toast.LENGTH_SHORT).show();
            }
        }
    }
}