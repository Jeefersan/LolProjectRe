package com.jeefersan.lolprojectre.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jeefersan.lolprojectre.model.matchwrapper.matches.Match;
import com.jeefersan.lolprojectre.model.SummonerModel;
import com.jeefersan.lolprojectre.util.SummonerRepository;

import java.util.List;


public class SearchViewModel extends AndroidViewModel{
    private SummonerRepository repository;

    public SearchViewModel(@NonNull Application application) {
        super(application);
        this.repository = new SummonerRepository(application);
    }


    public LiveData<SummonerModel> getSummoner(String input){
        repository.update(input);
        return repository.getSummoner();
    }

    public LiveData<Boolean> getError(){
        return repository.getError();
    }

    public LiveData<Boolean> isLoading(){
        return repository.getIsLoading();
    }

    public LiveData<List<Match>> getMatches(){
        return repository.getMatchMutableLiveData();
    }
}






