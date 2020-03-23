package com.abdurrahman.movies.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.abdurrahman.movies.R;
import com.abdurrahman.movies.ui.favorite.movies.FavoriteMovieFragment;
import com.abdurrahman.movies.ui.favorite.tvshows.FavoriteTvShowFragment;
import com.google.android.material.tabs.TabLayout;

public class FavoriteFragment extends Fragment {

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TabPagerAdapter tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());
        tabPagerAdapter.addFragment(new FavoriteMovieFragment(), getString(R.string.movies));
        tabPagerAdapter.addFragment(new FavoriteTvShowFragment(), getString(R.string.tvshows));

        ViewPager viewPager = view.findViewById(R.id.view_pager_favorite);
        viewPager.setAdapter(tabPagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_favorite);
        tabLayout.setupWithViewPager(viewPager);
    }
}
