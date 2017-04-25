package ru.sberbank.user7.testactivity;

import android.app.Activity;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView test;
    EditText et;
    TextView tv;
    long currentTime;
    private static final String ARGTime = "argtime";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = (TextView) findViewById(R.id.test);

        if(savedInstanceState == null){
            currentTime = System.currentTimeMillis();
            test.setText(String.valueOf(currentTime));
        }else {
            test.setText(savedInstanceState.getString(ARGTime));
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ARGTime, currentTime );
    }
}
