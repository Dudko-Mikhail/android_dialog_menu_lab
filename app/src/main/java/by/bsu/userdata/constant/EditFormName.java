package by.bsu.userdata.constant;

import androidx.appcompat.app.AppCompatActivity;

import by.bsu.userdata.ui.form.EditAddressActivity;
import by.bsu.userdata.ui.form.EditCommentActivity;
import by.bsu.userdata.ui.form.EditNameActivity;

public enum EditFormName {
    ADDRESS(EditAddressActivity.class),
    NAME(EditNameActivity.class),
    COMMENT(EditCommentActivity.class);

    private final Class<? extends AppCompatActivity> activityClass;

    public Class<? extends AppCompatActivity> getActivityClass() {
        return activityClass;
    }

    EditFormName(Class<? extends AppCompatActivity> activity) {
        this.activityClass = activity;
    }
}
