package com.example.mdp_app_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPageAdapter extends PagerAdapter {
    //creating arrays for images and description
    Context context;
    int images[] = {

            R.drawable.image1_1,
            R.drawable.image2_2,
            R.drawable.image3_3,
            R.drawable.image4_4,

    };

    int heaidngs[] = {

            R.string.heading_one,
            R.string.heading_two,
            R.string.heading_three,
            R.string.heading_fourth,

    };

    int description[] = {

            R.string.desc_one,
            R.string.desc_two,
            R.string.desc_three,
            R.string.desc_fourth,

    };

    public ViewPageAdapter(Context context){

        this.context = context;

    }

    @Override
    public int getCount() {
        return heaidngs.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    //position (int) is to get the position value of slider from 0
    //it will inflate the images, title, desc according the position num(tie with arrays)
    //layout inflater could change xml to view
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.texttitle);
        TextView slideDescription = (TextView) view.findViewById(R.id.textdesc);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(heaidngs[position]);
        slideDescription.setText(description[position]);

        //add the view to the container (diff ITD depends on position in array)
        container.addView(view);

        //return the view
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
