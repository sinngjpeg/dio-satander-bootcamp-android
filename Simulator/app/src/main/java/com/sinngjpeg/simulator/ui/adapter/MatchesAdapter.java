package com.sinngjpeg.simulator.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sinngjpeg.simulator.databinding.MainItemBinding;
import com.sinngjpeg.simulator.domain.Match;
import com.sinngjpeg.simulator.ui.DetailActivity;

import java.util.List;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    private List<Match> matches;

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MainItemBinding binding;

        public ViewHolder(MainItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MainItemBinding binding = MainItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesAdapter.ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Match match = matches.get(position);
        holder.binding.tvHomeTeamName.setText(match.getHomeTeam().getName());
        holder.binding.tvVisitTeamName.setText(match.getAwayTeam().getName());
        Glide.with(context).load(match.getHomeTeam().getImage()).into(holder.binding.ivHomeTeam);
        Glide.with(context).load(match.getAwayTeam().getImage()).into(holder.binding.ivVisitTeamName);
        if (match.getHomeTeam().getScore() != null) {
            holder.binding.tvHomeScore.setText(String.valueOf(match.getHomeTeam().getScore()));
        }
        if (match.getAwayTeam().getScore() != null) {
            holder.binding.tvVisitTeamScore.setText(String.valueOf(match.getAwayTeam().getScore()));
        }
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.Extras.MATCH, match);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }
}
