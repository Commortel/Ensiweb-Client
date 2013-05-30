package ensiweb.client.utils;

import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

class KfetJSONObject extends JSONObject {

    public KfetJSONObject() {
        super();
    }

    public KfetJSONObject(JSONObject obj) {
        super(obj);
    }

    public double getDouble(String name) {
        try {
            if (this.get(name) != null) {
                return Double.parseDouble(this.get(name).toString());
            }
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public int getInt(String name) {
        try {
            if (this.get(name) != null) {
                return Integer.parseInt(this.get(name).toString());
            }
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public String getString(String name) {
        if (this.get(name) != null) {
            return (String) this.get(name);
        }
        return "";
    }

    public JSONArray getArray(String name) {
        return (JSONArray) this.get(name);
    }

    public boolean has(String name) {
        return this.get(name) != null;
    }

    static KfetJSONObject iteratorNext(Iterator<JSONObject> iterator) {
        return new KfetJSONObject(iterator.next());
    }
}
