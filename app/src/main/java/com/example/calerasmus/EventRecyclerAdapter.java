package com.example.calerasmus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.MyViewHolder>{

    Context context;
    ArrayList<Events> arrayList;
    DBOpenHelper dbOpenHelper;

    public EventRecyclerAdapter(Context context, ArrayList<Events> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_row_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Events events = arrayList.get(position);
        holder.Event.setText(events.getEVENT());
        holder.DateText.setText(events.getDATE());
        holder.Time.setText(events.getTIME());
        holder.brisanjeeventova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteCalendarEvent(events.getEVENT(),events.getDATE(),events.getTIME());
                arrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "Event uspješno izbrisan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView DateText,Event,Time;
        Button brisanjeeventova;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            DateText = itemView.findViewById(R.id.eventdate);
            Event = itemView.findViewById(R.id.eventname);
            Time = itemView.findViewById(R.id.eventtime);
            brisanjeeventova = itemView.findViewById(R.id.brisanjeventova);
        }
    }

    private void deleteCalendarEvent(String event,String date, String time){
        dbOpenHelper = new DBOpenHelper(context);
        SQLiteDatabase database = dbOpenHelper.getWritableDatabase();
        dbOpenHelper.brisanjeEventa(event,date,time,database);
        dbOpenHelper.close();
    }

}
