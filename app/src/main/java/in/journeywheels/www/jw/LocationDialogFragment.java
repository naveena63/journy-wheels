package in.journeywheels.www.jw;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDialogFragment extends Fragment {
    ImageView close;
    TextView done,setlocation;
    SelectcityAdapters adapters;

    public LocationDialogFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_location_dialog, container, false);

        close=(ImageView)view.findViewById(R.id.close_setlocation);
        setlocation=(TextView)view.findViewById(R.id.set_location);

        GridView gridView = (GridView)view.findViewById(R.id.citygridView);

        // Instance of ImageAdapter Class
        gridView.setAdapter(adapters);
//        String location= getIntent().getStringExtra("set_location");
//        setlocation.setText(location);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), FromActivity.class);
                startActivity(i);


            }
        });


        return view;
    }

}
