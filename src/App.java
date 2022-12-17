import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class App {

    public static void main(String[] args) throws Exception {
        // The first Monday of the first TB of 2023 is probably the 9th of January
        System.out.println("-----------------------------------------------");
        System.out.println("is the 13th of December a WFH day? " + resolveWfhDay(13,12,2022));
        System.out.println("is the 16th of December a WFH day? " + resolveWfhDay(16,12,2022));
        System.out.println("is the 20th of December a WFH day? " + resolveWfhDay(20,12,2022));
        System.out.println("is the 22nd of December a WFH day? " + resolveWfhDay(22,12,2022));
    }

    private static boolean resolveWfhDay(int day, int month, int year) {
        LocalDate inputDate = LocalDate.of(year, month, day);

        TimeBoxWeekIterator timebox = new TimeBoxWeekIterator(LocalDate.of(2022, 10, 31), 0, 22);
        timebox.addWeekWfhDays(Set.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY));
        timebox.addWeekWfhDays(Set.of(DayOfWeek.FRIDAY, DayOfWeek.THURSDAY));

        while (!timebox.containsDate(inputDate)) timebox.nextTimeboxWeek();
        System.out.println("Timebox " + timebox.getTimeBoxNumber());

        return timebox.isWfhDay(inputDate.getDayOfWeek());
    }
}
