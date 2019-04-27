package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties prop = null;

    public static String read(String nameId) {
        if(prop == null){
            prop = new Properties();
        }
        try {
            prop.load(new FileInputStream("D:\\FIIT\\4. semester\\VAVA\\VAsVA\\09Client\\etc\\config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(nameId);
    }
}

