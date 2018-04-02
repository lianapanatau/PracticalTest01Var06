package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var06;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {
    private Integer number;

    private Button generateButton = null;
    private Button checkButton = null;
    private Button backButton = null;
    private TextView guessText = null;
    private TextView scoreTextt = null;
    private Integer correctGuess = 0;
    private MessageBroadcastReceiver messageBroadcastReceiver = null;
    private IntentFilter intentFilter = null;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var02_play);

        generateButton = (Button)findViewById(R.id.generateButton);
        checkButton = (Button)findViewById(R.id.checkButton);
        backButton = (Button)findViewById(R.id.backButton);
        guessText = (TextView) findViewById(R.id.guessText);
        scoreTextt = (TextView) findViewById(R.id.scoreText);

        messageBroadcastReceiver = new MessageBroadcastReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION1);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("number")) {
               guessText.setText(savedInstanceState.getString("number"));
            }
            if (savedInstanceState.containsKey("score")) {
                scoreTextt.setText(savedInstanceState.getString("score"));
            }
        }

        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null &&intent.getExtras().containsKey("number")){
            number = intent.getIntExtra("number", 0);
        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessText.setText(String.valueOf(random.nextInt(10) + 1));

//                try {
//                    Thread.sleep(7000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                guessText.setText("0");
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessText.getText().toString().equals("")) {
                    Toast
                            .makeText(getApplicationContext(),
                                    "First click on generate",
                                    Toast.LENGTH_LONG)
                            .show();
                }
                if (number.equals(Integer.valueOf(guessText.getText().toString()))) {
                    scoreTextt.setText(String.valueOf(correctGuess + 1));
                } else {
                    scoreTextt.setText(String.valueOf(correctGuess));
                }
            }
        });

        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var06Service.class);
        getApplicationContext().startService(intent2);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("number", guessText.getText().toString().equals("") ? "0": guessText.getText().toString());
        outState.putString("score", scoreTextt.getText().toString().equals("") ? "0":scoreTextt.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey("number")) {
            guessText.setText(savedInstanceState.getString("number"));
        } else if (savedInstanceState.containsKey("score")) {
            scoreTextt.setText(savedInstanceState.getString("score"));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, PracticalTest01Var06Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra("message");
        if (message != null) {
            guessText.setText(guessText.getText().toString());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(getClass().getSimpleName(), "onResume()");
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
    }
}
