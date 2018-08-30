package com.example.efhemo.mynews;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.efhemo.mynews.data.NewsContract;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by EFHEMO on 3/3/2018.
 */
public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolder> {

    Cursor dataCursor;
    Context context;
    Uri uri;

    //private static ViewHolder.ClickListener clickListener;

    public RCAdapter(Context context, Cursor dataCursor, Uri uri) {
        this.dataCursor = dataCursor;
        this.context = context;
        this.uri = uri;
    }

    /*public void setOnItemClickListenet(ViewHolder.ClickListener clickListener){
        //RCAdapter.clickListener = clickListener;
    }*/

    @Override
    public RCAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card, parent, false);
        return new ViewHolder(context, v, uri);
    }

    public Cursor swapCursor(Cursor cursor) {
        if (dataCursor == cursor) {
            return null;
        }
        Cursor oldCursor = dataCursor;
        this.dataCursor = cursor;
        if (cursor != null) {
            this.notifyDataSetChanged();
        }
        return oldCursor;
    }


    @Override
    public void onBindViewHolder(RCAdapter.ViewHolder holder, final int position) {

        dataCursor.moveToPosition(position);
        String title = dataCursor.getString(dataCursor.getColumnIndex(NewsContract.COLUMN_NEWS_TITLE));
        String date = dataCursor.getString(dataCursor.getColumnIndex(NewsContract.COLUMN_NEWS_DATE));
        String image = dataCursor.getString(dataCursor.getColumnIndex(NewsContract.COLUMN_NEWS_IMAGE));

        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        date = date.substring(0, 10);

        String jsonDate = date.replaceAll("-", "");
        int formatedJsonInint = Integer.parseInt(jsonDate);
        String formattedCurrentDate = strDate.replaceAll("-", "");
        int formatedCurInint = Integer.parseInt(formattedCurrentDate);



        if(date.equals(strDate)){
            date = "Today";
        }if(formatedJsonInint == formatedCurInint - 1){
            date = "Yesterday";
        }


        holder.titleView.setText(title);
        com.squareup.picasso.Picasso.with(context)
                .load(image).placeholder(R.drawable.ic_aspect_ratio_black_24dp).fit()
                .centerCrop().noFade().into(holder.photoView);
        //set the date
        holder.dateView.setText(date);
    }



    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return (dataCursor == null) ? 0 : dataCursor.getCount();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView photoView;
        private TextView titleView;
        //ImageView linkView;
        private TextView dateView;
        private Context mContext;
        private Uri uri;

        public ViewHolder(final Context mContext, final View itemView, final Uri uri) {
            super(itemView);
            photoView = (ImageView) itemView.findViewById(R.id.news_image);
            titleView = (TextView) itemView.findViewById(R.id.title);
            //link = (ImageView) itemView.findViewById(R.id.share);
            dateView = (TextView) itemView.findViewById(R.id.date);
            this.mContext = mContext;
            this.uri = uri;
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    sendIntent(position, uri);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                        sendIntent(position, uri);
                }
            });

        }

        public void sendIntent(int position, Uri uri) {
            Intent intent = new Intent(mContext, webActivity.class);
            Uri contentUri = ContentUris.withAppendedId(uri, position + 1);
            Cursor cursor = mContext.getContentResolver().query(contentUri, new String[]{
                    NewsContract.COLUMN_NEWS_TITLE,
                    NewsContract.COLUMN_NEWS_URL}, null, null, null);

            try {
                // Proceed with moving to the first row of the cursor and reading data from it
                // (This should be the only row in the cursor)
                if (cursor != null && cursor.moveToFirst()) {
                    // Find the columns of pet attributes that we're interested in
                    int nameColumnIndex = cursor.getColumnIndex(NewsContract.COLUMN_NEWS_TITLE);
                    int urlColumnIndex = cursor.getColumnIndex(NewsContract.COLUMN_NEWS_URL);

                    // Extract out the value from the Cursor for the given column index
                    String title = cursor.getString(nameColumnIndex);
                    String url = cursor.getString(urlColumnIndex);

                    intent.putExtra("title", title);
                    intent.putExtra("url", url);

                    mContext.startActivity(intent);
                    cursor.close();
                }

            } catch (Exception e){
                Log.d("what exception", "this exception "+ e);
            }

        }

    }
}
