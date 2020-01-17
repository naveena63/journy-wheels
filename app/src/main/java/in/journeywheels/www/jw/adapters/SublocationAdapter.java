package in.journeywheels.www.jw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.journeywheels.www.jw.FromActivity;
import in.journeywheels.www.jw.R;
import in.journeywheels.www.jw.pojoclass.Location;
import in.journeywheels.www.jw.pojoclass.Sublocation;

public class SublocationAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Sublocation> dataModelArrayList;

    public SublocationAdapter(Context context, ArrayList<Sublocation> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    public SublocationAdapter(List<Sublocation> dataitem, Context applicationContext) {
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sub_location_list, null, true);

            holder. subLocationName= (TextView) convertView.findViewById(R.id.drop);
            holder.id= (TextView) convertView.findViewById(R.id.dropid);


            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        holder.subLocationName.setText(dataModelArrayList.get(position).getSubLocationName());
        holder.id.setText(dataModelArrayList.get(position).getId());




        return convertView;
    }

    private class ViewHolder {

        protected TextView subLocationName, id;

    }



}
