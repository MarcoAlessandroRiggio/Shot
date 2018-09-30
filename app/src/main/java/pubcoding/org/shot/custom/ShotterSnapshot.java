package pubcoding.org.shot.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pubcoding.org.shot.R;

public class SummarySnapshot extends SummaryAdapter {

    public SummarySnapshot(Context context) {
        super(context);
    }

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

}
