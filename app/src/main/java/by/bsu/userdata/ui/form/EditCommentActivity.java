package by.bsu.userdata.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.function.Supplier;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class EditCommentActivity extends AppCompatActivity {
    private TextInputEditText commentText;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_comment);

        commentText = this.findViewById(R.id.commentEditText);
        saveBtn = this.findViewById(R.id.editCommentSaveButton);
        cancelBtn = this.findViewById(R.id.editCommentCancelButton);
        Supplier<String> newFieldText = () -> commentText.getText().toString();
        OnClickListenerProvider provider = OnClickListenerProvider.getInstance();
        cancelBtn.setOnClickListener(provider.cancelButtonListener(this));
        saveBtn.setOnClickListener(provider.saveButtonListener(EditFormName.COMMENT, // TODO возможно лишенее поле (можно извлечь имя активити из this)
                this, newFieldText));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cancelOption) {
            cancelBtn.callOnClick();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}