package pubcoding.org.shot.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseWrapper {

    private static FirebaseWrapper instance;
    private final DatabaseReference database;

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

    public void addListenerForInitialStatus(@NonNull final DataChangeListener dataChangeListener) {
        this.database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataChangeListener.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logError(1);
            }
        });
    }

    public void addDataChangeListener(@NonNull final DataChangeListener dataChangeListener) {
        this.database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataChangeListener.onDataChange(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                logError(2);
            }
        });
    }

    private void logError(int i) {
        Log.println(Log.ERROR, "Very bad error", "Sculo");
        System.exit(-i);
    }

}
