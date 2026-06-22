package com.example.bingeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bingeapp.database.DatabaseHelper;
import com.example.bingeapp.model.Filme;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnAdicionar;
    private ListView listViewFilmes;
    private TextView txtVazio;

    private DatabaseHelper databaseHelper;
    private ArrayList<Filme> listaFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdicionar = findViewById(R.id.btnAdicionar);
        listViewFilmes = findViewById(R.id.listViewFilmes);
        txtVazio = findViewById(R.id.txtVazio);

        databaseHelper = new DatabaseHelper(this);

        btnAdicionar.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CadastroFilme.class);
            startActivity(intent);
        });

        listViewFilmes.setOnItemClickListener((parent, view, position, id) -> {
            Filme filmeSelecionado = listaFilmes.get(position);

            Intent intent = new Intent(MainActivity.this, DetalhesFilme.class);
            intent.putExtra("id", filmeSelecionado.getId());
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarFilmes();
    }

    private void carregarFilmes() {
        listaFilmes = databaseHelper.listarFilmes();

        ArrayList<String> textosFilmes = new ArrayList<>();

        for (Filme filme : listaFilmes) {
            String texto = filme.getNome() +
                    "\n" + filme.getGenero() + " • " + filme.getAno() +
                    "\nStatus: " + filme.getStatus() + " • Nota: " + filme.getNota() + "/5";

            textosFilmes.add(texto);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                textosFilmes
        );

        listViewFilmes.setAdapter(adapter);

        if (listaFilmes.isEmpty()) {
            txtVazio.setVisibility(View.VISIBLE);
            listViewFilmes.setVisibility(View.GONE);
        } else {
            txtVazio.setVisibility(View.GONE);
            listViewFilmes.setVisibility(View.VISIBLE);
        }
    }
}