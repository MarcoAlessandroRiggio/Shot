package pubcoding.org.shot.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AbstractAdapter<T> extends BaseAdapter {

    private List<T> elements;
    private final LayoutInflater inflater;

    AbstractAdapter(@NonNull Context context, List<T> elements) {
        super();
        this.elements = elements;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public List<T> getElements() { return this.elements; }

    public void setElements(List<T> elements) { this.elements = elements; }

    LayoutInflater getInflater() { return this.inflater; }

    @Override
    public int getCount() { return this.elements.size(); }

    @Override
    public Object getItem(int position) { return this.elements.get(position); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void addItem(T item) {
        this.elements.add(item);
        notifyDataSetChanged();
    }

}
