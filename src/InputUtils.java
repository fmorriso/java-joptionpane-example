import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

public class InputUtils
{

    /**
     * Display a question with multiple choices and return the single choice made by the user.
     *
     * @param title    - The title for the pop-up dialog.
     * @param question - The question to ask the user just above the list of available choices.
     * @param choices  - An array of strings representing the choices available.
     * @return - The single choice chosen by the user.
     */
    public static String getSingleChoice(String title, String question, String[] choices)
    {
        String choice = "";
        // force user to choose something by ignoreing clicks on the Cancel button
        while(choice.equals(""))
        {
            JList<String> selections = new JList<String>(choices);
            selections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            Icon icon = UIManager.getIcon("OptionPane.questionIcon");
            Object response = JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0]);
            // if the user did not click the Cancel button, capture the response.
            if(response != null) choice = (String)response;
        }
        return choice;
    }

    /** Prompts the user to enter a whole number and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param msg - helpful text to tell the user what it is they are entering
     * @return - the whole number the user typed in.
     * @implNote - if any non-numeric or numbers with a decimal point are typed into the box, it will pop right back up until
     *             the user enters a valid value.
     */
    public static int getWholeNumber(String title, String msg)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        int n = Integer.MIN_VALUE;
        while(n == Integer.MIN_VALUE)
        {
            String resp = JOptionPane.showInputDialog(null, title, msg, msgType);
            try {
                n = Integer.parseInt(resp);
            } catch (Exception e) {
                n = 0;
            }
        }
        return n;
    }

    /** Prompts the user to enter a decimal number and returns it.
     * @param title - the title to use when presenting the dialog box to the user.
     * @param msg - helpful text to tell the user what it is they are entering
     * @return - the decimal number the user typed in.
     * @implNote - if any non-numeric characters are typed into the box,
     *             it will pop right back up until the user enters a valid value.
     */
    public static double getDecimalNumber(String title, String msg)
    {
        final int msgType = JOptionPane.INFORMATION_MESSAGE;
        double n = 0;
        while(n < 1)
        {
            String resp = JOptionPane.showInputDialog(null, title, msg, msgType);
            try {
                n = Double.parseDouble(resp);
            } catch (Exception e) {
                n = 0;
            }
        }
        return n;
    }

}
