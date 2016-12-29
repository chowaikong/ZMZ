package me.knox.zmz.network;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * Created by KNOX.
 */

public class ResponseTypeAdapterFactory implements TypeAdapterFactory {

  private static final String STATUS = "status";
  private static final String DATA = "data";
  private static final int SUCCESS = 1;

  @Override public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
    final TypeAdapter<T> deleggateAdapter = gson.getDelegateAdapter(this, type);
    final TypeAdapter<JsonElement> jsonElementTypeAdapter = gson.getAdapter(JsonElement.class);
    return new TypeAdapter<T>() {
      @Override public void write(JsonWriter out, T value) throws IOException {
        deleggateAdapter.write(out, value);
      }

      @Override public T read(JsonReader in) throws IOException {
        JsonElement element = jsonElementTypeAdapter.read(in);
        if (element.isJsonObject()) {
          JsonObject jsonObject = element.getAsJsonObject();
          if (jsonObject.has(STATUS)) {
            int code = jsonObject.get(STATUS).getAsInt();
            if (code != SUCCESS) {
              jsonObject.remove(DATA);
            }
          }
        }
        return deleggateAdapter.fromJsonTree(element);
      }
    }.nullSafe();
  }
}
