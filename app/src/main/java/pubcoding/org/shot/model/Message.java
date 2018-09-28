package pubcoding.org.shot.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Message {
    private static final String USER = "user";
    private static final String DATE = "date";
    private static final String VALUE = "value";

    private String user;
    private Long value;
    private Long date;

    public Message(String user, Long date, Long value) {
        this.user = user;
        this.date = date;
        this.value = value;
    }

    public Message(String user, Long value) {
        this.user = user;
        this.value = value;
    }
    public Message(Map<String, Object> properties) {
        this.user = (String) properties.get(USER);
        this.value = (Long) properties.get(VALUE);
    }

    public Map<String, Object> createMessageObj() {
        Map<String, Object> messageObj = new HashMap<>();
        messageObj.put(DATE, ServerValue.TIMESTAMP);
        messageObj.put(USER, this.user);
        messageObj.put(VALUE, this.value);

        return messageObj;
    }

    public String getUser() { return user; }

    public Long getValue() { return value; }
}
