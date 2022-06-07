package com.sinngjpeg.simulator.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.sinngjpeg.simulator.R;
import com.sinngjpeg.simulator.data.MatchesAPI;
import com.sinngjpeg.simulator.databinding.ActivityMainBinding;
import com.sinngjpeg.simulator.domain.Match;
import com.sinngjpeg.simulator.ui.adapter.MatchesAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MatchesAPI matchesAPI;
    private RecyclerView.Adapter matchesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setHttpClient();
        setupMatchesList();
        setMatchesRefresh();
        setFloatingActionButton();
    }

    private void setHttpClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sinngjpeg.github.io/dio-matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        matchesAPI = retrofit.create(MatchesAPI.class);
    }

    private void setFloatingActionButton() {
        binding.fabSimulate.setOnClickListener(view -> {
            view.animate().rotationBy(360).setDuration(500).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    //  TODO IMPLEMENTAR O ALGORITMO DE SIMULAÇÃO DE PARTIDA.
                }
            });
        });
    }

    private void setMatchesRefresh() {
        binding.srlMatches.setOnRefreshListener(this::findMatchesFromAPI);
    }

    private void setupMatchesList() {
        binding.srlMatches.setRefreshing(true);
        binding.rvMatches.setHasFixedSize(true);
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(this));
        findMatchesFromAPI();
    }

    private void findMatchesFromAPI() {
        matchesAPI.getMatches().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if (response.isSuccessful()) {
                    List<Match> matches = response.body();
                    matchesAdapter = new MatchesAdapter(matches);
                    binding.rvMatches.setAdapter(matchesAdapter);
                } else {
                    showErrorMessage();
                }
                binding.srlMatches.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show();
    }
}
