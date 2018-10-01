package pubcoding.org.shot.model;

import android.support.annotation.StringRes;

import java.util.Date;

import pubcoding.org.shot.custom.Nominable;

public class Log implements Nominable {

    private String date;
    private @StringRes
    int message;

    public Log() {}

    private Log(String date, @StringRes int message) {
        this.date = date;
        this.message = message;
    }

    static Log createNewLog(@StringRes int message) {
        return new Log(new Date().toGMTString(), message);
    }

    public String getDate() {
        return date;
    }

    public @StringRes
    int getMessage() {
        return message;
    }

    @Override
    public boolean equals(String date) { return this.date.equals(date); }
}
