package in.journeywheels.www.jw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.journeywheels.www.jw.adapters.CityAdapter;

public class SetLocationActivity extends AppCompatActivity {
    ImageView close;
    TextView done,setlocation;
    CityAdapter cityAdapter;
    SelectcityAdapters selectcityAdapters;
    public Integer[] mThumbIds = {
            R.drawable.vishakapatnam, R.drawable.vijayawada,
            R.drawable.rajamandry, R.drawable.tirupathi,
            R.drawable.nellore,
    };
    public String[] mcity = {
            "vishakapatnam", "vijayawada",
            "rajamandry","tirupathi", "nellore",
    };
    public String[] mids = {
            "1","2","3","4","5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_location);
        close=(ImageView)findViewById(R.id.close_setlocation);
        setlocation=(TextView)findViewById(R.id.set_location);
//        CityAdapter cityAdapter = new CityAdapter(SetLocationActivity.this, mThumbIds, mcity,mids);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(selectcityAdapters);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cityname=mcity[+position];
                String cityid=mids[+position];
                Intent i = new Intent(SetLocationActivity.this, FromActivity.class);
                i.putExtra("cityname", cityname);
                i.putExtra("cityid", cityid);
                startActivity(i);


                Toast.makeText(SetLocationActivity.this, "GridView Item: " + cityname+"  "+mids[+position],Toast.LENGTH_LONG).show();
            }
        });

//        String location= getIntent().getStringExtra("set_location");
//        setlocation.setText(location);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SetLocationActivity.this, FromActivity.class);
                startActivity(i);
                finish();

            }
        });



    }
}
