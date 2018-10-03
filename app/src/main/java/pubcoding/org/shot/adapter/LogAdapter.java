package pubcoding.org.shot.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import pubcoding.org.shot.R;
import pubcoding.org.shot.model.Log;

public class LogAdapter extends AbstractAdapter<Log> {

    private final Context context;

    public LogAdapter(Context context) {
        super(context, new LinkedList<>());
        this.context = context;
    }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = getInflater().inflate(R.layout.log_row, parent, false);
        TextView text = vi.findViewById(R.id.header);
        List<Log> log = getElements();
        text.setText(log.get(position).getDate());
        text = vi.findViewById(R.id.text);
        text.setText(this.context.getText(log.get(position).getMessage()));
        return vi;
    }

}
