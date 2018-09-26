package pubcoding.org.shot.custom;

import android.content.Context;
import android.media.UnsupportedSchemeException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import pubcoding.org.shot.R;
import pubcoding.org.shot.model.Shotter;

public class SummaryAdapter extends BaseAdapter {

    private Context context;
    private List<Shotter> data;
    private static LayoutInflater inflater = null;

    public SummaryAdapter(Context context, List<Shotter> data) {
        this.context = context;
        this.data = data;
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
}
