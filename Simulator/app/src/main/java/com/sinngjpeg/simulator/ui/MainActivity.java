package com.sinngjpeg.simulator.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sinngjpeg.simulator.data.MatchesAPI;
import com.sinngjpeg.simulator.databinding.ActivityMainBinding;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MatchesAPI matchesAPI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
]
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
        //TODO CRIAR EVENTO DE CLICK E SIMULAÇÃO DE PARTIDAS.
    }

    private void setMatchesRefresh() {
        // TODO ATUALIZAR AS PARTIDAS NA AÇÃO DE SWIPE REFRESH
    }

    private void setupMatchesList() {
        // TODO LISTAR AS PARTIDAS, CONSUMINDO NOSSA API.
    }
}
