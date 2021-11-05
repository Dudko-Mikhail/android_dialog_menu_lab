package by.bsu.userdata.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import java.util.function.Supplier;

import by.bsu.userdata.constant.EditFormName;

public class OnClickListenerProvider {
    private final static OnClickListenerProvider INSTANCE = new OnClickListenerProvider();

    public static OnClickListenerProvider getInstance() {
        return INSTANCE;
    }

    public View.OnClickListener cancelButtonListener(AppCompatActivity activity) {
        View.OnClickListener listener = view -> {
            Intent returnIntent = new Intent();
            activity.setResult(Activity.RESULT_CANCELED, returnIntent);
            activity.finish();
        };
        return  listener;
    }

    public View.OnClickListener saveButtonListener(EditFormName formName, AppCompatActivity activity, Supplier<String> newFieldText) {
        View.OnClickListener listener = view -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("EditFormName", formName.toString());
            returnIntent.putExtra("newFieldText", newFieldText.get());
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
