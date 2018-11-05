//package ru.digitaluniversity.serializer;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import ru.digitaluniversity.dto.JournalDto;
//
//import java.io.IOException;
//
//public class JsonSerializer {
//
//    public static String toJSON(Object object) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter objectWriter = objectMapper.writer().withDefaultPrettyPrinter();
//        String result = objectWriter.writeValueAsString(object);
//        return result;
//    }
//
//    public static Object fromJSON(String jsonString, Class<?> neededClass) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Object object = objectMapper.readValue(jsonString, neededClass);
//        return object;
//    }
//}
