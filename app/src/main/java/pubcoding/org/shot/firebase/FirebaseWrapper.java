package pubcoding.org.shot;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseWrapper {

    private static FirebaseWrapper instance;
    private DatabaseReference firebase;


    private FirebaseWrapper() {
        this.firebase = FirebaseDatabase.getInstance().getReference();
    }

    public static FirebaseWrapper getFirebase() {
        if (instance == null)
            instance = new FirebaseWrapper();
        return instance;
    }

    public void updateChild(@NonNull String child, @Nullable Object value,
                            @NonNull OnCompleteListener<Void> completionHandler) {
        this.firebase
                .child(child)
                .setValue(value)
                .addOnCompleteListener(completionHandler);
    }

}
