package pubcoding.org.shot.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Message {

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
        this.user = (String) properties.get("user");
        this.value = (Long) properties.get("value");
    }

    public Map<String, Object> createMessageObj() {
        Map<String, Object> messageObj = new HashMap<>();
        messageObj.put("date", ServerValue.TIMESTAMP);
        messageObj.put("user", this.user);
        messageObj.put("value", this.value);

        return messageObj;
    }

    public String getUser() { return user; }

    public Long getValue() { return value; }
}
