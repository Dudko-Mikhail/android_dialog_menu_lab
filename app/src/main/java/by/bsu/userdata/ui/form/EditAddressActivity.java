package by.bsu.userdata.form;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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