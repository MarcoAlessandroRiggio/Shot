package pubcoding.org.shot.custom;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public abstract class ShotterAdapter extends AbstractAdapter<Shotter> {

    ShotterAdapter(@NonNull Context context) { super(context, new LinkedList<>()); }

    public void addItem(Message message) {
        Shotter shotter = new Shotter(message);
        addItem(shotter);
    }

    public Shotter getItem(String name) {
        List<Shotter> elements = getElements();
        for (int i = 0; i < elements.size(); i++)
            if (elements.get(i).getName().equals(name))
                return elements.get(i);
        throw new Resources.NotFoundException();
    }
}
