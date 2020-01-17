package in.journeywheels.www.jw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import in.journeywheels.www.jw.pojoclass.Location;

public class SelectcityAdapters extends BaseAdapter  {

    private Context mContext;
    LayoutInflater inflter;
    ArrayList<Location> loct;
    public String[] mcity;
    public Integer[] mThumbIds ;
    public String[] ids ;

    public SelectcityAdapters(SetLocationActivity  fromActivity , Integer[] mThumbIds, String[] ids, String[] mcity ){
        mContext = fromActivity;
        this.mThumbIds = mThumbIds ;
        this.mcity =mcity;
        this.ids = ids;
    }



    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class Holder
    {
        TextView city,cat_id;
        ImageView icon;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View convert = null;
        Holder holder=new Holder();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


//        if (convertView == null) {

            convert = inflater.inflate(R.layout.selectlocat_gridview, null);
//            Location locat= (Location) getItem(position);
            holder.icon = (ImageView) convertView.findViewById(R.id.gridimg);
            holder.city = (TextView) convertView.findViewById(R.id.gridtxt);
            holder.cat_id= (TextView) convertView.findViewById(R.id.catog_id);
            holder.icon.setImageResource(position);
            holder.city.setText(position);
            holder.cat_id.setText(position);

//            Picasso.with(mContext)
//                    .load(locat.getLocationImage())
//                    .into(icon);

//        }
//        else {
//            convert = (View) convertView;
//        }

//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(mThumbIds[position]);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return convert;
    }



}
