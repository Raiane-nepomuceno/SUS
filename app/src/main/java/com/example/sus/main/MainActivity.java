package com.example.sus.main;


import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sus.R;
import com.example.sus.bd.DB;
import com.example.sus.model.Consulta;

public class MainActivity extends AppCompatActivity {
        private EditText nomeEt,telefoneEt,dataEt;
        private Button clean,insert,delete,update,view;
        private DB db;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            nomeEt = findViewById(R.id.nomeEt);
            telefoneEt = findViewById(R.id.telefoneEt);
            dataEt = findViewById(R.id.dataEt);


            clean = findViewById(R.id.btnLimpar);

            insert = findViewById(R.id.btnSalvar);
            update = findViewById(R.id.btnEditar);
            delete = findViewById(R.id.btnDeletar);
            view = findViewById(R.id.btnView);

            db = new DB(this);
            insert.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if (nomeEt != null && telefoneEt != null && dataEt != null) {
                        Consulta consulta = new Consulta();
                        consulta.setNome(nomeEt.getText().toString());
                        consulta.setTelefone(telefoneEt.getText().toString());
                        consulta.setDataConsulta(dataEt.getText().toString());

                        Boolean resultado = db.insertConsulta(consulta);
                        if (resultado == true) {
                            Toast.makeText(MainActivity.this, "Consulta cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Houve erro no cadastro da consulta.", Toast.LENGTH_SHORT).show();
                        }


                    }


                }
            });


            clean.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (nomeEt != null || telefoneEt != null  || dataEt != null) {
                        nomeEt.getText().clear();
                        telefoneEt.getText().clear();
                        dataEt.getText().clear();

                    } else {
                        Toast.makeText(MainActivity.this, "Os campos já estão apagados :)", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Consulta consulta = new Consulta();
                    consulta.setNome(nomeEt.getText().toString());
                    consulta.setTelefone(telefoneEt.getText().toString());
                    consulta.setDataConsulta(dataEt.getText().toString());

                    Boolean resultado = db.updateConsulta(consulta);
                    if(resultado==true)
                        Toast.makeText(MainActivity.this, "Consulta atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Ocorreu algum erro, tente novamente", Toast.LENGTH_SHORT).show();
                }
            });
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String telefone = telefoneEt.getText().toString();
                    Boolean resultado = db.deleteConsulta(telefone);
                    if(resultado==true)
                        Toast.makeText(MainActivity.this, "Consulta deletada com sucesso!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(MainActivity.this, "Não foi possível deletar a consulta", Toast.LENGTH_SHORT).show();
                }
            });
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Cursor res = db.getConsultas();
                    if(res.getCount()==0){
                        Toast.makeText(MainActivity.this, "Consulta não encontrada", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Nome: "+res.getString(1)+"\n");
                        buffer.append("Contato: "+res.getString(0)+"\n");
                        buffer.append("Data da consulta: "+res.getString(2)+"\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("Entradas");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            });
        }
    }

