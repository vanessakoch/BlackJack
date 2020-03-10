package com.example.blackjack;

import android.app.Activity;
import android.content.Intent;
import android.content.SearchRecentSuggestionsProvider;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Random;

public class BlackJackActivity extends AppCompatActivity {
    Button btnStop;
    Button btnComprar;
    ImageView userCard1;
    ImageView userCard2;
    ImageView userCard3;
    ImageView userCard4;
    ImageView userCard5;
    ImageView card1;
    ImageView card2;
    ImageView card3;
    ImageView card4;
    ImageView card5;
    TextView win;
    TextView lose;
    TextView userScore;
    TextView dealerScore;
    private int[][] matrizCartas;
    Dealer dealer = new Dealer();
    Button btnPlay;
    User logado = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.blackjack);

        btnStop = (Button) findViewById(R.id.btnStop);
        btnComprar = (Button) findViewById(R.id.btnComprar);
        win = (TextView) findViewById(R.id.txtWin);
        lose = (TextView) findViewById(R.id.txtLose);
        dealerScore = (TextView) findViewById(R.id.dealerScore);
        userScore = (TextView) findViewById(R.id.userScore);
        userCard1 = (ImageView) findViewById(R.id.userCard1);
        userCard2 = (ImageView) findViewById(R.id.userCard2);
        userCard3 = (ImageView) findViewById(R.id.userCard3);
        userCard4 = (ImageView) findViewById(R.id.userCard4);
        userCard5 = (ImageView) findViewById(R.id.userCard5);
        card1 = (ImageView) findViewById(R.id.card1);
        card2 = (ImageView) findViewById(R.id.card2);
        card3 = (ImageView) findViewById(R.id.card3);
        card4 = (ImageView) findViewById(R.id.card4);
        card5 = (ImageView) findViewById(R.id.card5);

        btnPlay = (Button) findViewById(R.id.btnComprar);

        Bundle extras = getIntent().getExtras();
        logado = (User) extras.getSerializable("user");


        getSupportActionBar().setTitle(logado.getUsername());

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C1C1C")));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void iniciarJogo(){
        btnStop.setVisibility(View.VISIBLE);
        btnComprar.setVisibility(View.VISIBLE);
        matrizCartas = new int[][]{
                {R.drawable.ha,11}, {R.drawable.h2, 2}, {R.drawable.h3,3}, {R.drawable.h4,4}, {R.drawable.h5,5},
                {R.drawable.h6,6}, {R.drawable.h7,7}, {R.drawable.h8,8}, {R.drawable.h9,9}, {R.drawable.h10,10},
                {R.drawable.hj,10}, {R.drawable.hq,10}, {R.drawable.hk,10}, {R.drawable.sa,11}, {R.drawable.s2,2},
                {R.drawable.s3,3}, {R.drawable.s4,4}, {R.drawable.s5,5}, {R.drawable.s6,6}, {R.drawable.s7,7},
                {R.drawable.s8,8}, {R.drawable.s9,9}, {R.drawable.s10,10}, {R.drawable.sj,10}, {R.drawable.sq,10},
                {R.drawable.sk,10}, {R.drawable.da,11}, {R.drawable.d2,2}, {R.drawable.d3,3}, {R.drawable.d4,4},
                {R.drawable.d5,5}, {R.drawable.d6,6}, {R.drawable.d7,7}, {R.drawable.d8,8}, {R.drawable.d9,9},
                {R.drawable.d10,10}, {R.drawable.dj,10}, {R.drawable.dq,10}, {R.drawable.dk,10}, {R.drawable.pa,11},
                {R.drawable.p2,2}, {R.drawable.p3,3}, {R.drawable.p4,4}, {R.drawable.p5,5}, {R.drawable.p6,6},
                {R.drawable.p7,7}, {R.drawable.p8,8}, {R.drawable.p9,9}, {R.drawable.p10,10}, {R.drawable.pj,10},
                {R.drawable.pq,10}, {R.drawable.pk,10}};

        darCartasIniciais();
        checkScore();
        win.setText(String.valueOf(logado.getVitorias()));
        lose.setText(String.valueOf(logado.getDerrotas()));
        userScore.setText("");
        dealerScore.setText("");
    }

    public void shuffle() {
        Random random = new Random();
        int a,b;

        for(int x = matrizCartas.length-1; x >= 0; x--) {
            int index = random.nextInt(x + 1);
            a = matrizCartas[index][0];
            b = matrizCartas[index][1];
            matrizCartas[index][0] = matrizCartas[x][0];
            matrizCartas[index][1] = matrizCartas[x][1];
            matrizCartas[x][0] = a;
            matrizCartas[x][1] = b;
        }
    }

    public void darCartasIniciais(){
        shuffle();

        if(matrizCartas[0][1] == 11 && matrizCartas[1][1] == 11) {
            matrizCartas[0][1] = 1;
            matrizCartas[1][1] = 1;
        }
        if(matrizCartas[0][1] == 11 && matrizCartas[1][1] == 10) {
            if(matrizCartas[1][0] != R.drawable.s10 || matrizCartas[1][0] != R.drawable.p10 ||
                    matrizCartas[1][0] != R.drawable.d10 || matrizCartas[1][0] != R.drawable.h10) {
                matrizCartas[0][1] = 11;
            }else{
                matrizCartas[0][1] = 1;
            }
        }
        if(matrizCartas[1][1] == 11 && matrizCartas[0][1] == 10) {
            if(matrizCartas[0][0] != R.drawable.s10 || matrizCartas[1][0] != R.drawable.p10 ||
                    matrizCartas[1][0] != R.drawable.d10 || matrizCartas[1][0] != R.drawable.h10) {
                matrizCartas[1][1] = 11;
            }else{
                matrizCartas[1][1] = 1;
            }
        }
        if(matrizCartas[2][1] == 11 && matrizCartas[3][1] == 11) {
            matrizCartas[2][1] = 1;
            matrizCartas[3][1] = 1;
        }
        if(matrizCartas[2][1] == 11 && matrizCartas[3][1] == 10){
            if(matrizCartas[3][0] != R.drawable.s10 && matrizCartas[1][0] != R.drawable.p10 &&
                    matrizCartas[1][0] != R.drawable.d10 && matrizCartas[1][0] != R.drawable.h10) {
                matrizCartas[2][1] = 11;
            }else{
                matrizCartas[2][1] = 1;
            }
        }
        if (matrizCartas[3][1] == 11 && matrizCartas[2][1] == 10){
            if(matrizCartas[2][0] != R.drawable.s10 && matrizCartas[1][0] != R.drawable.p10 &&
                    matrizCartas[1][0] != R.drawable.d10 && matrizCartas[1][0] != R.drawable.h10) {
                matrizCartas[3][1] = 11;
            }else{
                matrizCartas[3][1] = 1;
            }
        }

        dealer.getHandList().add(matrizCartas[0][0]);
        card1.setImageResource(matrizCartas[0][0]);
        dealer.getHandList().add(matrizCartas[1][0]);
        card2.setImageResource(R.drawable.red_back);
        logado.getHandList().add(matrizCartas[2][0]);
        userCard1.setImageResource(matrizCartas[2][0]);
        logado.getHandList().add(matrizCartas[3][0]);
        userCard2.setImageResource(matrizCartas[3][0]);
        userScore.setText(String.valueOf(totalUser()));

        checkScore();
    }

    public int totalDealer(){
        int total = 0;
        for(int j = 0; j < dealer.getHandList().size(); j++){
            for(int i = 0; i < matrizCartas.length; i++) {
                if (dealer.getHandList().get(j) == matrizCartas[i][0]) {
                    total += matrizCartas[i][1];
                }
            }
        }
        return total;
    }

    public int totalUser(){
        int total = 0;
        for(int j = 0; j < logado.getHandList().size(); j++){
            for(int i = 0; i < matrizCartas.length; i++) {
                if (logado.getHandList().get(j) == matrizCartas[i][0]) {
                    total += matrizCartas[i][1];
                }
            }
        }
        return total;
    }

    public void onClickComprar(View v) {
        shuffle();

        if (logado.getHandList().size() < 5) {
            logado.getHandList().add(matrizCartas[0][0]);
            if (logado.getHandList().size() == 3) {
                userCard3.setImageResource(matrizCartas[0][0]);
            }if (logado.getHandList().size() == 4) {
                userCard4.setImageResource(matrizCartas[0][0]);
            }if (logado.getHandList().size() == 5) {
                userCard5.setImageResource(matrizCartas[0][0]);
            }
            changeAcesUser();
            changeAcesDealer();
            checkScore();
        }else{
            btnComprar.setEnabled(false);
        }
    }

    public void mostrarCartaOculta(){
        for (int i = 0; i < matrizCartas.length; i++) {
            if(dealer.getHandList().get(1) == matrizCartas[i][0]){
                card2.setImageResource(matrizCartas[i][0]);
            }
        }
    }

    public void checkScore(){
        if(totalUser() == 21 && totalDealer() == 21){
            empatou();
        }else if(totalUser() == 21 && totalDealer() < 21){
            voceGanhou();
        }else if(totalUser() > 21){
            vocePerdeu();
        }
    }

    public void onClickReset(View v){
        esvaziarMaos();
        iniciarJogo();
    }

    public void onClickStand(View view) {
        shuffle();

        if (totalDealer() < 17) {
            dealer.getHandList().add(matrizCartas[0][0]);
            card3.setImageResource(matrizCartas[0][0]);
            if (totalDealer() < totalUser()) {
                dealer.getHandList().add(matrizCartas[1][0]);
                card4.setImageResource(matrizCartas[1][0]);
                if (totalDealer() < 17) {
                    dealer.getHandList().add(matrizCartas[2][0]);
                    card5.setImageResource(matrizCartas[2][0]);
                }
            }
            changeAcesUser();
            changeAcesDealer();
            checkScore();
        }
        if (totalDealer() > totalUser() && totalDealer() <= 21) {
            vocePerdeu();
        }else if(totalDealer() > 21){
            voceGanhou();
        }else if(totalUser() > totalDealer()){
            voceGanhou();
        }else if(totalDealer() == totalUser()){
            empatou();
        }
    }
    public void changeAcesUser(){
        if(totalUser() > 21){
            for(int i = 0; i < logado.getHandList().size(); i++){
                for(int j = 0; j < matrizCartas.length; j++){
                    if(logado.getHandList().get(i) == matrizCartas[j][0]){
                        if(matrizCartas[j][1] == 11){
                            matrizCartas[j][1] = 1;
                        }
                    }
                }
            }
        }
    }

    public void changeAcesDealer(){
        if(totalDealer() > 21){
            for(int i = 0; i < dealer.getHandList().size(); i++){
                for(int j = 0; j < matrizCartas.length; j++){
                    if(dealer.getHandList().get(i) == matrizCartas[j][0]){
                        if(matrizCartas[j][1] == 11){
                            matrizCartas[j][1] = 1;
                        }
                    }
                }
            }
        }
    }

    public void esvaziarMaos() {
        dealer.getHandList().clear();
        logado.getHandList().clear();
        card1.setImageDrawable(null);
        card2.setImageDrawable(null);
        card3.setImageDrawable(null);
        card4.setImageDrawable(null);
        card5.setImageDrawable(null);
        userCard1.setImageDrawable(null);
        userCard2.setImageDrawable(null);
        userCard3.setImageDrawable(null);
        userCard4.setImageDrawable(null);
        userCard5.setImageDrawable(null);
    }

    public void vocePerdeu(){
        mostrarCartaOculta();
        Toast.makeText(this, "Você perdeu!", Toast.LENGTH_SHORT).show();
        logado.setDerrotas(1);
        win.setText(String.valueOf(logado.getVitorias()));

        lose.setText(String.valueOf(logado.getDerrotas()));
        dealerScore.setText(String.valueOf(totalDealer()));
        userScore.setText(String.valueOf(totalUser()));
        btnStop.setVisibility(View.INVISIBLE);
        btnComprar.setVisibility(View.INVISIBLE);
    }

    public void voceGanhou(){
        mostrarCartaOculta();
        Toast.makeText(this, "Parabéns! Você ganhou!", Toast.LENGTH_SHORT).show();
        logado.setVitorias(1);
        win.setText(String.valueOf(logado.getVitorias()));
        lose.setText(String.valueOf(logado.getDerrotas()));
        dealerScore.setText(String.valueOf(totalDealer()));
        userScore.setText(String.valueOf(totalUser()));
        btnStop.setVisibility(View.INVISIBLE);
        btnComprar.setVisibility(View.INVISIBLE);
    }

    public void empatou(){
        mostrarCartaOculta();
        Toast.makeText(this, "EMPATOU!", Toast.LENGTH_SHORT).show();
        dealerScore.setText(String.valueOf(totalDealer()));
        userScore.setText(String.valueOf(totalUser()));
        btnStop.setVisibility(View.INVISIBLE);
        btnComprar.setVisibility(View.INVISIBLE);
    }

    public void onClickSair(MenuItem item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("logado", (Serializable) logado);
        Intent returnIntent = new Intent();
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}