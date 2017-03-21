package com.example.radu.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        Recipe recipe = (Recipe) getIntent().getSerializableExtra("Recipe");
        TextView finishView = (TextView) findViewById(R.id.finish_message);

        String instructionStr = recipe.getStages().get(recipe.getStages().size() -1).getInstruction();
        finishView.setText(instructionStr);

        PollyTalk polly = new PollyTalk(this,instructionStr);
        polly.PlaySpeech();


    }

    public void cookAgain(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
