package pubcoding.org.shot.layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import pubcoding.org.shot.R;
import pubcoding.org.shot.custom.ShotterList;
import pubcoding.org.shot.custom.SummaryAdapter;
import pubcoding.org.shot.model.Shotter;

public class OverviewFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeAddButton();
        initializeRemoveButton();
        initializeSeagullButton();
        ListView mylist = view.findViewById(R.id.SummaryList);
        mylist.setAdapter(new SummaryAdapter(view.getContext(), getShotter()));
    }

    private List<Shotter> getShotter() {
        return new ShotterList(Arrays.asList(new Shotter("Daniele", 1),
                new Shotter("Davide", 2), new Shotter("Marco", 3)))
                .getShotters();
    }

    private void initializeSeagullButton() {
        FloatingActionButton button = Objects.requireNonNull(getActivity())
                .findViewById(R.id.SeagullButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.SeagullMessage, Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }

    private void initializeRemoveButton() {
        FloatingActionButton button = Objects.requireNonNull(getActivity())
                .findViewById(R.id.RemoveShotButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.RemoveShotMessage, Toast.LENGTH_SHORT)
                        .show();

            }
        });
    }

    private void initializeAddButton() {
        FloatingActionButton button = Objects.requireNonNull(getActivity())
                .findViewById(R.id.AddShotButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), R.string.AddShotMessage, Toast.LENGTH_LONG)
                        .show();

            }
        });
    }

}
