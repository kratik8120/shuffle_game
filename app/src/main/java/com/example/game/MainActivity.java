package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener, View.OnClickListener {
        ImageView card1ImageView, card2ImageView, card3ImageView;
        List<String> cards = new ArrayList<>();
        Button button;
        boolean card1Flipped = true, card2Flipped = true, card3Flipped = true;
        Animation toMiddle, fromMiddle;
        int selectedCardIndex = -1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            card1ImageView = findViewById(R.id.one);
            card2ImageView = findViewById(R.id.two);
            card3ImageView = findViewById(R.id.three);
            button = findViewById(R.id.button);

            toMiddle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tomiddle);
            fromMiddle = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.frommiddle);

            toMiddle.setAnimationListener(this);
            fromMiddle.setAnimationListener(this);

            card1ImageView.setOnClickListener(this);
            card2ImageView.setOnClickListener(this);
            card3ImageView.setOnClickListener(this);

            button.setOnClickListener(this);

            setup();
        }
        private void setup() {
            cards.clear();
            cards.add("K");
            cards.add("K");
            cards.add("joker");
            Collections.shuffle(cards);
        }
        @Override
        public void onAnimationStart(Animation animation) {}
        @Override
        public void onAnimationEnd(Animation animation) {

            ImageView selectedCard;
            boolean isCardFlipped = false;

            switch (selectedCardIndex) {
                case 0:
                    selectedCard = card1ImageView;
                    isCardFlipped = card1Flipped;
                    break;
                case 1:
                    selectedCard = card2ImageView;
                    isCardFlipped = card2Flipped;
                    break;
                case 2:
                    selectedCard = card3ImageView;
                    isCardFlipped = card3Flipped;
                    break;
                default:
                    return;
            }
            if (animation == toMiddle) {
                if (isCardFlipped) {
                    showCard(selectedCard, selectedCardIndex);
                } else {
                    selectedCard.setImageResource(R.drawable.card1);
                }
                selectedCard.clearAnimation();
                selectedCard.setAnimation(fromMiddle);
                selectedCard.startAnimation(fromMiddle);
            } else
            {
                switch (selectedCardIndex) {
                    case 0:
                        card1Flipped = !card1Flipped;
                        break;
                    case 1:
                        card2Flipped = !card2Flipped;
                        break;
                    case 2:
                        card3Flipped = !card3Flipped;
                        break;
                }
            }
        }
        private void showCard(ImageView imageView, int index) {
            String card = cards.get(index);
            if (card.equals("K")) {
                imageView.setImageResource(R.drawable.card3);
            } else if (card.equals("joker")) {
                imageView.setImageResource(R.drawable.card2);
            }
        }
        @Override
        public void onAnimationRepeat(Animation animation) {}
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.button) {
                newGame();
            } else {
                v.clearAnimation();
                v.setAnimation(toMiddle);
                v.startAnimation(toMiddle);

                if (v.getId() == R.id.one)
                    selectedCardIndex = 0;
            else if (v.getId() == R.id.two)
                    selectedCardIndex = 1;
            else if (v.getId() == R.id.three)
                    selectedCardIndex = 2;
            }
        }
        private void newGame() {
            Collections.shuffle(cards);
            Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim1);
            Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim2);
            card1ImageView.startAnimation(anim1);
            card2ImageView.startAnimation(anim2);
            card3ImageView.startAnimation(anim2);
            card1ImageView.setImageResource(R.drawable.card1);
            card2ImageView.setImageResource(R.drawable.card1);
            card3ImageView.setImageResource(R.drawable.card1);
            card1Flipped = card2Flipped = card3Flipped = true;
        }
    }

