package pubcoding.org.shot.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pubcoding.org.shot.R;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class SnapshotAdapter extends ShotterAdapter {

    public SnapshotAdapter(Context context) { super(context); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = getInflater().inflate(R.layout.summary_row, null);
        TextView text = vi.findViewById(R.id.header);
        text.setText(getElements().get(position).getName());
        text = vi.findViewById(R.id.text);
        text.setText(String.valueOf(getElements().get(position).getRecord()));
        return vi;
    }

    public void updateItem(Message message) {
        final Shotter updatedShotter = new Shotter(message);
        final List<Shotter> elements = getElements();
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).getName().equals(updatedShotter.getName())) {
                elements.set(i, updatedShotter);
                notifyDataSetChanged();
            }
        }
    }

}
