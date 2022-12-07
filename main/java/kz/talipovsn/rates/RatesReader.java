package kz.talipovsn.rates;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

// СОЗДАТЕЛЬ КОТИРОВОК ВАЛЮТ
public class RatesReader {

    private static final String BASE_URL = "https://github.com/orgs/tsnsoft/people"; // Адрес с котировками

    // Парсинг котировок из формата html web-страницы банка, при ошибке доступа возвращаем null
    public static String getRatesData() {
        StringBuilder data = new StringBuilder();
        try {
            Document doc = Jsoup.connect(BASE_URL).timeout(5000).get(); // Создание документа JSOUP из html

            Elements box = doc.select("a.f4");

            // Цикл по строкам таблицы
            for (int i = 0; i < box.size(); i++) {
                String name2 = box.get(i).text();
                String log2 = box.get(i).attr("id");

                data.append("Пользователь: " +  name2 + "\n" +
                        log2 + "\n\n");

            }
        } catch (Exception ignored) {
            return null; // При ошибке доступа возвращаем null
        }
        return data.toString().trim(); // Возвращаем результат
    }

}