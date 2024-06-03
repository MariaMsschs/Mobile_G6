package com.example.mobile_g6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private CheckBox mensal;
    private CheckBox anual;

    private EditText renda;

    private Button enviar;
    Bundle dados = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensal = findViewById(R.id.mensal);
        anual = findViewById(R.id.anual);
        renda = findViewById(R.id.renda);
        enviar = findViewById(R.id.enviar);

        mensal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    anual.setChecked(false);
                }
            }
        });

        anual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mensal.setChecked(false);
                }
            }
        });

        dados.putString("renda", renda.getText().toString());

        enviar.setOnClickListener(v -> {
            if (mensal.isChecked() && !renda.getText().toString().isEmpty()) {
                dados.putBoolean("anual", false);
                dados.putBoolean("mensal", true);
                Intent intent = new Intent(this, TelaCalculo.class);
                intent.putExtras(dados);
                startActivity(intent);
            } else if (anual.isChecked() && !renda.getText().toString().isEmpty()) {
                dados.putBoolean("anual", true);
                dados.putBoolean("mensal", false);
                Intent intent = new Intent(this, TelaCalculo.class);
                intent.putExtras(dados);
                startActivity(intent);
            }
        });
    }
}