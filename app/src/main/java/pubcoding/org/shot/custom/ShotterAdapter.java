package pubcoding.org.shot.custom;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public abstract class ShotterAdapter extends AbstractAdapter {

    ShotterAdapter(@NonNull Context context, List data) {
        super(context, data);
    }

    @Override
    public List<Shotter> getElements() {
        return super.getElements();
    }

    public void addItem(Message message) {
        Shotter shotter = new Shotter(message);
        addItem(shotter);
    }

}
