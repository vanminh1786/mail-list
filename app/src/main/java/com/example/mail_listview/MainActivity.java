package com.example.mail_listview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mail_listview.MailItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<MailItemModel> mailModels;
    List<Drawable> listBackgrounds;
    List<String> hourExtensions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init list of avatar backgrounds
        listBackgrounds = new ArrayList<>();
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview1));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview2));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview3));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview4));
        listBackgrounds.add(getResources().getDrawable(R.drawable.rounded_textview5));

        // init hour extension (AM / PM)
        hourExtensions = new ArrayList<>();
        hourExtensions.add("AM");
        hourExtensions.add("PM");

        // init list of mails
        mailModels = new ArrayList<>();
        for (int i = 1; i <= 50; i++){
            mailModels.add(new MailItemModel(getRandomUserName(i), getRandomHour(),
                    "Xin chào Zhou Meng Jin " + i * 999 + " tớ muốn gửi cậu một thông điệp",
                    "Tớ muốn nói... " + i * 99 + " tớ thích cậu thật nhiều", false));
        }

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        MailAdapter recycleViewAdapter = new MailAdapter(mailModels, listBackgrounds);
        recyclerView.setAdapter(recycleViewAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\">" + getString(R.string.app_name) + "</font>"));
        return true;
    }

    // Create random send hour
    private String getRandomHour() {
        int hour =  (int) ((Math.random() * (12 - 0)) + 0);
        int minute = (int) ((Math.random() * (60 - 0)) + 0);
        String hourExtension = getRandomHourExtension();
        if (hour < 10 && minute < 10){
            return "0" + hour + ":" + "0" + minute + " " + hourExtension;
        }else if(hour < 10 && minute >= 10){
            return "0" + hour + ":" + minute + " " + hourExtension;
        }else if(hour >= 10 && minute < 10){
            return hour + ":" + "0" + minute + " " + hourExtension;
        }
        return hour + ":" + minute + " " + hourExtension;
    }

    // Create random AM or PM
    private String getRandomHourExtension(){
        return hourExtensions.get( (int) ((Math.random() * (1 - 0)) + 0));
    }

    // Create random username that send email
    private String getRandomUserName(int i){
        int c = (int) ((Math.random() * (90 - 65)) + 65);
        char head = (char)c;
        return head + " MinhNV 20194121 " + i * 100;
    }
}