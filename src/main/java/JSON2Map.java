import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JSON2Map {

    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream("src/main/resources/swagger.json");
        JSONObject jsonObject = JSON.parseObject(IOUtils.toString(is, "utf-8"), Feature.DisableCircularReferenceDetect);
//        System.out.println(jsonObject);
        Set<String> list = new HashSet<>();
        JSONObject paths = jsonObject.getJSONObject("paths");
        for (String path : paths.keySet()) {
            JSONObject api = paths.getJSONObject(path); // 对于当前接口URL
            for (String method : api.keySet()) {
                JSONObject responses = api.getJSONObject(method).getJSONObject("responses"); // 对于当前接口的响应，同名接口可能方法有多种
                for (String resp : responses.keySet()) {
                    if (responses.getJSONObject(resp).containsKey("schema")) { // 响应体schema字段定义了返回类型
                        for (Object value : responses.getJSONObject(resp).getJSONObject("schema").values()) {
                            if (!value.toString().startsWith("#/")) { // 返回类型为非引用类型
                                list.add(path + " + " + method);
                            }
                        }
                    }
                }
            }
        }
        list.forEach(System.out::println);
        System.out.println(list.size());
    }
}
