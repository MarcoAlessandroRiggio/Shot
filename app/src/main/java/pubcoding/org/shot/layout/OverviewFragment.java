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

import java.util.Map;
import java.util.Objects;

import pubcoding.org.shot.R;
import pubcoding.org.shot.custom.ShotterAdapter;
import pubcoding.org.shot.custom.ShotterSnapshot;
import pubcoding.org.shot.firebase.FirebaseWrapper;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class OverviewFragment extends Fragment {

    private FirebaseWrapper firebaseWrapper;
    private GoogleSignInAccount account;
    private ShotterAdapter shotterAdapter;
    private TextView userRecord;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.firebaseWrapper = FirebaseWrapper.getFirebase();
        this.account = GoogleSignIn.getLastSignedInAccount(view.getContext());
        this.shotterAdapter = new ShotterSnapshot(view.getContext());
        this.userRecord = view.findViewById(R.id.UserRecord);
        initializeSnapshotCard(view);
        initializeButtons();
    }

    private void initializeSnapshotCard(@NonNull View view) {
        initializeComplexSummary();
        addDatabaseListener();
        final ListView summaryList = view.findViewById(R.id.SummaryList);
        summaryList.setAdapter(this.shotterAdapter);
    }

    private void initializeComplexSummary() {
        this.firebaseWrapper.addListenerForInitialStatus(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                dataSnapshot.getChildren().forEach(snapshot -> {
                    Map<String, Object> properties = (Map<String, Object>) snapshot.getValue();
                    shotterAdapter.addItem(new Message(Objects.requireNonNull(properties)));
                });
                setRecord();
            }
        });
    }

    private void setRecord() {
        this.userRecord.setText(String.valueOf(getTotalShot()));
    }

    private long getTotalShot() {
        return shotterAdapter.getElements().stream().mapToLong(Shotter::getRecord).sum();
    }

    private void initializeButtons() {
        initializeAddButton();
        initializeRemoveButton();
        initializeSeagullButton();
    }

    private void addDatabaseListener() {
        this.firebaseWrapper.addDataChangeListner(dataSnapshot -> {
            if (dataSnapshot.exists()) {
                dataSnapshot.getChildren().forEach(mutation -> {
                    Map<String, Object> properties = (Map<String, Object>) mutation.getValue();
                    shotterAdapter.updateItem(new Message(Objects.requireNonNull(properties)));
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
        final Shotter user = this.shotterAdapter.getItem(this.account.getGivenName());
        final Message message = new Message(user);
        this.firebaseWrapper.updateChild(
                Objects.requireNonNull(this.account.getGivenName()),
                message.createMessageObj(variation, messageForUser),
                task -> Toast.makeText(view.getContext(), messageForUser, Toast.LENGTH_SHORT)
                        .show()
        );
    }

}
