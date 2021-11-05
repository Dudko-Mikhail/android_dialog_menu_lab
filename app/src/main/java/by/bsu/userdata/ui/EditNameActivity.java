package by.bsu.userdata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.StringJoiner;
import java.util.function.Supplier;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class EditNameActivity extends AppCompatActivity {

    private TextInputEditText nameText;
    private TextInputEditText surnameText;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        nameText = this.findViewById(R.id.nameEditText);
        surnameText = this.findViewById(R.id.surnameEditText);
        saveBtn = this.findViewById(R.id.editNameSaveButton);
        cancelBtn = this.findViewById(R.id.editNameCancelButton);

        Supplier<String> newFieldText = () -> {
            StringJoiner joiner = new StringJoiner(" ");
            joiner.add(nameText.getText());
            joiner.add(surnameText.getText());
            return joiner.toString();
        };

        OnClickListenerProvider provider = OnClickListenerProvider.getInstance();
        cancelBtn.setOnClickListener(provider.cancelButtonListener(this));
        saveBtn.setOnClickListener(provider.saveButtonListener(EditFormName.NAME, // TODO возможно лишенее поле (можно извлечь имя активити из this)
                this, newFieldText));
    }
}