package com.abdurrahman.movies.ui.atvshows;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.viewmodel.TVShowsViewModel;
import com.abdurrahman.movies.viewmodel.ViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment {

    private RecyclerView rvTvShows;
    private ProgressBar progressBar;
    private TvShowsAdapter tvShowsAdapter;

    public static Fragment newInstance() {
        return new TvShowsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            progressBar.setVisibility(View.VISIBLE);
            TVShowsViewModel tvShowViewModel = obtainViewModel(getActivity());
            tvShowsAdapter = new TvShowsAdapter(getActivity());
            tvShowViewModel.setUsername("Abdurrahman");
            tvShowViewModel.tvShow.observe(getViewLifecycleOwner(), tvShowList -> {
                if (tvShowList != null) {
                    switch (tvShowList.status) {
                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;
                        case SUCCESS:
                            progressBar.setVisibility(View.GONE);
                            tvShowsAdapter.setListTvShows(tvShowList.data);
                            tvShowsAdapter.notifyDataSetChanged();
                            break;
                        case ERROR:
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });

            rvTvShows.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTvShows.setHasFixedSize(true);
            rvTvShows.setAdapter(tvShowsAdapter);
        }
    }

    @NonNull
    private static TVShowsViewModel obtainViewModel(FragmentActivity activity) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity, factory).get(TVShowsViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_shows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShows = view.findViewById(R.id.rv_tv_shows);
        progressBar = view.findViewById(R.id.progress_bar);
    }

}
