package pubcoding.org.shot.layout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.Objects;

import pubcoding.org.shot.R;
import pubcoding.org.shot.adapter.SnapshotAdapter;
import pubcoding.org.shot.firebase.FirebaseWrapper;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class OverviewFragment extends Fragment {

    private FirebaseWrapper database;
    private GoogleSignInAccount account;
    private SnapshotAdapter snapshotAdapter;
    private TextView userRecord;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.database = FirebaseWrapper.getFirebase();
        this.account = GoogleSignIn.getLastSignedInAccount(view.getContext());
        this.snapshotAdapter = new SnapshotAdapter(view.getContext());
        this.userRecord = view.findViewById(R.id.UserRecord);
        initializeSnapshotCard(view);
        initializeButtons();
    }

    private void initializeSnapshotCard(@NonNull View view) {
        initializeComplexSummary();
        addDatabaseListener();
        final ListView summaryList = view.findViewById(R.id.SummaryList);
        summaryList.setAdapter(this.snapshotAdapter);
    }

    private void initializeComplexSummary() {
        this.database.addListenerForInitialStatus(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                dataSnapshot.getChildren().forEach(snapshot -> {
                    Message message = snapshot.getValue(Message.class);
                    snapshotAdapter.addItem(message);
                });
                setRecord();
            }
        });
    }

    private void setRecord() {
        this.userRecord.setText(String.valueOf(getTotalShot()));
    }

    private long getTotalShot() {
        return snapshotAdapter.getElements().stream().mapToLong(Shotter::getRecord).sum();
    }

    private void initializeButtons() {
        initializeAddButton();
        initializeRemoveButton();
        initializeSeagullButton();
    }

    private void addDatabaseListener() {
        this.database.addDataChangeListener(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                dataSnapshot.getChildren().forEach(mutation -> {
                    Message message = mutation.getValue(Message.class);
                    snapshotAdapter.updateItem(Objects.requireNonNull(message));
                });
                setRecord();
            }
        });
    }

    private void initializeSeagullButton() {
        final FloatingActionButton button = Objects.requireNonNull(getActivity())
                .findViewById(R.id.SeagullButton);
        button.setOnClickListener(view -> Toast.makeText(view.getContext(),
                R.string.SeagullMessage, Toast.LENGTH_SHORT)
                .show());
    }

    private void initializeRemoveButton() {
        Objects.requireNonNull(getActivity())
                .findViewById(R.id.RemoveShotButton)
                .setOnClickListener(view -> this.modifyShotterOnFirebase(view, -1, R.string.RemoveShotMessage));
    }

    private void initializeAddButton() {
        Objects.requireNonNull(getActivity())
                .findViewById(R.id.AddShotButton)
                .setOnClickListener(view -> this.modifyShotterOnFirebase(view, 1, R.string.AddShotMessage));
    }

    private void modifyShotterOnFirebase(View view, long variation, @StringRes int messageForUser) {
        final Shotter user = this.snapshotAdapter.getItem(this.account.getGivenName());
        final Message message = new Message(user);
        message.updateValue(variation, messageForUser);
        this.database.updateChild(
                Objects.requireNonNull(this.account.getGivenName()), message,
                task -> Toast.makeText(view.getContext(), messageForUser, Toast.LENGTH_SHORT)
                        .show()
        );
    }

}
