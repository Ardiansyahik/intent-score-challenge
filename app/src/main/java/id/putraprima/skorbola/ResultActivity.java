package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import javax.xml.transform.Result;

public class ResultActivity extends AppCompatActivity {
    private TextView result;
    private TextView winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = findViewById(R.id.textView2);
        winner  = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        int homeResult = extras.getInt("homeTextScore");
        int awayResult = extras.getInt("awayTextScore");
        String homename = extras.getString("homename");
        String awayname = extras.getString("awayname");

        
    }
}
