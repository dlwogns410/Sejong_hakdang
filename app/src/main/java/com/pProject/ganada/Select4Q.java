package com.pProject.ganada;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Select4Q extends AppCompatActivity {

    private CheckAnsDialog customDialog;

    String[] wordlist = {"아이", "화가", "뿌리", "가수", "오리", "파도", "부모", "까치", "의자"};
    // 단어 라벨 0-아이 1-화가 2-뿌리 3-가수 4-오리 5-파도 6-부모 7-까치 8-의자

    int answer; // 문제의 답안 저장
    int currenchk = 99; // 현재 선택한 선택지 표시
    Button A1, A2, A3, A4, btn_check;
    ImageButton imgA1, imgA2, imgA3, imgA4;
    ImageView ans_Img;
    TextView text, ans_word;
    String tmp = "";

    int count = 4; // 난수 생성 갯수
    int a[] = new int[count];

    int Currentstate = 1;
    int Stagenum;

    int QuestionType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select4_q);

        text = findViewById(R.id.select4_text);
        A1 = findViewById(R.id.Q4_ans1);
        A2 = findViewById(R.id.Q4_ans2);
        A3 = findViewById(R.id.Q4_ans3);
        A4 = findViewById(R.id.Q4_ans4);
        ans_Img = findViewById(R.id.ans_img);

        imgA1 = findViewById(R.id.Img_Q4_ans1);
        imgA2 = findViewById(R.id.Img_Q4_ans2);
        imgA3 = findViewById(R.id.Img_Q4_ans3);
        imgA4 = findViewById(R.id.Img_Q4_ans4);
        ans_word = findViewById(R.id.ans_word);

        // 문제 전처리 부분
        Random rnd = new Random();
        QuestionType = rnd.nextInt(2); // 문제 유형

        // 정답을 정하고, 보기 세 개를 정함
        makeQuestion();
        resetButtonColors(); // 버튼 색상 초기화 추가
        makeImg();

        // 문제 유형에 따른 UI 설정
        if (QuestionType == 0) { // 그림을 맞추는 유형
            ans_word.setText(wordlist[answer]);
            text.setText("단어에 맞는 그림을 고르세요.");
        } else {
            A1.setVisibility(View.VISIBLE);
            A2.setVisibility(View.VISIBLE);
            A3.setVisibility(View.VISIBLE);
            A4.setVisibility(View.VISIBLE);
            ans_Img.setVisibility(View.VISIBLE);

            imgA1.setVisibility(View.INVISIBLE);
            imgA2.setVisibility(View.INVISIBLE);
            imgA3.setVisibility(View.INVISIBLE);
            imgA4.setVisibility(View.INVISIBLE);
            ans_word.setVisibility(View.INVISIBLE);
        }

        // 다이얼로그 밖의 화면은 흐리게 만들어줌
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        btn_check = findViewById(R.id.btn_check);
        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 아무것도 선택하지 않고 정답을 제출하려는 경우
                if (currenchk == 99) {
                    Toast.makeText(getApplicationContext(), "정답을 선택하세요.", Toast.LENGTH_SHORT).show();
                } else { // 오답을 선택한 경우
                    customDialog = new CheckAnsDialog(view.getContext(), "[ " + wordlist[a[currenchk]] + " ]가 아닌 다른 것을 생각해봅시다.", answer, a[currenchk], Currentstate, Stagenum, -99);
                    customDialog.show();
                }
            }
        });

        // 버튼 클릭 시 상태 관리 코드 (생략 없이 포함)
        setupAnswerButtons();
        setupImageButtons();
    }

    private void resetButtonColors() {
        // 단어 문제 버튼 초기화
        A1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        A2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        A3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        A4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));

        // 그림 문제 버튼 초기화
        imgA1.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        imgA2.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        imgA3.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));
        imgA4.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn_nomal)));

        // 현재 선택 상태 초기화
        currenchk = 99;
    }

    private void setupAnswerButtons() {
        A1.setOnClickListener(view -> handleButtonClick(A1, 0));
        A2.setOnClickListener(view -> handleButtonClick(A2, 1));
        A3.setOnClickListener(view -> handleButtonClick(A3, 2));
        A4.setOnClickListener(view -> handleButtonClick(A4, 3));
    }

    private void handleButtonClick(Button button, int index) {
        resetButtonColors();
        button.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn)));
        currenchk = index;
    }

    private void setupImageButtons() {
        imgA1.setOnClickListener(view -> handleImageButtonClick(imgA1, 0));
        imgA2.setOnClickListener(view -> handleImageButtonClick(imgA2, 1));
        imgA3.setOnClickListener(view -> handleImageButtonClick(imgA3, 2));
        imgA4.setOnClickListener(view -> handleImageButtonClick(imgA4, 3));
    }

    private void handleImageButtonClick(ImageButton imageButton, int index) {
        resetButtonColors();
        imageButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.btn)));
        currenchk = index;
    }

    public void makeQuestion() {
        Random r = new Random();
        for (int i = 0; i < count; i++) {
            a[i] = r.nextInt(9); // 0부터 8까지의 난수 생성

            // 중복 방지
            for (int j = 0; j < i; j++) {
                if (a[i] == a[j]) {
                    i--; // 중복이 있으면 i를 감소시켜 다시 처리
                    break; // 중복이 발견되면 그 즉시 반복문 종료
                }
            }
        }

        answer = a[0]; // 정답 적어두기
        int k = r.nextInt(4);
        int temp = a[k];
        a[k] = a[0];
        a[0] = temp;

        A1.setText(wordlist[a[0]]);
        A2.setText(wordlist[a[1]]);
        A3.setText(wordlist[a[2]]);
        A4.setText(wordlist[a[3]]);

        makeImg_select(imgA1, a[0]);
        makeImg_select(imgA2, a[1]);
        makeImg_select(imgA3, a[2]);
        makeImg_select(imgA4, a[3]);
    }

    public void makeImg() {
        switch (answer) {
            case 0:
                ans_Img.setImageResource(R.drawable.kid);
                break;
            case 1:
                ans_Img.setImageResource(R.drawable.painter);
                break;
            case 2:
                ans_Img.setImageResource(R.drawable.root);
                break;
            case 3:
                ans_Img.setImageResource(R.drawable.singer);
                break;
            case 4:
                ans_Img.setImageResource(R.drawable.duck);
                break;
            case 5:
                ans_Img.setImageResource(R.drawable.wave);
                break;
            case 6:
                ans_Img.setImageResource(R.drawable.parents);
                break;
            case 7:
                ans_Img.setImageResource(R.drawable.magpie);
                break;
            case 8:
                ans_Img.setImageResource(R.drawable.chair);
                break;
        }
    }

    public void makeImg_select(ImageButton imgB, int here) {
        switch (here) {
            case 0:
                imgB.setImageResource(R.drawable.kid);
                break;
            case 1:
                imgB.setImageResource(R.drawable.painter);
                break;
            case 2:
                imgB.setImageResource(R.drawable.root);
                break;
            case 3:
                imgB.setImageResource(R.drawable.singer);
                break;
            case 4:
                imgB.setImageResource(R.drawable.duck);
                break;
            case 5:
                imgB.setImageResource(R.drawable.wave);
                break;
            case 6:
                imgB.setImageResource(R.drawable.parents);
                break;
            case 7:
                imgB.setImageResource(R.drawable.magpie);
                break;
            case 8:
                imgB.setImageResource(R.drawable.chair);
                break;
        }
    }
}
