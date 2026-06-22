package com.example.bingeapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bingeapp.database.DatabaseHelper;
import com.example.bingeapp.model.Filme;

public class DetalhesFilme extends AppCompatActivity {

    private TextView txtNomeDetalhe, txtGeneroDetalhe, txtAnoDetalhe, txtStatusDetalhe, txtNotaDetalhe;
    private Button btnEditar, btnExcluir;

    private DatabaseHelper databaseHelper;
    private int filmeId;
    private Filme filme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        txtNomeDetalhe = findViewById(R.id.txtNomeDetalhe);
        txtGeneroDetalhe = findViewById(R.id.txtGeneroDetalhe);
        txtAnoDetalhe = findViewById(R.id.txtAnoDetalhe);
        txtStatusDetalhe = findViewById(R.id.txtStatusDetalhe);
        txtNotaDetalhe = findViewById(R.id.txtNotaDetalhe);

        btnEditar = findViewById(R.id.btnEditar);
        btnExcluir = findViewById(R.id.btnExcluir);

        databaseHelper = new DatabaseHelper(this);

        filmeId = getIntent().getIntExtra("id", -1);

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalhesFilme.this, CadastroFilme.class);
            intent.putExtra("id", filmeId);
            startActivity(intent);
        });

        btnExcluir.setOnClickListener(v -> confirmarExclusao());
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarFilme();
    }

    private void carregarFilme() {
        filme = databaseHelper.buscarFilmePorId(filmeId);

        if (filme == null) {
            Toast.makeText(this, "Filme não encontrado", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        txtNomeDetalhe.setText(filme.getNome());
        txtGeneroDetalhe.setText("Gênero: " + filme.getGenero());
        txtAnoDetalhe.setText("Ano: " + filme.getAno());
        txtStatusDetalhe.setText("Status: " + filme.getStatus());
        txtNotaDetalhe.setText("Nota: " + filme.getNota() + "/5");
    }

    private void confirmarExclusao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Excluir filme");
        builder.setMessage("Tem certeza que deseja excluir este filme?");

        builder.setPositiveButton("Sim", (dialog, which) -> {
            boolean excluiu = databaseHelper.excluirFilme(filmeId);

            if (excluiu) {
                Toast.makeText(this, "Filme excluído com sucesso", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Erro ao excluir filme", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancelar", null);

        builder.show();
    }
}