import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Main
{
    public static void main(String[] args)
    {
        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        displayJavaVersionInformation();

        GameStatus gs = new GameStatus();

        // displayJFrameGUI();

        String[] startingMonthChoices = new String[]{"February", "March", "April"};
        String startingMonthName = getSingleChoice("Choose starting month", "Starting month?", startingMonthChoices);

        int monthNum = getMonthNumberFromMonthName(startingMonthName);
        displayMonthNumber(monthNum, startingMonthName);

        gs.setHoursDriven(1); // just s way to show that when we call updateHoursDriven, the total really does change
        int hoursDriven = InputUtils.getWholeNumber("Hours Driven", "Enter number of hours");
        String msg = String.format("Additional Hours Driven: %d", hoursDriven);
        JOptionPane.showMessageDialog(null, msg, "Hours Driven", JOptionPane.INFORMATION_MESSAGE);
        gs.updateHoursDriven(hoursDriven);
        msg = String.format("Total hours driven so far: %d", gs.getHoursDriven());
        JOptionPane.showMessageDialog(null, msg, "Total Hours Driven", JOptionPane.INFORMATION_MESSAGE);

        gs.setMilesDriven(1); // just s way to show that when we call updateMilesDriven, the total really does change
        double milesDriven = InputUtils.getDecimalNumber("Miles Driven", "Enter miles driven");
        msg = String.format("Additional Miles Driven: %.1f", milesDriven);
        JOptionPane.showMessageDialog(null, msg, "Miles Driven", JOptionPane.INFORMATION_MESSAGE);
        gs.updateMilesDriven(milesDriven);
        msg = String.format("Total miles driven so far: %.1f", gs.getMilesDriven());
        JOptionPane.showMessageDialog(null, msg, "Total miles driven", JOptionPane.INFORMATION_MESSAGE);

        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        System.exit(0);
    }



    private static void displayMonthNumber(int monthNumber, String monthName)
    {
        String title = "Starting month number";
        String msg = "";
        int msgType = JOptionPane.INFORMATION_MESSAGE;
        if (monthNumber == -1)
        {
            msg = "User did not choose a starting month";
            msgType = JOptionPane.WARNING_MESSAGE;
            title = "Warning";
        } else
        {
            msg = String.format("Month number for %s is %d%n", monthName, monthNumber);
        }
        System.out.println(msg);
        JOptionPane.showMessageDialog(null, msg, title, msgType);
    }


    /**
     * Get the month number for the specified month name.
     *
     * @param monthName - a string containing the month name (e.g., "February")
     * @return - int in the range 1-12 of the corresponding month number or -1
     * if the month name is null or not recognized.
     * @implNote The upper/lower case of the incoming month name is ignored.
     */
    private static int getMonthNumberFromMonthName(String monthName)
    {
        if (monthName == null) return -1;
        // look at just the first 3 characters and ignore case
        switch (monthName.substring(0,3).toLowerCase())
        {
            case "feb":
                return 2;
            case "mar":
                return 3;
            case "apr":
                return 4;
            default:
                return -1;
        }
    }

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
            if(response != null) choice = (String)response;
        }
        return choice;
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
        System.out.format("java.version=%s%n", version);

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

    /**
     * Gets a rectangle that is scaled to a percentage of available device screen size,
     * rounded up to the specified multiple.
     *
     * @param pct        - the percentage (> 0 and < 1.0) of available device screen size to use.
     * @param multipleOf - value to round up the scaled size to be a multiple of.
     * @return - a Dimension object that holds the scaled width and height.
     */
    private static Dimension getScaledSize(double pct, int multipleOf)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        if (pct < 0.1 || pct > 1)
            return screenSize;
        // System.out.format("Screen width=%d, height=%d%n", screenSize.width, screenSize.height);
        final int frameHeight = (int) (screenSize.height * pct) / multipleOf * multipleOf;
        final int frameWidth = (int) (screenSize.width * pct) / multipleOf * multipleOf;
        Dimension frameSize = new Dimension(frameWidth, frameHeight);
        System.out.format("Frame width=%d, height=%d%n", frameWidth, frameHeight);
        return frameSize;
    }

    private static void displayJFrameGUI()
    {
        Dimension scaledSize = getScaledSize(.75, 100);
        // because we want a square shaped GUI, take the smallest of width or height because we also
        // don't know the orientation (portrait vs. landscape) of the device we are running on.
        int squareSize = Math.min(scaledSize.height, scaledSize.width);

        JFrame frame = new JFrame("Workaround");
        frame.setSize(squareSize, squareSize);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setSize(squareSize, squareSize);
        pnl.setBackground(Color.CYAN);

        JLabel lbl = new JLabel(String.format("Running Java version %s", getJavaVersion()));
        lbl.setBackground(Color.RED.brighter().brighter());
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setVerticalAlignment(SwingConstants.CENTER);
        pnl.add(lbl);

        frame.setContentPane(pnl);
        pnl.requestFocus();
        frame.setVisible(true);
        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            System.out.println("InterruptedException");
        } finally
        {
            frame.setVisible(false);
        }
    }

}
