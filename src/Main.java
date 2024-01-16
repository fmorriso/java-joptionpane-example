import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
//
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;


public class Main
{
    public static void main(String[] args)
    {
        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        displayJavaVersionInformation();

        String[] startingMonthChoices = new String[]{"February","March","April"};
        String startingMonthName = getSingleChoice("What month are you starting in?", startingMonthChoices);

        int monthNum = getMonthNumberFromMonthName(startingMonthName);
        System.out.format("Month number for %s is %d%n", startingMonthName ,monthNum);

        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();
    }

    /** Get the month number for the specified month name.
     * @param monthName - a string containing the month name (e.g., "February")
     * @return - int in the range 1-12 of the corresponding month number or -1
     *           if the month name is not recognized.
     * @implNote The upper/lower case of the incoming month name is ignored.
     */
    private static int getMonthNumberFromMonthName(String monthName)
    {
        switch (monthName.toLowerCase())
        {
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            default:
                return -1;
        }
    }

    /** Display a question with multiple choices and return the single choice made by the user.
     * @param question - The question to ask the user.
     * @param choices - An array of strings representing the choices available.
     * @return - The single choice chosen by the user.
     */
    public static String getSingleChoice(String question, String[] choices)
    {
        JList<String> selections = new JList<String>(choices);
        selections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JOptionPane.showMessageDialog(null, selections, question, JOptionPane.QUESTION_MESSAGE);
        return choices[selections.getSelectedIndex()];
    }

    private static String getJavaVersion()
    {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(), runTimeVersion.patch());
    }

    /**
     * Display the version of Java this program is running under.
     */
    private static void displayJavaVersionInformation()
    {
        System.out.format("Java version: %s%n", getJavaVersion());

        String version = System.getProperty("java.version");
        System.out.println("java.version=" + version);

        version = System.getProperty("java.specification.version");
        System.out.format("java.specification.version=%s%n", version);

        version = System.getProperty("java.runtime.version");
        System.out.format("java.runtime.version=%s%n", version);

        Runtime.Version runTimeVersion = Runtime.version();
        System.out.format("RunTime.Version=%s%n", runTimeVersion);
        System.out.format("RunTime.Version.feature()=%s%n", runTimeVersion.feature()); // major version

    }

    /**
     * Display the current local Date/Time in a format close to ISO 8601 format
     */
    private static void displayCurrentLocalDateTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
    }

    /**
     * Display the current UTC Date/Time in a format close to ISO 8601 format
     */
    private static void displayCurrentUTCdateTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now(ZoneId.of("UTC"))));
    }
}
