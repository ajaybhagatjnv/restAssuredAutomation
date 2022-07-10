package CommonUtil;

import CommonUtil.FilePath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {


    public static Properties properties;
    private ObjectMapper objectMapper;

    public BaseClass() {
        properties = readPropertiesFile(FilePath.applicationProperty);
        objectMapper = new ObjectMapper();
    }

    public static Properties readPropertiesFile(String fileName){
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return prop;
    }
    public <T> T jsonToModelObject(Class className, JSONObject jsonObject) throws IOException {
        return (T) objectMapper.readValue(jsonObject.toString(), className);
    }
}
