package com.example.cawate14.cs4001project3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SCORE_REQUEST = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button start = (Button) findViewById(R.id.start);
        final EditText difficulty = (EditText) findViewById(R.id.difficulty);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("IMAGE_COUNT", Integer.decode(difficulty.getText().toString()));
                startActivityForResult(intent, SCORE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SCORE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            int score = data.getIntExtra("SCORE_RESULT", 0);
            TextView scoreview = (TextView) findViewById(R.id.prevscore);
            scoreview.setText(Integer.toString(score));
        }
    }

}
