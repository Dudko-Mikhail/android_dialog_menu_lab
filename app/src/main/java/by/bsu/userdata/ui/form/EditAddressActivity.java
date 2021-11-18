package by.bsu.userdata.ui.form;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import java.util.StringJoiner;
import java.util.function.Supplier;

import by.bsu.userdata.R;
import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.listener.OnClickListenerProvider;

public class EditAddressActivity extends EditFormTemplate {
    private TextInputEditText cityText;
    private TextInputEditText countryText;
    private TextInputEditText addressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        cityText = this.findViewById(R.id.cityEditText);
        countryText = this.findViewById(R.id.countryEditText);
        addressText = this.findViewById(R.id.addressEditText);
        saveBtn = this.findViewById(R.id.editAddressSaveButton);
        cancelBtn = this.findViewById(R.id.editAddressCancelButton);

        Supplier<String> newFieldText = () -> {
            StringJoiner joiner = new StringJoiner(", ");
            joiner.add(countryText.getText());
            joiner.add(cityText.getText());
            joiner.add(addressText.getText());
            return joiner.toString();
        };
        OnClickListenerProvider provider = OnClickListenerProvider.getInstance();
        cancelBtn.setOnClickListener(provider.cancelButtonListener(this));
        saveBtn.setOnClickListener(provider.saveButtonListener(EditFormName.ADDRESS,
                this, newFieldText));
    }
}