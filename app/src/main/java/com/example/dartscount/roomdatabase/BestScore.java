package com.example.dartscount.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Best_user_scores")
public class BestScore {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "mode1_easy_Time1_best_score")
    public String easyBestScoreTime1;

    @ColumnInfo(name = "mode1_easy_Time2_best_score")
    public String easyBestScoreTime2;

    @ColumnInfo(name = "mode1_easy_Time3_best_score")
    public String easyBestScoreTime3;

    @ColumnInfo(name = "mode1_medium_Time1_best_score")
    public String mediumBestScoreTime1;

    @ColumnInfo(name = "mode1_medium_Time2_best_score")
    public String mediumBestScoreTime2;

    @ColumnInfo(name = "mode1_medium_Time3_best_score")
    public String mediumBestScoreTime3;

    @ColumnInfo(name = "mode1_hard_Time1_best_score")
    public String hardBestScoreTime1;

    @ColumnInfo(name = "mode1_hard_Time2_best_score")
    public String hardBestScoreTime2;

    @ColumnInfo(name = "mode1_hard_Time3_best_score")
    public String hardBestScoreTime3;

    public BestScore(String easyBestScoreTime1, String easyBestScoreTime2, String easyBestScoreTime3, String mediumBestScoreTime1, String mediumBestScoreTime2, String mediumBestScoreTime3, String hardBestScoreTime1, String hardBestScoreTime2, String hardBestScoreTime3) {
        this.easyBestScoreTime1 = easyBestScoreTime1;
        this.easyBestScoreTime2 = easyBestScoreTime2;
        this.easyBestScoreTime3 = easyBestScoreTime3;
        this.mediumBestScoreTime1 = mediumBestScoreTime1;
        this.mediumBestScoreTime2 = mediumBestScoreTime2;
        this.mediumBestScoreTime3 = mediumBestScoreTime3;
        this.hardBestScoreTime1 = hardBestScoreTime1;
        this.hardBestScoreTime2 = hardBestScoreTime2;
        this.hardBestScoreTime3 = hardBestScoreTime3;
    }
}
