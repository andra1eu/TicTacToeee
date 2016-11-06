package com.example.andra.tictactoeee;

import android.media.Image;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //memoreaza tura cui ii
    int player = 0;

    //sirul de pahare goale
    int[] places = {3, 3, 3, 3, 3, 3, 3, 3, 3};


    //functie declansata la apasarea unei imagini
    public void dropIn(View view) {
        final ImageView counter = (ImageView) view;
        //transforma tagul imagini in int
        int tag = Integer.parseInt(counter.getTag().toString());

        //verifica daca paharaul este gol.
        if (places[tag] == 3) {

            //duce imaginea goala in afara ecranului
            counter.setTranslationY(-1000f);

            //umple paharul gol.
            places[tag] = player;

            //verifica tura cui ii si umplu cu imaginea corespunzatoare
            if (player == 0) {
                counter.setImageResource(R.drawable.kitty);
                //schimb tura
                player = 1;
            } else {
                counter.setImageResource(R.drawable.mouse);
                //schimb tura
                player = 0;
            }

            //spune cum sa coboare imaginea
            counter.animate().translationYBy(1000f).rotation(360f).setDuration(800);


        }
        if (verificareCastigator()) {
            if (places[tag] == 0) {
                AndraToast.showToast(this, "Kity, you won!", R.drawable.kitty);
            } else {
                AndraToast.showToast(this, "Mouse, you won!", R.drawable.mouse);
            }
            reinitializare(counter);
        } else if (isDraw()) {
            AndraToast.showToast(this, "It's a draw! Start again", R.drawable.fish);
            reinitializare(counter);
        }

    }

    private void reinitializare(ImageView counter) {
        places = new int[]{3, 3, 3, 3, 3, 3, 3, 3, 3};
        GridLayout grid = (GridLayout) counter.getParent();

        for (int i = 0; i < grid.getChildCount(); i++) {
            ((ImageView) grid.getChildAt(i)).setImageDrawable(null);
        }
    }


    private boolean verificareCastigator() {
        int[][] pozitii = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6,}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int[] perechi : pozitii) {
            if (places[perechi[0]] == places[perechi[1]]
                    && places[perechi[0]] == places[perechi[2]]
                    && places[perechi[0]] != 3) {
                return true;
            }
        }

        return false;
    }

    public boolean isDraw() {
        // un int care numara fiecare pozitie din places
        int pozitiiOcupate = 0;
        // pt fiecare pozitie ocupata, int pozOcupate trece mai
        // departe cu numaratoarea, pana ce toate cele 9 pozitii primesc o valoare;
        for (int bucata : places) {
            if (bucata != 3) {
                pozitiiOcupate ++;
            }
        }

        if (pozitiiOcupate == 9) {
            return true;
        }

        return false;
    }
}
