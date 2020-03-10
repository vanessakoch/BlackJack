package com.example.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CadastroActivity extends AppCompatActivity {
    EditText txtUsernameCadastro;
    EditText txtNomeCadastro;
    EditText txtSenhaCadastro;
    EditText txtEmailCadastro;
    Button btnCadastrar;
    List<User> cadastrosList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        txtUsernameCadastro = (EditText) findViewById(R.id.txtUserCadastro);
        txtNomeCadastro = (EditText) findViewById(R.id.txtNomeCadastro);
        txtSenhaCadastro = (EditText) findViewById(R.id.txtSenhaCadastro);
        txtEmailCadastro = (EditText) findViewById(R.id.txtEmailCadastro);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        cadastrosList = new ArrayList<User>();

        Bundle bundle = getIntent().getExtras();
        cadastrosList = (List<User>) bundle.getSerializable("lista");

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C1C1C")));


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtUsernameCadastro.getText().length() == 0 || txtSenhaCadastro.getText().length() == 0 || txtNomeCadastro.getText().length() == 0 || txtEmailCadastro.getText().length() == 0) {
                    Toast.makeText(getApplication(),
                            "Atenção, preencha todos os itens antes de concluír!",
                            Toast.LENGTH_SHORT).show();

                }
                if (txtUsernameCadastro.getText().toString().equals("")) {
                    txtUsernameCadastro.setBackgroundTintList(ColorStateList.valueOf(-65536));
                }
                if (txtEmailCadastro.getText().toString().equals("")) {
                    txtEmailCadastro.setBackgroundTintList(ColorStateList.valueOf(-65536));
                }
                if (txtNomeCadastro.getText().toString().equals("")) {
                    txtNomeCadastro.setBackgroundTintList(ColorStateList.valueOf(-65536));
                }
                if (txtSenhaCadastro.getText().toString().equals("")) {
                    txtSenhaCadastro.setBackgroundTintList(ColorStateList.valueOf(-65536));
                }
                else {
                    if(cadastrosList.size() > 0) {
                        for (User u : cadastrosList){
                            if (txtUsernameCadastro.getText().toString().equals(u.getUsername()) &&
                                    txtSenhaCadastro.getText().toString().equals(u.getSenha())) {
                                Toast.makeText(getApplication(),
                                        "Atenção, este usuário já tem cadastro!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                if(txtUsernameCadastro.getText().length() > 0 && txtSenhaCadastro.getText().length() > 0 &&
                                        txtNomeCadastro.getText().length() > 0 && txtEmailCadastro.getText().length() > 0) {
                                    cadastrosList.add(new User(txtUsernameCadastro.getText().toString(), txtSenhaCadastro.getText().toString(),
                                            txtNomeCadastro.getText().toString(), txtEmailCadastro.getText().toString(), 0, 0));
                                    retornoMain();
                                }
                            }
                        }
                    }else{
                        if(txtUsernameCadastro.getText().length() > 0 && txtSenhaCadastro.getText().length() > 0 &&
                                txtNomeCadastro.getText().length() > 0 && txtEmailCadastro.getText().length() > 0) {
                            cadastrosList.add(new User(txtUsernameCadastro.getText().toString(), txtSenhaCadastro.getText().toString(), txtNomeCadastro.getText().toString(), txtEmailCadastro.getText().toString(), 0, 0));
                            retornoMain();
                        }
                    }
                }
            }
        });
    }
    public void retornoMain(){
        Toast.makeText(getApplication(),
                "Cadastro realizado com sucesso!",
                Toast.LENGTH_SHORT).show();

        txtUsernameCadastro.setText("");
        txtSenhaCadastro.setText("");
        txtNomeCadastro.setText("");
        txtEmailCadastro.setText("");

        Bundle bundle = new Bundle();
        bundle.putSerializable("lista", (Serializable) cadastrosList);
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}

