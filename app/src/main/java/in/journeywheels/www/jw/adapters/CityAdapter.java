package in.journeywheels.www.jw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import in.journeywheels.www.jw.FromActivity;
import in.journeywheels.www.jw.R;
import in.journeywheels.www.jw.SetLocationActivity;

public class CityAdapter extends BaseAdapter {

    public String[] idss;
    public Integer[] thumbIds ;
    public String[] city ;
    Context context;
    private static LayoutInflater inflater=null;
    public CityAdapter(FromActivity fromActivity, Integer[] mThumbIds, String[] mids, String[] mcity) {
        // TODO Auto-generated constructor stub
        city=mcity;
        idss=mids;
        thumbIds=mThumbIds;
        context=fromActivity;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        return idss.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }


    public class Holder
    {
        TextView citys,cat_id;
        ImageView icon;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.selectlocat_gridview, null);
        holder.icon = (ImageView) rowView.findViewById(R.id.gridimg);
        holder.citys = (TextView) rowView.findViewById(R.id.gridtxt);
        holder.cat_id= (TextView) rowView.findViewById(R.id.catog_id);
        holder.icon.setImageResource(thumbIds[position]);
        holder.citys.setText(idss[position]);
        holder.cat_id.setText(city[position]);
//        rowView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                Toast.makeText(context, "You Clicked "+idss[position], Toast.LENGTH_LONG).show();
//            }
//        });

        return rowView;
    }

}
