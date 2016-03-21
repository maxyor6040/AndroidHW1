package com.hw1.hw1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private CheckBox checkBox;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar_num_of_sheep);
        textView = (TextView) findViewById(R.id.editText_num_of_sheep);
        checkBox = (CheckBox) findViewById(R.id.with_food_checkbox);
        button = (Button) findViewById(R.id.make_order_button);

        textView.setText(String.valueOf(0));
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress = 0;

                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progressValue, boolean fromUser) {
                        progress = progressValue;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textView.setText(String.valueOf(progress));
                        button.setEnabled(checkBox.isChecked() && progress > 0);
                    }
                });
        textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int progress = 0;
                try {
                    progress = Integer.valueOf(String.valueOf(s));
                    if (progress > 100) {
                        progress = 100;
                    }
                    if (progress < 0) {
                        progress = 0;
                    }
                } catch (NumberFormatException e) {

                }
                seekBar.setProgress(progress);
                button.setEnabled(checkBox.isChecked() && progress > 0);
            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int progress = 0;
                try {
                    progress = Integer.valueOf(String.valueOf(textView.getText()));
                } catch (NumberFormatException e) {

                }
                button.setEnabled(isChecked && progress > 0);
            }
        });
    }

    public void click_handler(View view) {
        Toast.makeText(getApplicationContext(),"Order sent", Toast.LENGTH_SHORT).show();
    }
}
