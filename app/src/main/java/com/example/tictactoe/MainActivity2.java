package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    int[][] boardstatus = new int[3][3];
    boolean Player = true;
    int turncount = 0;

    Button[][] board;
    TextView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button b1 = findViewById(R.id.button1);
        Button b2 = findViewById(R.id.button2);
        Button b3 = findViewById(R.id.button3);
        Button b4 = findViewById(R.id.button4);
        Button b5 = findViewById(R.id.button5);
        Button b6 = findViewById(R.id.button6);
        Button b7 = findViewById(R.id.button7);
        Button b8 = findViewById(R.id.button8);
        Button b9 = findViewById(R.id.button9);
        display = findViewById(R.id.display);
        Button reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateboardstatus();
                Player = true;
                turncount = 0;
                updatetext("Player 1 Turn");
            }
        });

        board = new Button[][]{{b1, b2, b3}, {b4, b5, b6}, {b7, b8, b9}};

        updateboardstatus();

        for(int i = 0; i<board.length; i++){
            for(int j = 0; j<board.length; j++ ){
                board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getId() == R.id.button1) {
                                updatevalue(0, 0,Player);
                            }
                        else if(v.getId() == R.id.button2){
                                updatevalue(0, 1, Player);
                            }
                        else if(v.getId() == R.id.button3) {
                                updatevalue(0, 2, Player);
                            }
                        else if(v.getId() == R.id.button4){
                                updatevalue(1, 0, Player);
                            }
                        else if(v.getId() == R.id.button5) {
                                updatevalue(1, 1, Player);
                            }
                        else if (v.getId() == R.id.button6) {
                                updatevalue(1, 2, Player);
                            }
                        else if (v.getId() == R.id.button7) {
                                updatevalue(2, 0, Player);
                            }
                        else if (v.getId() == R.id.button8) {
                                updatevalue(2, 1, Player);
                            }
                        else if (v.getId() == R.id.button9) {
                                updatevalue(2, 2,Player);
                            }
                        turncount++;
                        Player = !Player;

                        if (Player) {
                            updatetext("Player 1 Turn");
                        } else {
                            updatetext("Player 2 Turn");
                        }
                        if (turncount == 9) {
                            updatetext("Game Draw");
                        }
                        checkwinner();
                        }
                });
            }
        }

    }

    private void updateboardstatus() {
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                boardstatus[i][j] = -1;
            }
        }

        for (Button[] buttons : board) {
            for (int j = 0; j < board.length; j++) {

                buttons[j].setEnabled(true);
                buttons[j].setText("");

            }
        }
    }

    private void checkwinner() {

        //horizontal rows
        for (int i = 0; i<3; i++) {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2]) {
                if (boardstatus[i][0] == 1) {
                    updatetext("Player 1 winner");
                    break;
                } else if (boardstatus[i][0] == 0) {
                    updatetext("Player 2 winner");
                    break;
                }
            }
        }

        //vertical columns
        for (int i = 0 ; i<3; i++) {
            if (boardstatus[0][i] == boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i]) {
                if (boardstatus[0][i] == 1) {
                    updatetext("Player 1 winner");
                    break;
                } else if (boardstatus[0][i] == 0) {
                    updatetext("Player 2 winner");
                    break;
                }
            }
        }

        //first diagonal
        if (boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2]) {
            if (boardstatus[0][0] == 1) {
                updatetext("Player 1 winner");
            } else if (boardstatus[0][0] == 0) {
                updatetext("Player 2 winner");
            }
        }

        //Second diagonal
        if (boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0]) {
            if (boardstatus[0][2] == 1) {
                updatetext("Player 1 winner");
            } else if (boardstatus[0][2] == 0) {
                updatetext("Player 2 winner");
            }
        }
    }

    private void updatetext(String s) {

        display.setText(s);
        if (s.contains("winner")) {
            disablebutton();
        }
    }
    private void disablebutton() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {

                board[i][j].setEnabled(false);

            }
        }
    }

    private void updatevalue(int row, int col, boolean player) {

        String text = "";
        int value;
        if (player){
            text = "X";
        } else {
            text = "0";
        }
        if (player){
            value = 1;
        } else {
            value = 0;
        }

        board[row][col].setEnabled(false);
        board[row][col].setText(text);

        boardstatus[row][col] = value;
    }
}