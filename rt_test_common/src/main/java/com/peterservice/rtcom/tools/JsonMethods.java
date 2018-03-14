package com.peterservice.rtcom.tools;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.peterservice.rtcom.tools.ObjectMapperMethods.MAPPER;

/**
 * Методы работы с JSON
 */
public class JsonMethods {
    private final static Logger LOGGER = Logger.getLogger(JsonMethods.class);
    private static JsonMethods instance;
    private static final JSONParser JSON_PARSER = new JSONParser();

    public static synchronized JsonMethods inst() {
        if (instance == null) {
            instance = new JsonMethods();
        }
        return instance;
    }

    /**
     * Удаление элемента из json
     *
     * @param json - строка с json
     * @param element - элемент который требуется удалить
     * @return json c удаленным элементом
     */
    public String removeJsonElement(String json, String element) throws IOException {
        Objects.requireNonNull(json);
        Objects.requireNonNull(element);
        ObjectNode jsonNode = getObjectNode(json);
        jsonNode.findParent(element).remove(element);
        LOGGER.info("Remove " + element);
        return jsonNode.toString();
    }

    /**
     * Присвоение элементу json нового значения
     * TODO: работает некорректно если поле вложенное и не уникальное
     *
     * @param json    - json
     * @param element - элемент который требуется присвоить
     * @return json c установленным параметром
     */
    public String putJsonElement(String json, String element, String value) throws IOException {
        Objects.requireNonNull(json);
        Objects.requireNonNull(element);
        LOGGER.info("Put " + element + " = " + value);
        ObjectNode jsonNode = getObjectNode(json);
        jsonNode.findParent(element).put(element, value);
        return jsonNode.toString();
    }

    /**
     * Получить ObjectNode из строки
     *
     * @param json - строка с json
     * @return ObjectNode полученный из строки json
     */
    public ObjectNode getObjectNode(String json) throws IOException {
        Objects.requireNonNull(json);
        return (ObjectNode) MAPPER.readTree(json);
    }

    /**
     * @param nameFile - имя файла ресурсов
     * @return json из файла
     */
    /**
     * @param nameFile - имя файла ресурсов
     * @return json из файла
     * @throws ParseException
     */
    public String getJsonFromFile(String nameFile) throws ParseException {
        Objects.requireNonNull(nameFile, "nameFile = null");
        InputStream stream = getClass().getResourceAsStream("/" + nameFile);
        InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(streamReader);
        JSONObject parse = null;
        try {
            parse = (JSONObject) JSON_PARSER.parse(bufferedReader);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return Objects.requireNonNull(parse).toJSONString();
    }
}
