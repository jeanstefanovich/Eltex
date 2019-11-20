package task3_University;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface JSON {
    /**
     * Конвертирует объект в строку в формате JSON для записи в файл.
     * @return Строку в формате JSON
     */
    default public String toJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Заполняет пустой объект данными из строки в формате JSON
     * @param str Содержит строку в формате JSON
     */
    void fromJson(String str);

    /**
     * Записывает объект this в файл соответствующий этому типу объектов
     */
    void saveToJsonFile();

    //TODO Надо понять как это реализовать, но точно не метод объекта, так как эта функция будет возращать
    //TODO массив объектов, то явно она будет не методом объекта, скорее просто static метод класса
    //void readFromJsonFile();
}