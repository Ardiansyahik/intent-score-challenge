package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String HOME_KEY = "home";
    public static final String AWAY_KEY = "away";
    public static final String HOMEIMG_KEY = "homeURL";
    public static final String AWAYIMG_KEY = "awayURL";

    private EditText homeInput;
    private EditText awayInput;

    private TextView Hometeam;
    private TextView Awayteam;

    private ImageView homeImage;
    private ImageView awayImage;

    private String homeURL;
    private String awayURL;

    private static final int HOME_REQUEST_CODE = 1;
    private static final int AWAY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
        Hometeam = findViewById(R.id.home_team);
        Awayteam = findViewById(R.id.away_team);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);



    }

    public void enext(View view) {

        Intent intent = new Intent(this,MatchActivity.class);
        String home = Hometeam.getText().toString();
        String away = Awayteam.getText().toString();
        intent.putExtra(HOME_KEY, home);
        intent.putExtra(AWAY_KEY, away);
        intent.putExtra(HOMEIMG_KEY, homeURL);
        intent.putExtra(AWAYIMG_KEY, awayURL);
        startActivity(intent);
    }

    public void teamhome(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, HOME_REQUEST_CODE);
    }

    public void teamaway(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, AWAY_REQUEST_CODE);
    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }

        if (requestCode == HOME_REQUEST_CODE) {
            if (data != null) {
                try {
                    homeURL = data.getDataString();
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (requestCode == AWAY_REQUEST_CODE) {
            if (data != null) {
                try {
                    awayURL = data.getDataString();
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
