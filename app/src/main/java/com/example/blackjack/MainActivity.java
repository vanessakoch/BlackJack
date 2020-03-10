package com.example.blackjack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button btnCadastro;
    Button btnJogar;
    EditText txtLogin;
    EditText txtPassword;
    CheckBox check;
    public List<User> cadastrosList;
    public static final int tela_cadastro = 1;
    public static final int tela_jogo = 2;
    private boolean loginExists;
    User retorno = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnJogar = (Button) findViewById(R.id.btnJogar);
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        check = (CheckBox) findViewById(R.id.checkBox);
        cadastrosList = new ArrayList<User>();

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked)
                    txtPassword.setTransformationMethod(new PasswordTransformationMethod());
                else
                    txtPassword.setTransformationMethod(null);
            }
        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C1C1C")));
    }

    public void onClickCadastrar(View v) {
        Intent intent = new Intent(this, CadastroActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("lista", (Serializable) cadastrosList);
        intent.putExtras(bundle);
        startActivityForResult(intent, tela_cadastro);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == tela_cadastro){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                cadastrosList = (List<User>) bundle.getSerializable("lista");
            }
        }
        if(requestCode == tela_jogo){
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = data.getExtras();
                retorno = (User) bundle.getSerializable("logado");
                for(int i = 0; i< cadastrosList.size(); i++){
                    if(retorno.getUsername().equals(cadastrosList.get(i).getUsername()) &&
                            retorno.getSenha().equals(cadastrosList.get(i).getSenha())){
                        cadastrosList.remove(cadastrosList.get(i));
                        cadastrosList.add(retorno);
                    }
                }
            }
        }
    }


    public void onClickJogar(View v) {
        if (txtLogin.getText().length() == 0 || txtPassword.getText().length() == 0) {
            Toast.makeText(getApplication(), "É necessário uma conta para jogar!", Toast.LENGTH_LONG).show();
            if(txtLogin.getText().toString().equals("")){
                txtLogin.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
            if(txtPassword.getText().toString().equals("")){
                txtPassword.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            }
        }else{
            for (int i = 0; i < cadastrosList.size(); i++) {
                if (txtLogin.getText().toString().equals(cadastrosList.get(i).getUsername()) &&
                        txtPassword.getText().toString().equals(cadastrosList.get(i).getSenha())) {
                    login(cadastrosList.get(i));
                    loginExists = true;
                }
            }
            if(!loginExists){
                Toast.makeText(getApplication(),
                        "Usuário e/ou Senha incorretos!",
                        Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void login(User user){
        Intent intent = new Intent(MainActivity.this, BlackJackActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", (Serializable) user);
        intent.putExtras(bundle);
        startActivityForResult(intent, tela_jogo);

        Toast.makeText(getApplication(),
                "Seja bem vindo(a) " + user.getUsername(),
                Toast.LENGTH_SHORT).show();

        txtLogin.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
        txtPassword.setBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
        txtLogin.setText("");
        txtPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}