package com.udacity.sandwichclub;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView origintv,sandwichdescription,alsoknownas,place_of_origin,ingredients;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Getting our Layout XML by their id

       origintv = (TextView) findViewById(R.id.also_known_tv);
       imageView=(ImageView)findViewById(R.id.image_iv);
      sandwichdescription = (TextView) findViewById(R.id.description_tv);
      alsoknownas=(TextView)findViewById(R.id.also_known_tv);
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        place_of_origin=(TextView)findViewById(R.id.placeoforigin);
        ingredients=(TextView)findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        try {
            populateUI(position);


        Picasso.with(this)
                .load(sandwich.getImage())
                .into(imageView);



            setTitle(sandwich.getMainName());


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(int position) throws JSONException {

        //Getting our Array Value
        String[] sandwitchdata = getResources().getStringArray(R.array.sandwich_details);

        //Greating class object and parsing our array.
        String json = sandwitchdata[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);

        //Getting data parsed from sandwitch class
        //Assigning data to correct values in the XML Layout
       origintv.setText(sandwich.getMainName());
       place_of_origin.setText(sandwich.getPlaceOfOrigin());
       alsoknownas.setText(sandwich.getAlsoKnownAs().toString());
       ingredients.setText(sandwich.getIngredients().toString());
       sandwichdescription.setText(sandwich.getDescription());



    }


    }

