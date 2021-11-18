package by.bsu.userdata.ui.form;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class MainActivity extends AppCompatActivity {
    private TextView nameText;
    private TextView addressText;
    private TextView commentText;
    private Button nameEditBtn;
    private Button addressEditBtn;
    private Button commentEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = this.findViewById(R.id.nameTextView);
        addressText = this.findViewById(R.id.addressTextView);
        commentText = this.findViewById(R.id.commentTextView);
        nameEditBtn = this.findViewById(R.id.nameEditButton);
        addressEditBtn = this.findViewById(R.id.addressEditButton);
        commentEditBtn = this.findViewById(R.id.commentEditButton);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        EditFormName formName = EditFormName.valueOf(data.getStringExtra("EditFormName"));
                        String newFieldText = data.getStringExtra("newFieldText");
                        switch (formName) {
                            case ADDRESS: {
                                addressText.setText(newFieldText);
                                break;
                            }
                            case NAME: {
                                nameText.setText(newFieldText);
                                break;
                            }
                            case COMMENT: {
                                commentText.setText(newFieldText);
                                break;
                            }
                        }
                    }
                });

        OnClickListenerProvider provider = OnClickListenerProvider.getInstance();
        nameEditBtn.setOnClickListener(provider.editButtonListener(this, EditFormName.NAME, activityResultLauncher));
        addressEditBtn.setOnClickListener(provider.editButtonListener(this, EditFormName.ADDRESS, activityResultLauncher));
        commentEditBtn.setOnClickListener(provider.editButtonListener(this, EditFormName.COMMENT, activityResultLauncher));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}