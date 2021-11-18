package by.bsu.userdata.ui.form;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import java.util.function.Supplier;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class EditCommentActivity extends EditFormTemplate {
    private TextInputEditText commentText;

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
        saveBtn.setOnClickListener(provider.saveButtonListener(EditFormName.COMMENT,
                this, newFieldText));
    }
}