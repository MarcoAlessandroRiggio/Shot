package pubcoding.org.shot.custom;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T extends Nominable> extends BaseAdapter {

    private List<T> data;
    private static LayoutInflater inflater = null;

    AbstractAdapter(@NonNull Context context, List<T> data) {
        super();
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<T> getElements() { return data; }

    LayoutInflater getInflater() { return inflater; }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public Object getItem(int position) { return this.data.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public T getItem(String name) {
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).equals(name))
                return data.get(i);
        throw new Resources.NotFoundException();
    }

    public void addItem(T item) {
        data.add(item);
        notifyDataSetChanged();
    }
}
