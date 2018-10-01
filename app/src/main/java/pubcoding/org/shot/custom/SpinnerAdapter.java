package pubcoding.org.shot.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import pubcoding.org.shot.R;
import pubcoding.org.shot.model.Shotter;

public class SpinnerAdapter extends ShotterAdapter {


    public SpinnerAdapter(Context context) {
        super(context, new LinkedList<Shotter>());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = getInflater().inflate(R.layout.spinner_row, null);
        TextView text = vi.findViewById(R.id.text);
        text.setText(getElements().get(position).getName());
        return vi;
    }
}