package com.example.checkboxushakov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText inputMoney;
    private EditText inputInfo;
    private Button buttonOk;
    private CheckBox bankCardCheckbox;
    private CheckBox mobilePhoneCheckbox;
    private CheckBox cashAddressCheckbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews(){
        inputMoney = findViewById(R.id.inputMoney);
        inputInfo = findViewById(R.id.inputInfo);
        buttonOk = findViewById(R.id.buttonOk);
        bankCardCheckbox = findViewById(R.id.bankCardCheckbox);
        mobilePhoneCheckbox = findViewById(R.id.mobilePhoneCheckbox);
        cashAddressCheckbox = findViewById(R.id.cashAddressCheckbox);
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    switch (compoundButton.getId()) {
                        case R.id.bankCardCheckbox:
                            resetCheckBoxes();
                            bankCardCheckbox.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_NUMBER);
                            break;
                        case R.id.mobilePhoneCheckbox:
                            resetCheckBoxes();
                            mobilePhoneCheckbox.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_PHONE);
                            break;
                        case R.id.cashAddressCheckbox:
                            resetCheckBoxes();
                            cashAddressCheckbox.setChecked(true);
                            inputInfo.setInputType(InputType.TYPE_CLASS_TEXT);
                            break;
                        default:
                    }
                }
            }
        };
        bankCardCheckbox.setOnCheckedChangeListener(checkedChangeListener);
        mobilePhoneCheckbox.setOnCheckedChangeListener(checkedChangeListener);
        cashAddressCheckbox.setOnCheckedChangeListener(checkedChangeListener);



        buttonOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String inputMoneyValue = inputMoney.getText().toString();
                String inputInfoValue = inputInfo.getText().toString();
                String paymentType = checkedBox();

                StringBuilder sb = new StringBuilder();
                sb.append("Сумма: " + inputMoneyValue).append("Инфо: " + inputInfoValue)
                        .append("Тип оплаты: " + paymentType);

                Toast.makeText(MainActivity.this, sb, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetCheckBoxes(){
        bankCardCheckbox.setChecked(false);
        mobilePhoneCheckbox.setChecked(false);
        cashAddressCheckbox.setChecked(false);
    }

    private String checkedBox(){
        if (bankCardCheckbox.isChecked()){
            return "Оплата с банковской карты";
        } else if (mobilePhoneCheckbox.isChecked()){
            return "Оплата с мобильного телефона";
        } else {
            return "Оплата наличными на месте";
        }
    }
}
