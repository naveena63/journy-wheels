package in.journeywheels.www.jw;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SliderAdapter extends PagerAdapter {
    Context context;
    int images[];
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {

        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == ((LinearLayout) o);
    }


    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.slide_image_home, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.offer);
        imageView.setImageResource(images[position]);

        container.addView(itemView);

        //listening to image click

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

}
