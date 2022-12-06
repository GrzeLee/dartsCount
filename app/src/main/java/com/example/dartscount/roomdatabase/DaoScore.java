package com.example.dartscount.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoScore {

    @Insert
    void insertAll(BestScore... bestScores);

    //Select data
    @Query("SELECT * FROM Best_user_scores")
    List<BestScore> selectAll();

    @Query("SELECT mode1_easy_3min_best_score FROM Best_user_scores")
    String getMode1Easy3min();

    @Query("SELECT mode1_easy_5min_best_score FROM Best_user_scores")
    String getMode1Easy5min();

    @Query("SELECT mode1_easy_10min_best_score FROM Best_user_scores")
    String getMode1Easy10min();

    @Query("SELECT mode1_medium_3min_best_score FROM Best_user_scores")
    String getMode1Medium3min();

    @Query("SELECT mode1_medium_5min_best_score FROM Best_user_scores")
    String getMode1Medium5min();

    @Query("SELECT mode1_medium_10min_best_score FROM Best_user_scores")
    String getMode1Medium10min();

    @Query("SELECT mode1_hard_3min_best_score FROM Best_user_scores")
    String getMode1Hard3min();

    @Query("SELECT mode1_hard_5min_best_score FROM Best_user_scores")
    String getMode1Hard5min();

    @Query("SELECT mode1_hard_10min_best_score FROM Best_user_scores")
    String getMode1Hard10min();

    //Update Data
    @Query("UPDATE Best_user_scores SET mode1_easy_3min_best_score= :score ")
    void updateEasyScore3min(String score);

    @Query("UPDATE Best_user_scores SET mode1_easy_5min_best_score= :score ")
    void updateEasyScore5min(String score);

    @Query("UPDATE Best_user_scores SET mode1_easy_10min_best_score= :score ")
    void updateEasyScore10min(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_3min_best_score= :score ")
    void updateMediumScore3min(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_5min_best_score= :score ")
    void updateMediumScore5min(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_10min_best_score= :score ")
    void updateMediumScore10min(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_3min_best_score= :score ")
    void updateHardScore3min(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_5min_best_score= :score ")
    void updateHardScore5min(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_10min_best_score= :score ")
    void updateHardScore10min(String score);

    //Delete all data
    @Query("DELETE FROM Best_user_scores")
    void delete();
}
