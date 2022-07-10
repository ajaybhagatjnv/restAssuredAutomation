package CommonUtil;
import java.io.*;

public class JSONUtility {


    public String returnJsonAsString(String jsonPath)
    {
        InputStream fis ;
        BufferedInputStream bis;
        DataInputStream dis ;
        String jsonData = "";
        try {
            File initialFile = new File(System.getProperty("user.dir") + jsonPath);
            fis = new FileInputStream(initialFile);

            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            while (dis.available() != 0) {
                jsonData = jsonData + dis.readLine().trim();
            }
            dis.close();
            bis.close();
            fis.close();
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
        return jsonData;
    }
}
