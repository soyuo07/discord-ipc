package meteordevelopment.discordipc;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonObject;

public class RichPresence {
    private String details;
    private String state;

    private Assets assets;
    private Timestamps timestamps;
    private List<Button> buttons;

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void addButton(String label, String url) {
        if (buttons == null) buttons = new ArrayList<>();
        buttons.add(new Button(label, url));
    }

    public JsonObject toJson() {
        JsonObject o = new JsonObject();

        if (details != null) o.addProperty("details", details);
        if (state != null) o.addProperty("state", state);

        // Assets
        if (assets != null) {
            JsonObject a = new JsonObject();
            if (assets.large_image != null) a.addProperty("large_image", assets.large_image);
            if (assets.large_text != null) a.addProperty("large_text", assets.large_text);
            if (assets.small_image != null) a.addProperty("small_image", assets.small_image);
            if (assets.small_text != null) a.addProperty("small_text", assets.small_text);
            o.add("assets", a);
        }

        // Timestamps
        if (timestamps != null) {
            JsonObject t = new JsonObject();
            if (timestamps.start != null) t.addProperty("start", timestamps.start);
            if (timestamps.end != null) t.addProperty("end", timestamps.end);
            o.add("timestamps", t);
        }

        // Buttons
        if (buttons != null && !buttons.isEmpty()) {
            com.google.gson.JsonArray arr = new com.google.gson.JsonArray();
            for (Button b : buttons) {
                JsonObject btn = new JsonObject();
                btn.addProperty("label", b.label);
                btn.addProperty("url", b.url);
                arr.add(btn);
            }
            o.add("buttons", arr);
        }

        return o;
    }

    public static class Assets {
        public String large_image, large_text;
        public String small_image, small_text;
    }

    public static class Timestamps {
        public Long start;
        public Long end;
    }

    public static class Button {
        public String label;
        public String url;

        public Button(String label, String url) {
            this.label = label;
            this.url = url;
        }
    }
}
