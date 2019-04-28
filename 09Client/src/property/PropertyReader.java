package property;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {

    private static final Logger LOG = Logger.getLogger(PropertyReader.class.getName());

    private static Properties prop = null;

    public static String read(String nameId) {
        if(prop == null){
            prop = new Properties();
        }
        try {
            prop.load(new FileInputStream("D:\\FIIT\\4. semester\\VAVA\\VAsVA\\09Client\\etc\\config.properties"));
        } catch (IOException e) {
            LOG.severe("property file was not found");
        }
        return prop.getProperty(nameId);
    }
}

