package com.sinngjpeg.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sinngjpeg.soccernews.data.remote.SoccerNewsAPI;
import com.sinngjpeg.soccernews.domain.News;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final SoccerNewsAPI api;

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sinngjpeg.github.io/dio-soccer-news-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SoccerNewsAPI.class);
        findNews();
    }

    private void findNews() {
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                } else {
                    //TODO PENSAR EM UMA ESTRATEGIA DE TRATAMENTO DE ERROS
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                //TODO PENSAR EM UMA ESTRATEGIA DE TRATAMENTO DE ERROS
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}