package task3_University;

import java.util.ArrayList;

public interface CSV {
    /**
     * Конвертирует объект в строку в формате CSV для записи в файл.
     * @return Строку в формате CSV
     */
    String toCSV();

    /**
     * Заполняет пустой объект данными из строки в формате CSV
     * @param str Содержит строку в формате CSV
     */
    void fromCSV(String str);

    /**
     * Записывает объект this в файл соответствующий этому типу объектов
     */
    void saveToCsvFile();

    //TODO Надо понять как это реализовать, но точно не метод объекта, так как эта функция будет возращать
    //TODO массив объектов, то явно она будет не методом объекта, скорее просто static метод класса
    //void readFromCsvFile();
}
