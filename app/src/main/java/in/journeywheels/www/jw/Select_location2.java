package in.journeywheels.www.jw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Select_location2 extends AppCompatActivity {
    ImageView close;
    TextView done,setlocation,selectcity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location2);
        close=(ImageView)findViewById(R.id.close_setlocation2);
        setlocation=(TextView)findViewById(R.id.set_location2);
        selectcity=(TextView)findViewById(R.id.select_city);
        String cityloc= getIntent().getStringExtra("City");
        selectcity.setText(cityloc);
        String location= getIntent().getStringExtra("set_location");
        setlocation.setText(location);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Select_location2.this, FromActivity.class);
                startActivity(i);
                finish();

            }
        });

    }
}
