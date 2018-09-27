package pubcoding.org.shot.custom;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import pubcoding.org.shot.model.Shotter;

public class ShotterList extends LinkedList<Shotter> {

    private List<Shotter> shotters;

    public ShotterList(@NonNull List<Shotter> shotters) {
        super(shotters);
        this.shotters = shotters;
    }

    public LinkedList<Shotter> getShotters() {
        final LinkedList<Shotter> formattedShotters = new LinkedList<>(this.shotters);
        formattedShotters.add(new Shotter("total",
                this.shotters.stream().mapToLong(Shotter::getRecord).sum()));
        return formattedShotters;
    }

}
