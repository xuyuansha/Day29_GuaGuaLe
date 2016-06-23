package com.example.huaming_lin.day29_guaguale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private GuaGuaLe ggl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ggl.savaCanvas2File();
            }
        });
        ggl = (GuaGuaLe) findViewById(R.id.ggl);

        System.out.print("dfd");

    }
}
