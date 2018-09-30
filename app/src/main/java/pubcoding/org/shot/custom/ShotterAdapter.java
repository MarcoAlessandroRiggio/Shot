package pubcoding.org.shot.custom;

import android.content.Context;
import android.content.res.Resources;
import android.media.UnsupportedSchemeException;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;

import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public abstract class SummaryAdapter extends BaseAdapter {
    private static final String SCULO = "Sculo.";

    private List<Shotter> data;
    private static LayoutInflater inflater = null;

    SummaryAdapter(Context context) {
        super();
        this.data = new LinkedList<>();
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return new UnsupportedSchemeException(SCULO);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Shotter getItem(String name) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(name))
                return this.data.get(i);
        }
        throw new Resources.NotFoundException();
    }

    LayoutInflater getInflater() { return inflater; }

    public void updateItem(Message message) {
        final Shotter updatedShotter = new Shotter(message);
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(updatedShotter.getName())) {
                this.data.set(i, updatedShotter);
                notifyDataSetChanged();
            }
        }
    }

    public void addItem(Message message) {
        final Shotter shotter = new Shotter(message);
        this.data.add(shotter);
        notifyDataSetChanged();
    }

    public List<Shotter> getElements() {
        return this.data;
    }
}
