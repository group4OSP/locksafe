package com.example.locksafe;

import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;

public class Rating extends AppCompatActivity {
  private NestedScrollView scroll;
  private AppCompatButton ratingBtn;
  private RatingBar ratingBar;

  int appRating = 0;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rating);

    scroll = (NestedScrollView) findViewById(R.id.nestscroll);
    ratingBtn = (AppCompatButton) findViewById(R.id.rating_btn);
    ratingBar = (RatingBar) findViewById(R.id.ratingBar);

    ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
      @Override
      public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        int rate = (int) rating;
        String message = null;

        appRating = (int) ratingBar.getRating();

        switch (rate){
          case 1:
            message = "Sorry to hear that :(";
            break;
          case 2:
            message = "We'll try to do better!";
            break;
          case 3:
            message = "Good";
            break;
          case 4:
            message = "Great! Thank you!";
            break;
          case 5:
            message = "Awesome! We really appreciate it! :)";
            break;
        }
        Toast.makeText(Rating.this,message,Toast.LENGTH_SHORT).show();
      }
    });

    ratingBtn.setOnClickListener(new View.OnClickListener(){

      @Override
      public void onClick(View v) {
        Toast.makeText(Rating.this,String.valueOf(appRating), Toast.LENGTH_SHORT).show();
      }
    });
  }
}
