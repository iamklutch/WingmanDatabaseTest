package yukidev.com.wingmandatabasetest.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import yukidev.com.wingmandatabasetest.Airman;
import yukidev.com.wingmandatabasetest.R;
import yukidev.com.wingmandatabasetest.db.DatabaseMethods;


// created by me

public class MainActivity extends ActionBarActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    protected Context mContext;

    @InjectView(R.id.nameEditText)EditText mAirmanName;
    @InjectView(R.id.ageEditText)EditText mAirmanAge;
    @InjectView(R.id.rankEditText)EditText mAirmanRank;
    @InjectView(R.id.goButton)Button mGoButton;
    @InjectView(R.id.setButton)Button mSetButton;
    private String mEncryptedData;
    private int mId;
    private Airman mAirman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cipher = getString(R.string.encryption_password);
                String name = mAirmanName.getText().toString();
                int age = Integer.parseInt(mAirmanAge.getText().toString());
                String rank = mAirmanRank.getText().toString();

                String encryptedName = encryptThis(cipher, name);
//                String encryptedAge = encryptThis(cipher, age);
                String encryptedRank = encryptThis(cipher, rank);

                mAirman = new Airman(0,"",0,"");
                mAirman.setName(encryptedName);
                mAirman.setAge(age);
                mAirman.setRank(encryptedRank);

                DatabaseMethods databaseMethods = new DatabaseMethods(MainActivity.this);
                databaseMethods.create(mAirman);
            }
        });

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAirmenActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String encryptThis(String pass, String data) {
        try {
            Crypto crypto = new Crypto(pass);
            mEncryptedData = crypto.encrypt(data);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return mEncryptedData;
    }

}
