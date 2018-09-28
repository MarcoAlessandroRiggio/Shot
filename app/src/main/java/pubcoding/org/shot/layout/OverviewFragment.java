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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;
import java.util.Objects;

import pubcoding.org.shot.R;
import pubcoding.org.shot.custom.SummaryAdapter;
import pubcoding.org.shot.model.Message;
import pubcoding.org.shot.model.Shotter;

public class OverviewFragment extends Fragment {

    private DatabaseReference firebase;
    private GoogleSignInAccount account;
    private SummaryAdapter summaryAdapter;
    private TextView userRecord;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.overview_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.firebase = FirebaseDatabase.getInstance().getReference();
        this.account = GoogleSignIn.getLastSignedInAccount(view.getContext());
        this.summaryAdapter = new SummaryAdapter(view.getContext());
        this.userRecord = view.findViewById(R.id.UserRecord);
        initializeSnapshotCard(view);
        initializeButtons();
    }

    private void initializeSnapshotCard(@NonNull View view) {
        initializeComplexSummary();
        addDatabaseListener();
        final ListView summaryList = view.findViewById(R.id.SummaryList);
        summaryList.setAdapter(this.summaryAdapter);
    }

    private void initializeComplexSummary() {
        this.firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.getChildren().forEach(mutation -> {
                        Map<String, Object> properties = (Map<String, Object>) mutation.getValue();
                        summaryAdapter.addItem(new Message(Objects.requireNonNull(properties)));
                    });
                    setRecord();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void setRecord() {
        this.userRecord.setText(String.valueOf(getTotalShot()));
    }

    private long getTotalShot() {
        return summaryAdapter.getElements().stream().mapToLong(Shotter::getRecord).sum();
    }

    private void initializeButtons() {
        initializeAddButton();
        initializeRemoveButton();
        initializeSeagullButton();
    }

    private void addDatabaseListener() {
        this.firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataSnapshot.getChildren().forEach(mutation -> {
                        Map<String, Object> properties = (Map<String, Object>) mutation.getValue();
                        summaryAdapter.updateItem(new Message(Objects.requireNonNull(properties)));
                    });
                    setRecord();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void initializeSeagullButton() {
        final FloatingActionButton button = Objects.requireNonNull(getActivity())
                .findViewById(R.id.SeagullButton);
        button.setOnClickListener(view -> Toast.makeText(view.getContext(), R.string.SeagullMessage, Toast.LENGTH_SHORT)
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
                .setOnClickListener(view -> {
                    this.modifyShotterOnFirebase(view, 1, R.string.AddShotMessage);
                });
    }

    private void modifyShotterOnFirebase(View view, long variation, @StringRes int messageForUser) {
        final Shotter user = this.summaryAdapter.getItem(this.account.getGivenName());
        final Message message = new Message(this.account.getGivenName(), user.getRecord() + variation);
        this.firebase
                .child(Objects.requireNonNull(this.account.getGivenName()))
                .setValue(message.createMessageObj())
                .addOnCompleteListener(task -> Toast.makeText(view.getContext(), messageForUser, Toast.LENGTH_LONG)
                        .show());
    }

}
