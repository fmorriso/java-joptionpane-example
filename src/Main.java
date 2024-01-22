import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
//
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
//
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Main
{
    public static void main(String[] args)
    {
        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        displayJavaVersionInformation();

        // displayJFrameGUI();

        String[] startingMonthChoices = new String[]{"February","March","April"};
        String startingMonthName = getSingleChoice("Choose starting month", "Starting month?", startingMonthChoices);

        int monthNum = getMonthNumberFromMonthName(startingMonthName);
        String msg = "";
        if (monthNum == -1) msg = "User did not choose a starting month";
        else msg = String.format("Month number for %s is %d%n", startingMonthName ,monthNum);
        System.out.println(msg);
        JOptionPane.showMessageDialog(null, msg,"Starting Month Number", JOptionPane.INFORMATION_MESSAGE);

        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        System.exit(0);
    }

    /** Get the month number for the specified month name.
     * @param monthName - a string containing the month name (e.g., "February")
     * @return - int in the range 1-12 of the corresponding month number or -1
     *           if the month name is null or not recognized.
     * @implNote The upper/lower case of the incoming month name is ignored.
     */
    private static int getMonthNumberFromMonthName(String monthName)
    {
        if(monthName == null) return -1;
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
     * @param title - The title for the pop-up dialog.
     * @param question - The question to ask the user just above the list of available choices.
     * @param choices - An array of strings representing the choices available.
     * @return - The single choice chosen by the user.
     */
    public static String getSingleChoice(String title, String question, String[] choices)
    {
        JList<String> selections = new JList<String>(choices);
        selections.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Icon icon = UIManager.getIcon("OptionPane.questionIcon");
        Object choice = JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE, icon, choices, choices[0] );
        return (String)choice;
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
     * @param pct - the percentage (> 0 and < 1.0) of available device screen size to use.
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
        try {
            Thread.sleep(2000);            
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } finally {
            frame.setVisible(false);
        }
    }

}
