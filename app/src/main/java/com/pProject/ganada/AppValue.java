package com.pProject.ganada;

import android.app.Application;

public class AppValue extends Application {
    private int trynum;        // 시도 횟수
    private int Lastscore;     // 마지막 점수
    private int Currentstate;  // 현재 문제 번호 추가

    public int getTry() {
        return trynum;
    }

    public void setTry(int trynum) {
        this.trynum = trynum;
    }

    public int getLastscore() {
        return Lastscore;
    }

    public void setLastscore(int Lastscore) {
        this.Lastscore = Lastscore;
    }

    public int getCurrentstate() {
        return Currentstate;   // Currentstate 값 반환
    }

    public void setCurrentstate(int Currentstate) {
        this.Currentstate = Currentstate;   // Currentstate 값 설정
    }
}
