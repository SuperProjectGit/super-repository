import com.common.io.BigFileInput;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * html 解析
 *
 * @author subo
 * @create 2018-03-06 18:11
 **/
public class HtmlTest extends BaseTest {

    @Test
    public void htmlTest() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        readData();
    }

    public static List<String> readData() throws Exception {
        List<String> mobiles = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream("F:\\user_data2.xlsx");
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; sheet.getRow(i) != null; i++) {
                XSSFRow row = sheet.getRow(i);
                if (row.getCell(7) != null && StringUtils.isNotBlank(row.getCell(7).toString())) {
                    System.out.println(row.getCell(1));
                    continue;
                }
                mobiles.add(row.getCell(1).toString());
                String location = getLocation(row.getCell(1).toString());
                BigFileInput.appendFile("F:\\temp.txt", row.getCell(1).toString() + "_" + location);
                row.createCell(7);
                row.getCell(7).setCellValue(location);
            }
            FileOutputStream fileOutputStream = new FileOutputStream("F:\\user_data3.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e ){
            e.printStackTrace();
        }
        return mobiles;
    }

    public static String getLocation(String mobile) {
        String location = "";
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("gb2312")));
            Map<String, String> map = new HashMap<>();
            String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=" + mobile;
            String response = restTemplate.getForObject(url, String.class, map);
            Document doc = Jsoup.parse(response);
            Elements rows = doc.select("tr[class=tdc]").get(2).select("td");
            Element row = rows.get(1);
            System.out.println("---------------------------------------");
            location = row.select("td").text();
            System.out.println(location);
        } catch (Exception e) {
            System.out.println("failure mobile: " + mobile);
        }
        return location;
    }

}
