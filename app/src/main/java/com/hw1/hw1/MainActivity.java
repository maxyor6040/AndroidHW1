package com.hw1.hw1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Toolbar myToolbar;
    private Menu myMenu;
    static final int PICK_FOOD_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar) findViewById(R.id.seekBar_num_of_sheep);
        textView = (TextView) findViewById(R.id.editText_num_of_sheep);
        checkBox = (CheckBox) findViewById(R.id.with_food_checkbox);
        button = (Button) findViewById(R.id.make_order_button);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);

        textView.setText(String.valueOf(0));
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar,
                                                  int progressValue, boolean fromUser) {
                        if (!fromUser)
                            return;
                        textView.setText(String.valueOf(progressValue));
                        Boolean flag = checkBox.isChecked() && progressValue > 0;
                        button.setEnabled(flag);
                        if (myMenu != null) {
                            myMenu.findItem(R.id.make_order_item).setEnabled(flag);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
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
                Boolean flag = checkBox.isChecked() && progress > 0;
                button.setEnabled(flag);
                if (myMenu != null) {
                    myMenu.findItem(R.id.make_order_item).setEnabled(flag);
                }
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
                Boolean flag = isChecked && progress > 0;
                button.setEnabled(flag);
                if (myMenu != null) {
                    myMenu.findItem(R.id.make_order_item).setEnabled(flag);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        myMenu = menu;

        int progress = 0;
        try {
            progress = Integer.valueOf(String.valueOf(textView.getText()));
        } catch (NumberFormatException e) {

        }
        Boolean flag = checkBox.isChecked() && progress > 0;
        button.setEnabled(flag);
        if (myMenu != null) {
            myMenu.findItem(R.id.make_order_item).setEnabled(flag);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.make_order_item:
                Intent intent = new Intent(this, OrderSentActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == PICK_FOOD_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.
                Toast.makeText(getApplicationContext(), data.getStringExtra("FOOD"), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void click_handler(View view) {
        Intent intent = new Intent(this, OrderSentActivity.class);
        startActivity(intent);
    }

    public void yet_another_click_handler(View view) {
        Intent intent = new Intent(this, FoodMenuActivity.class);
        startActivityForResult(intent, PICK_FOOD_REQUEST);
    }
}
