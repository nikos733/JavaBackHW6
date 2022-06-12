package Ivin.HW6;

import com.fasterxml.jackson.databind.ObjectMapper;
import Ivin.HW5.DTO.CatResponseGET;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Utils {

    @Test
    void test() throws IOException {
        CatResponseGET catResponseGET = new CatResponseGET();
        catResponseGET.setId(1);
        catResponseGET.setTitle("myTitle");
        StringWriter writer = new StringWriter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, catResponseGET);
        String result = writer.toString();
        System.out.println(result);
        StringReader reader = new StringReader("{\"id\":1,\"title\":\"myTitle\",\"products\":[]}");
        CatResponseGET catResponseGETReader = mapper.readValue(reader, CatResponseGET.class);
    }
}
