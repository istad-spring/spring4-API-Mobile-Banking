package co.istad.s4mbanking.util;

import org.springframework.stereotype.Component;

@Component
public class FileUtil {

    public String getExtension(String name) {
        int dotLastIndex = name.lastIndexOf(".");
        return name.substring(dotLastIndex + 1);
    }

}
