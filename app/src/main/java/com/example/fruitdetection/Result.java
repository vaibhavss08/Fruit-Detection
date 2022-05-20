package com.example.fruitdetection;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result extends AppCompatActivity {
    DatabaseReference ref;
    DatabaseReference reff;
    TableLayout t ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        String user = getIntent().getStringExtra("Username");

        reff = FirebaseDatabase.getInstance().getReference().child("Fruits");
        ref = reff.child("-N1YqxgNxz7dHQr3srtX");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Fruit_Data> fruit = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Fruit_Data obj = new Fruit_Data(ds.getKey(),(Long) ds.getValue());
                    fruit.add(obj);
                }
                collectData(fruit);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void collectData(ArrayList<Fruit_Data> data){
        ArrayList<String> fruits = new ArrayList<>();
        ArrayList<Long> number = new ArrayList<>();
        int size = data.size();
        for (Fruit_Data result : data) {
            fruits.add(result.data);
            number.add(result.count);
        }

        fruits.remove(0);
        number.remove(0);

        t = findViewById(R.id.TableLayout);
        TableRow ro = new TableRow(Result.this);
        TextView txt = new TextView(Result.this);
        txt.setTextColor(Color.WHITE);
        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
        txt.setGravity(Gravity.CENTER_HORIZONTAL);
        txt.setText("Sr.No");
        txt.setPadding(10,10,10,10);
        txt.setBackgroundColor(Color.MAGENTA);
        ro.addView(txt);

        // col = 2
        txt = new TextView(Result.this);
        txt.setTextColor(Color.WHITE);
        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
        txt.setGravity(Gravity.CENTER_HORIZONTAL);
        txt.setText("Fruit");
        txt.setPadding(10,10,10,10);
        txt.setBackgroundColor(Color.BLUE);
        ro.addView(txt);

        // col = 3
        txt = new TextView(Result.this);
        txt.setTextColor(Color.WHITE);
        txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
        txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
        txt.setGravity(Gravity.CENTER_HORIZONTAL);
        txt.setText("Quantity");
        txt.setBackgroundColor(Color.GREEN);
        txt.setPadding(10,10,10,10);
        ro.addView(txt);
        t.addView(ro);

        for(int i=1;i<=size;i++){
            final TableRow row = new TableRow(Result.this);
            // col = 1

            txt = new TextView(Result.this);
            txt.setTextColor(Color.WHITE);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
            txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
            txt.setGravity(Gravity.CENTER_HORIZONTAL);
            txt.setText(String.valueOf(i)+" ");
            txt.setPadding(10,10,10,10);
            txt.setBackgroundColor(Color.MAGENTA);
            row.addView(txt);

            // col = 2
            txt = new TextView(Result.this);
            txt.setTextColor(Color.WHITE);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
            txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
            txt.setGravity(Gravity.CENTER_HORIZONTAL);
            txt.setText(String.valueOf(fruits.get(i-1))+" ");
            txt.setPadding(10,10,10,10);
            txt.setBackgroundColor(Color.BLUE);
            row.addView(txt);

            // col = 3
            txt = new TextView(Result.this);
            txt.setTextColor(Color.WHITE);
            txt.setTextSize(TypedValue.COMPLEX_UNIT_PT, 10);
            txt.setTypeface(Typeface.SERIF, Typeface.BOLD);
            txt.setGravity(Gravity.CENTER_HORIZONTAL);
            txt.setText(String.valueOf(number.get(i-1))+" ");
            txt.setBackgroundColor(Color.GREEN);
            txt.setPadding(10,10,10,10);
            row.addView(txt);
            t.addView(row);
        }
    }


}

class Fruit_Data{
    String data;
    Long count;

    Fruit_Data(String data, Long count){
        this.data = data;
        this.count = count;
    }

    public void setData(){
       this.data=data;
    }

    public void setCount(){
        this.count = count;
    }

    public String getData(){
        return data;
    }

    public Long getCount(){
        return count;
    }
}