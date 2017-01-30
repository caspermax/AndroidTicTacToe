package edu.hhs.cemmetmabson.androidtictactoe;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TicTacToe extends AppCompatActivity implements View.OnClickListener {


    private TicTacToeGame mGame;
    // Buttons making up the board
    private Button mBoardButtons[];
    // Various text displayed
    private TextView mInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe);
        mGame = new TicTacToeGame();
        mBoardButtons = new Button[9];

        mBoardButtons[0] = (Button)findViewById(R.id.one);
        mBoardButtons[1] = (Button)findViewById(R.id.two);
        mBoardButtons[2] = (Button)findViewById(R.id.three);

        mBoardButtons[3] = (Button)findViewById(R.id.four);
        mBoardButtons[4] = (Button)findViewById(R.id.five);
        mBoardButtons[5] = (Button)findViewById(R.id.six);

        mBoardButtons[6] = (Button)findViewById(R.id.seven);
        mBoardButtons[7] = (Button)findViewById(R.id.eight);
        mBoardButtons[8] = (Button)findViewById(R.id.nine);

        mInfoTextView = (TextView)findViewById(R.id.information);
        startNewGame();
    }

    private void startNewGame(){
        mGame.clearBoard();
        for(int i=0; i<9; i++){
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(this);
        }
        mInfoTextView.setText("You go first");
    }

    @Override
    public void onClick(View view) {
        for(int i=0; i<9; i++){
            if(mBoardButtons[i].getId() == view.getId()){
                setMove(TicTacToeGame.HUMAN_PLAYER, i);
                if(mGame.checkForWinner() == 0){
                    setMove(TicTacToeGame.COMPUTER_PLAYER, mGame.getComputerMove());
                    if(mGame.checkForWinner() == 3){
                        mInfoTextView.setText("You lose, you fucking idiot!");
                    }
                }
                else if(mGame.checkForWinner() == 2){
                    mInfoTextView.setText("You win! Holy fucking shit!");
                }
                else{
                    mInfoTextView.setText("You tied, shitter!");

                }
            }
        }
    }

    private void setMove(char player, int location) {

        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        mBoardButtons[location].setText(String.valueOf(player));
        if (player == TicTacToeGame.HUMAN_PLAYER)
            mBoardButtons[location].setTextColor(Color.rgb(0, 200, 0));
        else
            mBoardButtons[location].setTextColor(Color.rgb(200, 0, 0));
    }
}
