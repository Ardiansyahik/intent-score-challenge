package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
    private EditText score;
    private EditText minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
    }

    public void Accept(View view) {
        score = findViewById(R.id.editText);
        minute = findViewById(R.id.editText1);
        String stringToPassBack1 = score.getText().toString();
        String stringToPassBack2 = minute.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("score", stringToPassBack1);
        intent.putExtra("minute", stringToPassBack2);
        setResult(RESULT_OK);
        finish();
    }
}
