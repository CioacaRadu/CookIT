package com.example.radu.cookit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class StageActivity extends AppCompatActivity {

    static int CURRENT_STAGE ;
    Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage);

        recipe = (Recipe) getIntent().getSerializableExtra("Recipe");
       // TextView descriptionView = (TextView) findViewById(R.id.stage_description);
        TextView instructionView = (TextView) findViewById(R.id.stage_instruction);
        //descriptionView.setText(recipe.getDescription()); // stage in loc

        String instructionStr = recipe.getStages().get(CURRENT_STAGE++).getInstruction();
        instructionView.setText(instructionStr);

        PollyTalk polly = new PollyTalk(this,instructionStr);
        polly.PlaySpeech();




    }

    public void nextCook(View View) {

        if(PollyTalk.isRunning) return;
        if(CURRENT_STAGE < recipe.getStages().size() -1 ) {
            Log.w("Warn","Current stage:"+ Integer.toString(CURRENT_STAGE));
            Intent intent = new Intent(this, StageActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("Recipe", recipe);
            startActivity(intent);
        }
        else {

            CURRENT_STAGE = 0;
            Intent intent = new Intent(this, FinishActivity.class);
            intent.putExtra("Recipe", recipe);
            startActivity(intent);
        }

    }
}
