package pubcoding.org.shot.layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

import pubcoding.org.shot.R;
import pubcoding.org.shot.custom.LogAdapter;
import pubcoding.org.shot.custom.SpinnerAdapter;
import pubcoding.org.shot.firebase.FirebaseWrapper;
import pubcoding.org.shot.model.Log;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class LogFragment extends Fragment {

    private FirebaseWrapper database;
    private Spinner shotterSpinner;
    private SpinnerAdapter spinnerAdapter;
    private LogAdapter logAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.database = FirebaseWrapper.getFirebase();
        this.shotterSpinner = view.findViewById(R.id.UserSpinner);
        this.spinnerAdapter = new SpinnerAdapter(view.getContext());
        this.shotterSpinner.setAdapter(this.spinnerAdapter);
        this.logAdapter = new LogAdapter(view.getContext());
        ListView logView = view.findViewById(R.id.LogList);
        logView.setAdapter(logAdapter);
        initializeSpinner();
        setSpinnerAction();
    }

    private void setSpinnerAction() {
        this.shotterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Shotter item = (Shotter) spinnerAdapter.getItem(position);
                List<Log> logs = item.getLog();
                if (logs != null) {
                    logAdapter.setElements(logs);
                }
                logAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(parent.getContext(), R.string.error_message, Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    private void initializeSpinner() {
        this.database.addListenerForInitialStatus(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                dataSnapshot.getChildren().forEach(snapshot -> {
                    Message message = snapshot.getValue(Message.class);
                    spinnerAdapter.addItem(Objects.requireNonNull(message));
                });
            }
        });
    }

}
