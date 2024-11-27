package com.pProject.ganada;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Random;

public class CheckAnsDialog extends Dialog {  // 정답 제출 시 결과를 출력하는 Dialog
    private TextView txt_contents;
    private Button shutdownClick;

    int Currentstate = 5;
    int Stagenum;

    ImageView resultimg;

    private Context context1;

    @SuppressLint("ResourceAsColor")
    public CheckAnsDialog(@NonNull Context context, String contents, int ans, int select, int Current, int stage, int score) {
        super(context);
        setContentView(R.layout.activity_custom_dialog);
        Stagenum = stage;
        Currentstate = Current;
        txt_contents = findViewById(R.id.txt_contents);
        txt_contents.setText(contents);
        shutdownClick = findViewById(R.id.btn_shutdown);
        resultimg = findViewById(R.id.resultimgview);

        context1 = getContext();

        if (ans == select) {  // 정답인 경우
            shutdownClick.setText("다음으로");
            txt_contents.setText("정답입니다");
            resultimg.setImageResource(R.drawable.great);  // 정답 이미지
        } else {  // 오답인 경우
            txt_contents.setText("틀렸습니다. 다시 시도해보세요!");
            resultimg.setImageResource(R.drawable.incorrect);  // 오답 이미지로 변경
            shutdownClick.setText("다시 시도하기");
        }

        shutdownClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppValue appValue = (AppValue) getContext().getApplicationContext();
                int Currentstate = appValue.getCurrentstate(); // Currentstate 가져오기

                // 문제 제한: Currentstate >= 5인 경우
                if (Currentstate >= 5) {
                    txt_contents.setText("모든 문제를 완료했습니다!");
                    shutdownClick.setText("완료");
                    shutdownClick.setOnClickListener(null); // 버튼 비활성화
                    return;
                }

                // 정답일 경우
                if (ans == select) {
                    int tmptry = appValue.getTry();
                    int tmpLastscore = appValue.getLastscore();
                    tmpLastscore = tmpLastscore - tmptry + 1;
                    appValue.setLastscore(tmpLastscore);
                    appValue.setTry(0);

                    appValue.setCurrentstate(Currentstate + 1); // Currentstate 증가

                    Intent intent = new Intent(getContext().getApplicationContext(), Select4Q.class);
                    intent.putExtra("stageIndex", Stagenum);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);
                    dismiss();
                } else {  // 오답인 경우
                    Intent intent = new Intent(getContext().getApplicationContext(), Select4Q.class);
                    intent.putExtra("stageIndex", Stagenum);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);
                    dismiss();
                }
            }
        });
    }

    public void dismiss() {
        super.dismiss();
    }
}