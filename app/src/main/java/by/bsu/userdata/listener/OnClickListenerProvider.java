package by.bsu.userdata.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.function.Supplier;

import by.bsu.userdata.constant.EditFormName;
import by.bsu.userdata.constant.FieldData;
import by.bsu.userdata.ui.dialog.ConfirmationDialogFragment;

public class OnClickListenerProvider {
    private final static OnClickListenerProvider INSTANCE = new OnClickListenerProvider();

    public static OnClickListenerProvider getInstance() {
        return INSTANCE;
    }

    public View.OnClickListener cancelButtonListener(AppCompatActivity activity) {
        View.OnClickListener listener = view -> {
            DialogFragment dialogFragment = new ConfirmationDialogFragment();
            dialogFragment.show(activity.getSupportFragmentManager(), "ConfirmationDialogFragment");
        };
        return  listener;
    }

    public View.OnClickListener saveButtonListener(EditFormName formName, AppCompatActivity activity, Supplier<String> fieldContent) {
        View.OnClickListener listener = view -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(FieldData.FIELD_NAME.name(), formName.toString());
            returnIntent.putExtra(FieldData.FIELD_CONTENT.name(), fieldContent.get());
            activity.setResult(Activity.RESULT_OK, returnIntent);
            activity.finish();
        };
        return  listener;
    }

    public View.OnClickListener editButtonListener(AppCompatActivity src, EditFormName formName, ActivityResultLauncher<Intent> launcher) {
        View.OnClickListener listener = view -> {
            Intent intent = new Intent(src, formName.getActivityClass());
            launcher.launch(intent);
        };
        return  listener;
    }

    private OnClickListenerProvider() {}
}