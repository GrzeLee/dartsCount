package com.example.dartscount.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BestScore.class}, version = 1)
public abstract class ScoreDataBase extends RoomDatabase {

    public abstract DaoScore daoScore();

    private static ScoreDataBase instance;

    public static ScoreDataBase getDataBase(final Context context) {
        if (instance == null) {
            synchronized (ScoreDataBase.class) {
                instance = Room.databaseBuilder(context, ScoreDataBase.class, "BESTSCOREDATABASE").allowMainThreadQueries().build();
            }
        }
        return instance;
    }
}


