 package com.example.moviex.adapters;

 import android.content.Context;
 import android.content.Intent;
 import android.net.Uri;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.TextView;

 import androidx.recyclerview.widget.RecyclerView;

 import com.bumptech.glide.Glide;
 import com.bumptech.glide.request.RequestOptions;
 import com.example.moviex.R;
 import com.example.moviex.activities.MoviesActivity;
 import com.example.moviex.models.Movies;

 import java.util.List;

// Tüm yönlendirmeler, bağlantılar bu sınıf üzerinden yönetilecek.

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext ;
    private List<Movies> mData ;
    RequestOptions option;


    public RecyclerViewAdapter(Context mContext, List<Movies> mData) {
        this.mContext = mContext;
        this.mData = mData;

        // Request option for Glide
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.movies_row_item,parent,false) ;
        final MyViewHolder viewHolder = new MyViewHolder(view) ;
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, MoviesActivity.class);
                i.putExtra("anime_name",mData.get(viewHolder.getAdapterPosition()).getName());
                i.putExtra("anime_description",mData.get(viewHolder.getAdapterPosition()).getDescription());
                i.putExtra("anime_studio",mData.get(viewHolder.getAdapterPosition()).getStudio());
                i.putExtra("anime_category",mData.get(viewHolder.getAdapterPosition()).getCategorie());
                i.putExtra("anime_nb_episode",mData.get(viewHolder.getAdapterPosition()).getNb_episode());
                i.putExtra("anime_rating",mData.get(viewHolder.getAdapterPosition()).getRating());
                i.putExtra("mov_url",mData.get(viewHolder.getAdapterPosition()).getUrl());
                //i.putExtra("anime_img",mData.get(viewHolder.getAdapterPosition()).getImage_url());

                mContext.startActivity(i);

            }
        });

        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name ;
        TextView tv_rating ;
        TextView tv_studio ;
        TextView tv_category;
        ImageView thumbnail;
        LinearLayout view_container;

        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.container);
            tv_name = itemView.findViewById(R.id.anime_name);
            tv_category = itemView.findViewById(R.id.categorie);
            tv_rating = itemView.findViewById(R.id.rating);
            tv_studio = itemView.findViewById(R.id.studio);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            //img_thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(mData.get(position).getName());
        holder.tv_rating.setText(mData.get(position).getRating());
        holder.tv_studio.setText(mData.get(position).getStudio());
        holder.tv_category.setText(mData.get(position).getCategorie());
        Uri uri = Uri.parse(mData.get(position).getUrl()); // url'i çekip uri'ye dönüştür
        Glide.with(mContext).asBitmap().load(uri).into(holder.thumbnail);

    }

}