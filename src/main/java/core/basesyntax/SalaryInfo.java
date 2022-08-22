package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int HOURS_POSITION = 2;
    private static final int SALARY_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        String[] arrayOfData;
        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                if (!line.contains(name)) {
                    continue;
                }
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE_POSITION], FORMATTER);
                if (localDateFrom.compareTo(currentDate) <= 0
                        && localDateTo.compareTo(currentDate) >= 0
                        && arrayOfData[1].equals(name)) {
                    salary += (Integer.parseInt(arrayOfData[HOURS_POSITION])
                            * Integer.parseInt(arrayOfData[SALARY_POSITION]));
                }
            }
            result.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return result.toString().trim();
    }
}
