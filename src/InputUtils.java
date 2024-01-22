import javax.swing.JOptionPane;

public class InputUtils
{
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
        int n = 0;
        while(n < 1)
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
