package com.rohit.examples.android.indiancoffeehouse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Integer array to store drawable sources.
    private static final Integer[] images = {
            R.drawable.ich,
            R.drawable.coffee,
            R.drawable.manchurian,
            R.drawable.pavbhaji,
            R.drawable.dosa
    };
    // ViewPager ImageSlider page initialized to 0 (zero).
    public static int currentPage = 0;
    // Variable declaration for the XML views.
    public ViewPager mPager;
    public TextView addr_Tv;
    public TextView url_Tv;
    public ImageView phn_Img;
    public ImageView mail_Img;
    public Button about_Btn;
    // ArrayList Collection to store images.
    private ArrayList<Integer> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hiding the ActionBar for the screen.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        setContentView(R.layout.activity_main);


        // Fetching ids of all XML elements into View type variables.
        addr_Tv = findViewById(R.id.addr_text);
        url_Tv = findViewById(R.id.web_text);
        phn_Img = findViewById(R.id.phone_img);
        mail_Img = findViewById(R.id.email_img);
        about_Btn = findViewById(R.id.about_btn);

        // Method to initiate ViewPager ImageSlide (automatic).
        init();

        // OnClick() Interface call to handle 'Place Address' TextView click.
        addr_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToMap(view);
            }
        });

        // onClick() Interface call to handle 'Phone' ImageView click.
        phn_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToPhone(view);
            }
        });

        // onClick() Interface call to handle 'Mail' ImageView click.
        mail_Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToMail(view);
            }
        });

        // onClick() Interface call to handle 'About' Button click.
        about_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linkToWiki(view);
            }
        });
    }

    // Method definition to initiate ViewPager ImageSlider automatically whenever the app runs for indefinite times.
    private void init() {

        arrayList.addAll(Arrays.asList(images));
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(new ImageAdapter(MainActivity.this, arrayList));

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mPager.setCurrentItem(currentPage, true);
                if (currentPage == images.length + 1) {
                    currentPage = 0;
                } else {
                    ++currentPage;
                }
            }
        };

        Timer swipeTime = new Timer();
        swipeTime.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1500, 2000);
    }

    // Method definition for onClick() Interface call to handle 'Place Address' TextView click.
    public void linkToMap(View v) {
        String map_Addr = addr_Tv.getText().toString();
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + map_Addr));
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }
    }

    // Method definition for onClick() Interface call to handle 'Phone' ImageView click.
    public void linkToPhone(View v) {
        String phoneNumber = "tel:07514011417";
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse(phoneNumber));
        if (phoneIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(phoneIntent);
        }
    }

    //Method definition for onClick() Interface call to handle 'Mail' ImageView click.
    public void linkToMail(View v) {
        String email = mail_Img.getContentDescription().toString();
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setData(Uri.parse("mailto:" + email));

        if (mailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mailIntent);
        }
    }

    // Method definition for onClick() Interface call to handle 'About' Button click.
    public void linkToWiki(View v) {
        String url = "https://en.wikipedia.org/wiki/Indian_Coffee_House";
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (webIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(webIntent);
        }
    }
}
