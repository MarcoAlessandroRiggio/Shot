package pubcoding.org.shot.custom;

import android.content.Context;
import android.content.res.Resources;
import android.media.UnsupportedSchemeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import pubcoding.org.shot.R;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class SummaryAdapter extends BaseAdapter {

    private List<Shotter> data;
    private static LayoutInflater inflater = null;

    public SummaryAdapter(Context context) {
        super();
        this.data = new LinkedList<>();
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return new UnsupportedSchemeException("Problema tuo");
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Shotter getItem(String name) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(name))
                return this.data.get(i);
        } throw new Resources.NotFoundException();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.summary_row, null);
        TextView text = vi.findViewById(R.id.header);
        text.setText(data.get(position).getName());
        text = vi.findViewById(R.id.text);
        text.setText(String.valueOf(data.get(position).getRecord()));
        return vi;
    }

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
}
