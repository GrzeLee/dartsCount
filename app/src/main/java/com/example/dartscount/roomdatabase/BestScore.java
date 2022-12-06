package com.example.dartscount.roomdatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Best_user_scores")
public class BestScore {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "mode1_easy_3min_best_score")
    public String easyBestScore3;

    @ColumnInfo(name = "mode1_easy_5min_best_score")
    public String easyBestScore5;

    @ColumnInfo(name = "mode1_easy_10min_best_score")
    public String easyBestScore10;

    @ColumnInfo(name = "mode1_medium_3min_best_score")
    public String mediumBestScore3;

    @ColumnInfo(name = "mode1_medium_5min_best_score")
    public String mediumBestScore5;

    @ColumnInfo(name = "mode1_medium_10min_best_score")
    public String mediumBestScore10;

    @ColumnInfo(name = "mode1_hard_3min_best_score")
    public String hardBestScore3;

    @ColumnInfo(name = "mode1_hard_5min_best_score")
    public String hardBestScore5;

    @ColumnInfo(name = "mode1_hard_10min_best_score")
    public String hardBestScore10;


    public String getEasyBestScore3() {
        return easyBestScore3;
    }

    public void setEasyBestScore3(String easyBestScore3) {
        this.easyBestScore3 = easyBestScore3;
    }

    public String getEasyBestScore5() {
        return easyBestScore5;
    }

    public void setEasyBestScore5(String easyBestScore5) {
        this.easyBestScore5 = easyBestScore5;
    }

    public String getEasyBestScore10() {
        return easyBestScore10;
    }

    public void setEasyBestScore10(String easyBestScore10) {
        this.easyBestScore10 = easyBestScore10;
    }

    public String getMediumBestScore3() {
        return mediumBestScore3;
    }

    public void setMediumBestScore3(String mediumBestScore3) {
        this.mediumBestScore3 = mediumBestScore3;
    }

    public String getMediumBestScore5() {
        return mediumBestScore5;
    }

    public void setMediumBestScore5(String mediumBestScore5) {
        this.mediumBestScore5 = mediumBestScore5;
    }

    public String getMediumBestScore10() {
        return mediumBestScore10;
    }

    public void setMediumBestScore10(String mediumBestScore10) {
        this.mediumBestScore10 = mediumBestScore10;
    }

    public String getHardBestScore3() {
        return hardBestScore3;
    }

    public void setHardBestScore3(String hardBestScore3) {
        this.hardBestScore3 = hardBestScore3;
    }

    public String getHardBestScore5() {
        return hardBestScore5;
    }

    public void setHardBestScore5(String hardBestScore5) {
        this.hardBestScore5 = hardBestScore5;
    }

    public String getHardBestScore10() {
        return hardBestScore10;
    }

    public void setHardBestScore10(String hardBestScore10) {
        this.hardBestScore10 = hardBestScore10;
    }

    public BestScore(String easyBestScore3, String easyBestScore5, String easyBestScore10, String mediumBestScore3, String mediumBestScore5, String mediumBestScore10, String hardBestScore3, String hardBestScore5, String hardBestScore10) {
        this.easyBestScore3 = easyBestScore3;
        this.easyBestScore5 = easyBestScore5;
        this.easyBestScore10 = easyBestScore10;
        this.mediumBestScore3 = mediumBestScore3;
        this.mediumBestScore5 = mediumBestScore5;
        this.mediumBestScore10 = mediumBestScore10;
        this.hardBestScore3 = hardBestScore3;
        this.hardBestScore5 = hardBestScore5;
        this.hardBestScore10 = hardBestScore10;
    }

}
