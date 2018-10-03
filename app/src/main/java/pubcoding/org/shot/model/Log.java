package pubcoding.org.shot.model;

import android.support.annotation.StringRes;

import java.util.Date;

public class Log {

    private String date;
    private @StringRes int message;

    public Log() {}

    private Log(String date, @StringRes int message) {
        this.date = date;
        this.message = message;
    }

    static Log createNewLog(@StringRes int message) {
        return new Log(new Date().toString(), message);
    }

    public String getDate() {
        return date;
    }

    public @StringRes
    int getMessage() {
        return message;
    }

}
