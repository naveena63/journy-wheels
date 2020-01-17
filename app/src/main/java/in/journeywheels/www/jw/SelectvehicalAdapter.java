package in.journeywheels.www.jw;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.journeywheels.www.jw.pojoclass.Vehicleslist;
import in.journeywheels.www.jw.sharedpref.Session;
import in.journeywheels.www.jw.sharedpref.Sharedprefrence;

public class SelectvehicalAdapter extends RecyclerView.Adapter<SelectvehicalAdapter.MyViewHolder> {


    private Context context;
    private List<Vehicleslist> select=null;
    int lastPosition = -1;
    Sharedprefrence sharedprefrence;
    String sausid;
    Session session;

    public SelectvehicalAdapter(Context context, List<Vehicleslist> select) {
        this.context = context;
        this.select = select;
    }

    public SelectvehicalAdapter(Response.Listener<String> listener, ArrayList<Vehicleslist> select) {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.selectvehical_list, null);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        final Vehicleslist select_vehical_list=select.get(i);
        myViewHolder.bktittle.setText(select_vehical_list.getProductName());
        myViewHolder.bklimit.setText(select_vehical_list.getKmLimit());
        myViewHolder.bkbook.setText(select_vehical_list.getAvailability());
        myViewHolder.bkexecess.setText(select_vehical_list.getExcess());
        myViewHolder.bkprice.setText(select_vehical_list.getPrice());
        myViewHolder.select_veh_id.setText(select_vehical_list.getId());
  // myViewHolder.bkimg.setImageDrawable(context.getResources().getDrawable(Integer.parseInt(select_vehical_list.getProductImage())));
        Picasso.get().load(select.get(i).getProductImage()).into(myViewHolder.bkimg);
        Log.d("status",select_vehical_list.getProductImage() );
//
//        int zero=0;
//        if(select_vehical_list.getAvailability()==String.valueOf(zero)){
//            Log.d("status",String.valueOf(select_vehical_list.getAvailability()));
//            myViewHolder.status.setText("Un Availability");
//            myViewHolder.status.setBackgroundColor(R.color.red);
//            myViewHolder.status.setTextColor(R.color.black);
//            myViewHolder.bkbook.setEnabled(false);
//        }else {


        myViewHolder.bkbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String status=select_vehical_list.getAvailability();
                Log.d("status", status);
                if (status.equals("Sold Out")){
                    myViewHolder.bkbook.setClickable(false);
                }else {

                    session = new Session(context.getApplicationContext());
                    HashMap<String, String> user = session.getUserDetails();
                    String name = user.get(Session.KEY_NAME);
//                    String response = sharedprefrence.getLoginResponse(context.getApplicationContext());


                    if (name.equals("0")) {
                        Intent i = new Intent(context, LoginActivity.class);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);

                    } else {
//                        Gson gson = new Gson();
//                        final Loginpojo userObj = gson.fromJson(response, Loginpojo.class);
//                        sharedprefrence.getLoginResponse(context.getApplicationContext());
//
//                        try {
//                            sausid = userObj.getUser().getId().toString();
                        sausid=name;

                        Log.d("Nid", String.valueOf(sausid));

//                        } catch (Exception e) {
//
//
//                        }
                        Log.d("Nid", String.valueOf(sausid));

                      //  Toast.makeText(context, "you clicked" + select_vehical_list.getProductName() + sausid, Toast.LENGTH_SHORT).show();
                        final String vechileid = select_vehical_list.getId();
                        Log.d("vechileid", String.valueOf(vechileid));
                        Intent i = new Intent(context, CheckOutActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("myData", vechileid);
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.putExtras(bundle);
                        context.startActivity(i);
                    }
                }

            }
        });
//        }
//

//
//        if(i >lastPosition) {
//
//            Animation animation = AnimationUtils.loadAnimation(context,
//                    R.anim.up_from_bottom);
//            myViewHolder.itemView.startAnimation(animation);
//            lastPosition = i;
//        }


    }

    @Override
    public int getItemCount() {
        return select.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder  {
        TextView bktittle,bklimit,bkexecess,bkprice,select_veh_id,status;
        ImageView bkimg;
        Button bkbook;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bktittle = (TextView) itemView.findViewById(R.id.bkselect_tittle);
            bklimit = (TextView) itemView.findViewById(R.id.bkselect_date1);
            bkexecess = (TextView) itemView.findViewById(R.id.bkselect_date2);
            status = (TextView) itemView.findViewById(R.id.status);
            select_veh_id = (TextView) itemView.findViewById(R.id.select_vechile_id);
            bkprice = (TextView) itemView.findViewById(R.id.bkselect_price);
            bkimg= (ImageView) itemView.findViewById(R.id.bkselect_img);
            bkbook=(Button)itemView.findViewById(R.id.bk_select_book);
//            bkbook.setOnClickListener(this);

        }

//        @Override
//        public void onClick(View v) {
//            if (v.getId() == bkbook.getId()){
//                Toast.makeText(context,"you clicked"+ , Toast.LENGTH_SHORT).show();
//
////                Intent i = new Intent(context, LoginActivity.class);
////                context.startActivity(i);
//
//            }
//        }
    }


}