package in.journeywheels.www.jw.sqllite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.journeywheels.www.jw.R;

public class SqlLite extends AppCompatActivity implements View.OnClickListener {
    private Button addTodoBtn;
    private EditText subjectEditText;

    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);
        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_record:

                final String name = subjectEditText.getText().toString();
                dbManager.insert(name);
                break;
        }
    }
}
