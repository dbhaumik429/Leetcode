package systemdesign;

import java.util.HashMap;
import java.util.Map;

public class Logger {


    /**
     * Approach 1: uses hashmap
     * this one uses a lot of memory
     */

    private final Map<String, Integer> messagesExpiry;

    public Logger() {
        this.messagesExpiry = new HashMap<>();
    }

    public static void main(String[] args) {

        Logger obj = new Logger();
        System.out.println(obj.shouldPrintMessage(1, "Hi"));

    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (messagesExpiry.containsKey(message)) {

            var expiry = messagesExpiry.get(message);
            if (timestamp < expiry)
                return false;
        }

        messagesExpiry.put(message, timestamp + 10);
        return true;

    }


}
