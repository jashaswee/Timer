package xyz.timer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static xyz.timer.R.id.start;
import static xyz.timer.R.id.stop;
import static xyz.timer.R.id.timer;

public class MainActivity extends Activity implements View.OnClickListener {

    public int seconds = 60;
    final public String TAG = "FO";
    public int minutes = 10;
   // public int flag = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button) findViewById(start);
      //  Button b1 = (Button)findViewById(stop);



        b.setOnClickListener(this);
       // b1.setOnClickListener(this);


    }

    public void trigger() {
       // while (flag != 0)
        {
            //Declare the timer
            Timer t = new Timer();
            //Set the schedule function and rate
            t.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            TextView tv = (TextView) findViewById(timer);
                            tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                            seconds -= 1;

                            if (seconds == 0) {
                                tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));

                                seconds = 60;
                                minutes = minutes - 1;

                            }


                        }

                    });
                }

            }, 0, 1000);
        }
    }


    @Override
    public void onClick(View view) {
        TextView secs = (TextView) findViewById(R.id.secs);
        TextView mins = (TextView) findViewById(R.id.mins);
        int s = Integer.parseInt(secs.getText().toString());
        int m = Integer.parseInt(mins.getText().toString());
        try {
            switch (view.getId()) {
                case (start): {


                    //flag = 1;

                    seconds = s;
                    minutes = m;

                    trigger();
                }
                case (stop): {
                    //flag =0;
                }


            }

        }
        catch(NumberFormatException e ){
            Log.e(TAG, "NumberFormatException occured");
        }
    }
}

