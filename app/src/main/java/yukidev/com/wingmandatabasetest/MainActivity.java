package yukidev.com.wingmandatabasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    protected Context mContext;

    @InjectView(R.id.nameEditText)EditText mAirmanName;
    @InjectView(R.id.ageEditText)EditText mAirmanAge;
    @InjectView(R.id.rankEditText)EditText mAirmanRank;
    @InjectView(R.id.goButton)Button mGoButton;
    @InjectView(R.id.setButton)Button mSetButton;
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

                String name = mAirmanName.getText().toString();
                int age = Integer.parseInt(mAirmanAge.getText().toString());
                String rank = mAirmanRank.getText().toString();

                mAirman = new Airman("",0,"");
                mAirman.setName(name);
                mAirman.setAge(age);
                mAirman.setRank(rank);

                DatabaseMethods databaseMethods = new DatabaseMethods(MainActivity.this);
                databaseMethods.create(mAirman);
            }
        });

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                if(mAirman.getId() != -1) {
//                    databaseMethods.update(mAirman);
//                }else {
//                    databaseMethods.create(mAirman);
//                }
            }
        });


    }

//    private void refreshMemes() {
//        DatabaseMethods dataSource = new DatabaseMethods(this);
//        ArrayList<Airman> memes = dataSource.readAirman();
//        // setListAdapter(new MemeItemListAdapter(getActivity(), memes));
//    }

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
}
