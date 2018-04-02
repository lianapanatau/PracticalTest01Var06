package practicaltest01var02.eim.systems.cs.pub.ro.practicaltest01var06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var03ChooseNumber extends AppCompatActivity {

    private EditText numberText = null;
    private Button playButton = null;
    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var03_choose_number);

        numberText = (EditText)findViewById(R.id.numberText);
        playButton = (Button) findViewById(R.id.playButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberText.getText().toString().equals("")) {
                    Toast
                            .makeText(getApplicationContext(),
                                    "Number is empty",
                                    Toast.LENGTH_LONG)
                            .show();
                } else {
                    Intent intent1 = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                    intent1.putExtra("number", Integer.valueOf(numberText.getText().toString()));
                    startActivityForResult(intent1, SECONDARY_ACTIVITY_REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
