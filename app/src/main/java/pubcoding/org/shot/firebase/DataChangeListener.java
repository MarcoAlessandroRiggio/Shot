package pubcoding.org.shot.firebase;

import android.support.annotation.NonNull;

import com.google.firebase.annotations.PublicApi;
import com.google.firebase.database.DataSnapshot;

public interface DataChangeListener {
    @PublicApi
    void onDataChange(@NonNull DataSnapshot var1);
}
