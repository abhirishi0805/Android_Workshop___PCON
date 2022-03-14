package com.example.attendance_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    List<Subject> list;
    Context ctx;
    public SubjectAdapter(List<Subject> list, Context ctx){
        this.ctx=ctx;
        this.list=list;

    }
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allsubject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.ViewHolder holder, int position) {
        Subject s=list.get(position);
        holder.tv.setText(s.getSubject());
        int percent = (s.getTotalpresent()*100)/s.getTotalclass();
        int minimum = s.getMinimumpercentage();
        holder.tv1.setText(percent+"%");
        holder.tv2.setText("Attendance = "+s.getTotalpresent()+"/"+s.getTotalclass());
        if(percent==minimum){
            holder.tv3.setText("Status: On Track");
        }
        else if(percent>minimum){
            int a=percent;
            int b=s.getTotalclass();
            int c=0;
            while(a>minimum){
                b++;
                a=(s.getTotalpresent()*100)/b;
                if(a>=minimum)
                    c++;
            }
            if(c==0)
                holder.tv3.setText("Status: You can't leave next class");
            else if(c==1)
                holder.tv3.setText("Status: You can leave next class");
            else
                holder.tv3.setText("Status: You can leave next "+ c+" classes");
        }
        else{
            int a=percent;
            int b=s.getTotalclass();
            int c=0;
            int d = s.getTotalpresent();
            while(a<minimum){
                b++;
                d++;
                a=(d*100)/b;
                if(a<=minimum)
                    c++;
            }
            if(c!=1)
                holder.tv3.setText("Status: You have to attend next "+ c+" classes");
            else
                holder.tv3.setText("Status: You have to attend next class");
        }
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int present = s.getTotalpresent();
                int totalclass = s.getTotalpresent();
                present++;
                totalclass++;
                FirebaseDatabase.getInstance().getReference("Subject").child(s.getId()).child("totalpresent").setValue(present);
                FirebaseDatabase.getInstance().getReference("Subject").child(s.getId()).child("totalclass").setValue(totalclass);
                notifyDataSetChanged();
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int totalclass = s.getTotalclass();
                totalclass++;
                FirebaseDatabase.getInstance().getReference("Subject").child(s.getId()).child("totalclass").setValue(totalclass);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv,tv1,tv2,tv3;
        Button add,sub;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.subname);
            tv1 = itemView.findViewById(R.id.percentage);
            tv2 = itemView.findViewById(R.id.decimal);
            tv3 = itemView.findViewById(R.id.status);
            add=itemView.findViewById(R.id.add);
            sub = itemView.findViewById(R.id.sub);
        }
    }
}
