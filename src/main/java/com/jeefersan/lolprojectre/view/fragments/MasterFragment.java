package com.jeefersan.lolprojectre.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeefersan.lolprojectre.viewmodel.BasePlayerViewModel;

public class MasterFragment extends BasePlayerFragment {

    private BasePlayerViewModel mViewModel;

    public MasterFragment(BasePlayerViewModel mViewModel) {
        super(mViewModel);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
