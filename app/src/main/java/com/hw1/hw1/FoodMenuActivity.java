package com.hw1.hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FoodMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);

        setSupportActionBar((Toolbar) findViewById(R.id.my_toolbar));
        ListView listView = (ListView) findViewById(R.id.food_menu_list_view);
        Resources res = getResources();
        String[] strings = res.getStringArray(R.array.food_menu_strings);
        TypedArray images = res.obtainTypedArray(R.array.food_menu_images);
        FoodListItemArrayAdapter adapter = new FoodListItemArrayAdapter(this, strings, images);
        listView.setAdapter(adapter);
    }


    public class FoodListItemArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] strings;
        private final TypedArray images;

        public FoodListItemArrayAdapter(Context context, String[] strings, TypedArray images) {
            super(context, R.layout.food_menu_row_item, strings);
            this.context = context;
            this.strings = strings;
            this.images = images;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.food_menu_row_item, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.food_menu_item_text);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.food_menu_item_image);
            textView.setText(strings[position]);
            Drawable imagesIndex = images.getDrawable(position);
            imageView.setImageDrawable(imagesIndex);
            rowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("FOOD", ((TextView) v.findViewById(R.id.food_menu_item_text)).getText());
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            });
            return rowView;
        }
    }


}

