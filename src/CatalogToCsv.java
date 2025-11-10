import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class CatalogToCsv {

    private static String escapeCsv(String field) {
        if (field == null) field = "";
        field = field.replace("\"", "\"\"");
        return "\"" + field + "\"";
    }

    public static void main(String[] args) throws IOException {
        String url = "https://foxlink.rollins.edu/prod/pkg_display_schedule.p_get_rschedule_spring?i_term_code=202601&i_campus_code=R";
        Document doc = Jsoup.connect(url).get();

        FileWriter csv = new FileWriter("rollins_catalog_spring2026.csv");
        csv.write("code,title,credits,instructor,location,day,start,end,type\n");

        Elements rows = doc.select("table tr");
        for (Element row : rows) {
            Elements cols = row.select("td");
            if (cols.size() < 10) continue;

            String status = cols.get(0).text().trim();
            if (status.equalsIgnoreCase("Status")) continue;

            String course = cols.get(3).text().trim();
            String title = cols.get(4).text().trim();
            String credits = cols.get(5).text().trim();
            String time = cols.get(6).text().trim();
            String day = cols.get(7).text().trim();
            String location = cols.get(8).text().trim();
            String instructor = cols.get(9).text().trim();

            String start = "", end = "";
            if (time.contains("-")) {
                String[] parts = time.split("-");
                start = parts[0].trim();
                end = parts[1].trim();
            }

            csv.write(escapeCsv(course) + "," + escapeCsv(title) + "," +
                      escapeCsv(credits) + "," + escapeCsv(instructor) + "," +
                      escapeCsv(location) + "," + escapeCsv(day) + "," +
                      escapeCsv(start) + "," + escapeCsv(end) + ",LECTURE\n");
        }

        csv.close();
        System.out.println("✅ rollins_catalog_spring2026.csv created!");
    }
}
