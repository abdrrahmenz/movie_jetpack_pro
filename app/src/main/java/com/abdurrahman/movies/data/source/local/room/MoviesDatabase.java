package com.abdurrahman.movies.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.abdurrahman.movies.data.source.local.entity.MoviesEntity;
import com.abdurrahman.movies.data.source.local.entity.TVShowEntity;

@Database(entities = {MoviesEntity.class, TVShowEntity.class},
        version = 1,
        exportSchema = false)
public abstract class MoviesDatabase extends RoomDatabase {
    private static MoviesDatabase INSTANCE;

    private static final Object sLock = new Object();

    public static MoviesDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MoviesDatabase.class, "movies.db")
                        .build();
            }
            return INSTANCE;
        }
    }

    public abstract MoviesDao moviesDao();
}
