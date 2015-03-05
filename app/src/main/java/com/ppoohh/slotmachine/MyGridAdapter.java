package com.ppoohh.slotmachine;

import com.squareup.picasso.*;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso.*;

import android.content.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.net.*;
import android.os.*;
import android.support.v7.widget.*;
import android.support.v7.widget.RecyclerView.*;
import android.support.v7.widget.RecyclerView.*;
import android.support.v7.widget.RecyclerView.*;
import android.util.*;
import android.view.*;
import android.widget.*;

import java.util.*;

/**
 * Created by peter on 3/4/15.
 */
public class MyGridAdapter extends RecyclerView.Adapter {

	private Context mContext;
	private List<String> imageUrls = new ArrayList<>();
	private List<Float> ratios = new ArrayList<>();

	Picasso p;

	public MyGridAdapter(Context context) {
		mContext = context;
		Picasso p = new Builder(mContext)
				.memoryCache(new LruCache(24000))
				.build();
		p.setIndicatorsEnabled(true);
		p.setLoggingEnabled(true);
		Picasso.setSingletonInstance(p);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
		View itemView = LayoutInflater.from(mContext).inflate(R.layout.resizable_grid_item, null);
		MyViewHolder holder = new MyViewHolder(itemView);
		holder.imageView = (DynamicHeightImageView)itemView.findViewById(R.id.dynamic_height_image_view);
		holder.positionTextView = (TextView) itemView.findViewById(R.id.item_position_view);
		itemView.setTag(holder);
		return holder;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		MyViewHolder vh = (MyViewHolder) viewHolder;
		vh.positionTextView.setText("pos: " + position);
		vh.imageView.setRatio(ratios.get(position));
		Picasso.with(mContext).load(imageUrls.get(position)).placeholder(PlaceHolderDrawableHelper.getBackgroundDrawable(position)).into(vh.imageView);
	}

	@Override
	public int getItemCount() {
		return ratios.size();
	}

	public void addDrawable(String imageUrl, int width, int height) {
		Log.d("POH", imageUrl);
		imageUrls.add(imageUrl);
		float ratio = (float)height / (float)width;
		ratios.add(ratio);
	}
}
