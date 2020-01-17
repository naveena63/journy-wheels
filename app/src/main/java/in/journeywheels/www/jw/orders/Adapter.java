package in.journeywheels.www.jw.orders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.journeywheels.www.jw.R;

public class Adapter extends RecyclerView.Adapter<Adapter.VH> {
    Context context;
    List<Model> modelList;

    public Adapter(Context context, List<Model> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @Override
    public Adapter.VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.previousbookings_layout,
                viewGroup,
                false);

        return new VH(view);
    }

    @Override
    public void onBindViewHolder(final Adapter.VH vh, int i) {
        vh.vehicalTitle.setText("Vehicle Name :" + modelList.get(i).getVehicleTitle());
        vh.pickLocation.setText("pickLocation :" + modelList.get(i).getPickLocation());
        vh.pickDatetime.setText("pickDatetime :" + modelList.get(i).getPickDateTime());
        vh.dropLocation.setText("dropLocation :" + modelList.get(i).getDropLocation());
        vh.dropDateTime.setText("dropDateTime :" + modelList.get(i).getDropDateTime());
        vh.orderDate.setText("orderDate    :" + modelList.get(i).getOrderDate());
        vh.amount.setText("amount       :" + modelList.get(i).getAmount());

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class VH extends RecyclerView.ViewHolder {
        TextView vehicalTitle,pickLocation,pickDatetime,dropLocation,dropDateTime,location,orderDate,amount;

        public VH(View itemView) {
            super(itemView);
            vehicalTitle = itemView.findViewById(R.id.vehicalTitle);
            pickLocation = itemView.findViewById(R.id.vehicle_pickUpLoctaion);
            pickDatetime = itemView.findViewById(R.id.vehicle_pickDateTime);
            dropLocation = itemView.findViewById(R.id.vehicle_droppingLocation);
            dropDateTime = itemView.findViewById(R.id.vehicle_DropDatetime);
            orderDate = itemView.findViewById(R.id.vehicleOrderDate);
            amount = itemView.findViewById(R.id.vehicle_orderAmount);

        }
    }
}