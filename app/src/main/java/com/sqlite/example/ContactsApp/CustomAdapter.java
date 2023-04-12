package com.sqlite.example.ContactsApp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList person_id, person_name, person_address, person_phoneno;

    CustomAdapter(Activity activity, Context context, ArrayList person_id, ArrayList person_name, ArrayList person_address,
                  ArrayList person_phoneno){
        this.activity = activity;
        this.context = context;
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_address = person_address;
        this.person_phoneno = person_phoneno;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_id_txt.setText(String.valueOf(person_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(person_name.get(position)));
        holder.book_author_txt.setText(String.valueOf(person_address.get(position)));
        holder.book_pages_txt.setText(String.valueOf(person_phoneno.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(person_id.get(position)));
                intent.putExtra("title", String.valueOf(person_name.get(position)));
                intent.putExtra("author", String.valueOf(person_address.get(position)));
                intent.putExtra("pages", String.valueOf(person_phoneno.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return person_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.person_id_txt);
            book_title_txt = itemView.findViewById(R.id.person_name_txt);
            book_author_txt = itemView.findViewById(R.id.person_address_txt);
            book_pages_txt = itemView.findViewById(R.id.person_phoneno_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
