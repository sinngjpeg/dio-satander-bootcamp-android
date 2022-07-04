package com.sinngjpeg.soccernews.ui.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinngjpeg.soccernews.databinding.NewsItemBinding;
import com.sinngjpeg.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<News> news;
    private View.OnClickListener favoriteListener;

    public NewsAdapter(List<News> news, View.OnClickListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image)
                .fit()
                .into(holder.binding.ivThumbnail);
        holder.binding.btnOpenLink.setOnClickListener(v -> {
            Intent intentOpenLink = new Intent(Intent.ACTION_VIEW);
            intentOpenLink.setData(Uri.parse(news.link));
            holder.itemView.getContext().startActivity(intentOpenLink);
        });
        holder.binding.ivShare.setOnClickListener(v -> {
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("text/plain");
            intentShare.putExtra(Intent.EXTRA_SUBJECT, news.title);
            intentShare.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext()
                    .startActivity(Intent.createChooser(intentShare, "Share"));
        });
        holder.binding.ivFavorite.setOnClickListener(this.favoriteListener);
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
