package com.sinngjpeg.simulator.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sinngjpeg.simulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupMatchesList();
        setMatchesRefresh();
        setFloatingActionButton();
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
