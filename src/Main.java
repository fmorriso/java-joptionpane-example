import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;


public class Main
{
    public static void main(String[] args)
    {
        displayJavaVersionInformation();

        String[] startingMonthChoices = new String[]{"February","March","April"};
        String startingMonthName = getSingleChoice("What month are you starting in?", startingMonthChoices);

        int monthNum = getStartingMonthNumberFromMonthName(startingMonthName);
        System.out.format("Month number for %s is %d%n", startingMonthName ,monthNum);
    }

    private static int getStartingMonthNumberFromMonthName(String startingMonth)
    {

        switch (startingMonth.toLowerCase())
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

    private static void displayJavaVersionInformation()
    {
        String version = System.getProperty("java.version");
        System.out.println("java.version=" + version);

        version = System.getProperty("java.specification.version");
        System.out.println("java.specification.version=" + version);

        version = System.getProperty("java.runtime.version");
        System.out.println("java.runtime.version=" + version);

        Runtime.Version runTimeVersion = Runtime.version();
        System.out.println("RunTime.Version=" + runTimeVersion);
        System.out.println("RunTime.Version.feature()=" + runTimeVersion.feature()); // major version

    }

}
