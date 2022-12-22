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

    @Query("SELECT mode1_easy_Time1_best_score FROM Best_user_scores")
    String getMode1EasyTime1();

    @Query("SELECT mode1_easy_Time2_best_score FROM Best_user_scores")
    String getMode1EasyTime2();

    @Query("SELECT mode1_easy_Time3_best_score FROM Best_user_scores")
    String getMode1EasyTime3();

    @Query("SELECT mode1_medium_Time1_best_score FROM Best_user_scores")
    String getMode1MediumTime1();

    @Query("SELECT mode1_medium_Time2_best_score FROM Best_user_scores")
    String getMode1MediumTime2();

    @Query("SELECT mode1_medium_Time3_best_score FROM Best_user_scores")
    String getMode1MediumTime3();

    @Query("SELECT mode1_hard_Time1_best_score FROM Best_user_scores")
    String getMode1HardTime1();

    @Query("SELECT mode1_hard_Time2_best_score FROM Best_user_scores")
    String getMode1HardTime2();

    @Query("SELECT mode1_hard_Time3_best_score FROM Best_user_scores")
    String getMode1HardTime3();

    //Update Data
    @Query("UPDATE Best_user_scores SET mode1_easy_Time1_best_score= :score ")
    void updateEasyScoreTime1(String score);

    @Query("UPDATE Best_user_scores SET mode1_easy_Time2_best_score= :score ")
    void updateEasyScoreTime2(String score);

    @Query("UPDATE Best_user_scores SET mode1_easy_Time3_best_score= :score ")
    void updateEasyScoreTime3(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_Time1_best_score= :score ")
    void updateMediumScoreTime1(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_Time2_best_score= :score ")
    void updateMediumScoreTime2(String score);

    @Query("UPDATE Best_user_scores SET mode1_medium_Time3_best_score= :score ")
    void updateMediumScoreTime3(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_Time1_best_score= :score ")
    void updateHardScoreTime1(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_Time2_best_score= :score ")
    void updateHardScoreTime2(String score);

    @Query("UPDATE Best_user_scores SET mode1_hard_Time3_best_score= :score ")
    void updateHardScoreTime3(String score);

}
