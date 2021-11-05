package by.bsu.userdata.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import java.util.StringJoiner;
import java.util.function.Supplier;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class EditAddressActivity extends AppCompatActivity {
    private TextInputEditText cityText;
    private TextInputEditText countryText;
    private TextInputEditText addressText;
    private Button saveBtn;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // TODO add validation
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        cityText = this.findViewById(R.id.cityEditText);
        countryText = this.findViewById(R.id.countryEditText);
        addressText = this.findViewById(R.id.addressEditText);
        saveBtn = this.findViewById(R.id.editAddressSaveButton);
        cancelBtn = this.findViewById(R.id.editAddressCancelButton);

        Supplier<String> newFieldText = () -> {
            StringJoiner joiner = new StringJoiner(", ");
            joiner.add(cityText.getText());
            joiner.add(countryText.getText());
            joiner.add(addressText.getText());
            return joiner.toString();
        };
        OnClickListenerProvider provider = OnClickListenerProvider.getInstance();
        cancelBtn.setOnClickListener(provider.cancelButtonListener(this));
        saveBtn.setOnClickListener(provider.saveButtonListener(EditFormName.ADDRESS, // TODO возможно лишенее поле (можно извлечь имя активити из this)
                this, newFieldText));
    }
}