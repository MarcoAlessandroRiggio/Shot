package pubcoding.org.shot.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseWrapper {

    private static FirebaseWrapper instance;
    private DatabaseReference database;

    private FirebaseWrapper() {
        this.database = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseWrapper getFirebase() {
        if (instance == null)
            instance = new FirebaseWrapper();
        return instance;
    }

    public void updateChild(@NonNull String child, @Nullable Object value,
                            @NonNull OnCompleteListener<Void> completionHandler) {
        this.database
                .child(child)
                .setValue(value)
                .addOnCompleteListener(completionHandler);
    }

    public void addListenerForInitialStatus(@NonNull final DataChangeListner dataChangeListner) {
        this.database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataChangeListner.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO
            }
        });
    }

    public void addDataChangeListner(@NonNull final DataChangeListner dataChangeListner) {
        this.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataChangeListner.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // TODO
            }
        });
    }

}
