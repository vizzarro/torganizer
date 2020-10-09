package it.vizzarro.torganizer.utils.jsend.utils;

import com.google.common.reflect.TypeToken;

import java.util.List;

public class JSendUtils {

    public static <T> TypeToken<List<T>> listType() {
        return new TypeToken<List<T>>() {};
    }

}
