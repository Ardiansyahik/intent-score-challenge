package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MatchActivity extends AppCompatActivity {
    private TextView homeText;
    private TextView awayText;
    private TextView homeTextscore;
    private TextView awayTextscore;

    String homename;
    String awayname;

    private ImageView homeImage;
    private ImageView awayImage;

    private int homescore;
    private int awayscore;
    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeText = findViewById(R.id.score_home);
        awayText = findViewById(R.id.score_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            homename = extras.getString(MainActivity.HOME_KEY);
            awayname = extras.getString(MainActivity.AWAY_KEY);
            String homeURL = extras.getString(MainActivity.HOMEIMG_KEY);
            String awayURL = extras.getString(MainActivity.AWAYIMG_KEY);
            homeText.setText(homename);
            awayText.setText(awayname);
            try {
                Bitmap homebitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(homeURL));
                Bitmap awaybitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(awayURL));
                homeImage.setImageBitmap(homebitmap);
                awayImage.setImageBitmap(awaybitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void handlehome(View view) {
        //homescore++;
        //homeText.setText(String.valueOf(homescore));
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, HOME_REQUEST_CODE);
    }

    public void handleaway(View view) {
        //awayscore++;
        //awayText.setText(String.valueOf(awayscore));
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, AWAY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == HOME_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String returnEditScore = data.getStringExtra("score");
                String returnEditMinute = data.getStringExtra("minute");
                TextView playerGoal = findViewById(R.id.textView4);
                playerGoal.setText(returnEditScore + "-" + returnEditMinute);
            }
            homescore++;
            homeTextscore.setText(String.valueOf(homescore));
        }
        if (requestCode == AWAY_REQUEST_CODE){
            if (resultCode == RESULT_OK){
                String returnEditScore = data.getStringExtra("score");
                String returnEditMinute = data.getStringExtra("minute");
                TextView playerGoal = findViewById(R.id.textView5);
                playerGoal.setText(returnEditScore + "-" + returnEditMinute);
            }
            awayscore++;
            awayTextscore.setText(String.valueOf(awayscore));
        }

    }

    public void next(View view) {
        Intent intent = new Intent(this,ResultActivity.class);
        intent.putExtra("homename", homename);
        intent.putExtra("awayname", awayname);
        intent.putExtra("homeTextScore",homescore);
        intent.putExtra("awayTextScore",awayscore);
        startActivity(intent);
    }

}
