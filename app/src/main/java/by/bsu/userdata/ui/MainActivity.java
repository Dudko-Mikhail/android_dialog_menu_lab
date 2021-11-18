package by.bsu.userdata.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.constant.FieldData;
import by.bsu.userdata.listener.OnClickListenerProvider;
import by.bsu.userdata.ui.dialog.ConfirmationDialogFragment;

public class MainActivity extends AppCompatActivity implements ConfirmationDialogFragment.OnPositiveClickListener {
    private TextInputEditText nameText;
    private TextInputEditText addressText;
    private TextInputEditText commentText;
    private Button nameEditBtn;
    private Button addressEditBtn;
    private Button commentEditBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameText = this.findViewById(R.id.nameField);
        addressText = this.findViewById(R.id.addressField);
        commentText = this.findViewById(R.id.commentField);
        nameEditBtn = this.findViewById(R.id.nameEditButton);
        addressEditBtn = this.findViewById(R.id.addressEditButton);
        commentEditBtn = this.findViewById(R.id.commentEditButton);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        EditFormName formName = EditFormName.valueOf(data.getStringExtra(FieldData.FIELD_NAME.name()));
                        String fieldContent = data.getStringExtra(FieldData.FIELD_CONTENT.name());
                        switch (formName) {
                            case ADDRESS: {
                                addressText.setText(fieldContent);
                                break;
                            }
                            case NAME: {
                                nameText.setText(fieldContent);
                                break;
                            }
                            case COMMENT: {
                                commentText.setText(fieldContent);
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
        if (item.getItemId() == R.id.exitOption) {
            DialogFragment dialogFragment = new ConfirmationDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "ConfirmationDialogFragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void positiveClick(DialogFragment fragment) {
        finishAndRemoveTask();
    }
}