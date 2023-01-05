package com.example.houzit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyHolder> {

    Context context;
    String ruserstring[];
    String rrentval[];
    String rdepositval[];
    int rimage[];

    public ListAdapter(Context c,String[] ruserstring, String[] rrentval, String[] rdepositval, int[] rimage) {
        this.ruserstring = ruserstring;
        this.rrentval = rrentval;
        this.rdepositval = rdepositval;
        this.rimage = rimage;
        context = c;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.single_row_item, parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.img.setImageResource(rimage[position]);
        holder.txt1.setText(ruserstring[position]);
        holder.txt2.setText(rrentval[position]);
        holder.txt3.setText(rdepositval[position]);
    }

    @Override
    public int getItemCount() {
        return ruserstring.length;
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txt1, txt2, txt3;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.imageview);
            txt1=itemView.findViewById(R.id.userString);
            txt2=itemView.findViewById(R.id.rentVal);
            txt3=itemView.findViewById(R.id.depositVal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, HouseDetails.class);
                    intent.putExtra("userstring", ruserstring[getAdapterPosition()]);
                    intent.putExtra("rentval", rrentval[getAdapterPosition()]);
                    intent.putExtra("depositval", rdepositval[getAdapterPosition()]);
                    intent.putExtra("image", rimage[getAdapterPosition()]);
                    context.startActivity(intent);


                }
            });
        }
    }
}
