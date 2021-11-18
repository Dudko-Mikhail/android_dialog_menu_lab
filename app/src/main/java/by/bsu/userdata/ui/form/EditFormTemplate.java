package by.bsu.userdata.ui.form;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import by.bsu.userdata.R;
import by.bsu.userdata.ui.dialog.ConfirmationDialogFragment;

public abstract class EditFormTemplate extends AppCompatActivity implements ConfirmationDialogFragment.OnPositiveClickListener {
    protected Button cancelBtn;
    protected Button saveBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_form_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.cancelOption) {
            DialogFragment dialogFragment = new ConfirmationDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "ConfirmationDialogFragment");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void positiveClick(DialogFragment fragment) {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
