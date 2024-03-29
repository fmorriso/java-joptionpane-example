import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Main {
    public static void main(String[] args) {
        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        displayJavaVersionInformation();

        GameStatus gs = new GameStatus();
        String msg = "";
        // displayJFrameGUI();

        final int minMonth = 1, maxMonth = 12;
        int startMonth = InputUtils.getWholeNumberInRange("Enter starting month", minMonth, maxMonth);
        msg = String.format("The starting month is: %d", startMonth);
        InputUtils.displayMessage(msg, "Starting month");

        final double minAFE = 10, maxAFE = 100;
        double afe = InputUtils.getDecimalNumberInRange("Enter average fuel economy", minAFE, maxAFE);
        msg = String.format("The average fuel economy is: %.1f", afe);
        InputUtils.displayMessage(msg, "Hours driven");
        //JOptionPane.showMessageDialog(null, msg, "Hours Driven", JOptionPane.INFORMATION_MESSAGE);

        String[] startingMonthChoices = new String[] {"February", "March", "April"};
        String startingMonthName = InputUtils.getSingleChoice("Choose starting month", "Starting month?",
                startingMonthChoices);

        int monthNum = getMonthNumberFromMonthName(startingMonthName);
        displayMonthNumber(monthNum, startingMonthName);

        gs.setHoursDriven(1); // just a way to show that when we call updateHoursDriven, the total really does
                              // change
        int hoursDriven = InputUtils.getWholeNumber("Hours Driven", "Enter number of hours");
        msg = String.format("Additional Hours Driven: %d", hoursDriven);
        InputUtils.displayMessage(msg, "Hours Driven");      
        gs.updateHoursDriven(hoursDriven);
        msg = String.format("Total hours driven so far: %d", gs.getHoursDriven());
        InputUtils.displayMessage(msg, "Total Hours Driven");
        

        gs.setMilesDriven(1); // just a way to show that when we call updateMilesDriven, the total really does
                              // change
        double milesDriven = InputUtils.getDecimalNumber("Miles Driven", "Enter miles driven");
        msg = String.format("Additional Miles Driven: %.1f", milesDriven);
        InputUtils.displayMessage(msg, "Miles Driven");        
        gs.updateMilesDriven(milesDriven);
        msg = String.format("Total miles driven so far: %.1f", gs.getMilesDriven());
        InputUtils.displayMessage(msg, "Total Miles Driven");      

        displayCurrentLocalDateTime();
        displayCurrentUTCdateTime();

        System.exit(0);
    }

    /**
     * Displays the month number (1-12) that corresponds to the specified month name
     * 
     * @param monthNumber
     * @param monthName
     */
    private static void displayMonthNumber(int monthNumber, String monthName) {
        String title = "Starting month number";
        String msg = "";
        int msgType = JOptionPane.INFORMATION_MESSAGE;
        if (monthNumber == -1) {
            msg = "User did not choose a starting month";
            msgType = JOptionPane.WARNING_MESSAGE;
            title = "Warning";
        } else {
            msg = String.format("Month number for %s is %d%n", monthName, monthNumber);
        }
        System.out.println(msg);
        JOptionPane.showMessageDialog(null, msg, title, msgType);
    }

    /**
     * Get the month number for the specified month name.
     *
     * @param monthName - a string containing the month name (e.g., "February")
     * @return - int in the range 1-12 of the corresponding month number or -1 if
     *           the month name is null or not recognized.
     * @implNote The upper/lower case of the incoming month name is ignored.
     */
    private static int getMonthNumberFromMonthName(String monthName) {
        if (monthName == null || monthName.length() < 3)
            return -1;
        // look at just the first 3 characters and ignore case
        switch (monthName.substring(0, 3).toLowerCase()) {
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

    private static String getJavaVersion() {
        Runtime.Version runTimeVersion = Runtime.version();
        return String.format("%s.%s.%s.%s", runTimeVersion.feature(), runTimeVersion.interim(), runTimeVersion.update(),
                runTimeVersion.patch());
    }

    /**
     * Display the version of Java this program is running under.
     */
    private static void displayJavaVersionInformation() {
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
    private static void displayCurrentLocalDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()));
    }

    /**
     * Display the current UTC Date/Time in a format close to ISO 8601 format
     */
    private static void displayCurrentUTCdateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now(ZoneId.of("UTC"))));
    }

    /**
     * Gets a rectangle that is scaled to a percentage of available device screen
     * size, rounded up to the specified multiple.
     *
     * @param pct        - the percentage (> 0 and < 1.0) of available device screen
     *                   size to use.
     * @param multipleOf - value to round up the scaled size to be a multiple of.
     * @return - a Dimension object that holds the scaled width and height.
     */
    private static Dimension getScaledSize(double pct, int multipleOf) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        if (pct < 0.1 || pct > 1)
            return screenSize;
        // System.out.format("Screen width=%d, height=%d%n", screenSize.width,
        // screenSize.height);
        final int frameHeight = (int) (screenSize.height * pct) / multipleOf * multipleOf;
        final int frameWidth = (int) (screenSize.width * pct) / multipleOf * multipleOf;
        Dimension frameSize = new Dimension(frameWidth, frameHeight);
        System.out.format("Frame width=%d, height=%d%n", frameWidth, frameHeight);
        return frameSize;
    }

    private static void displayJFrameGUI() {
        Dimension scaledSize = getScaledSize(.75, 100);
        // because we want a square shaped GUI, take the smallest of width or height
        // because we also
        // don't know the orientation (portrait vs. landscape) of the device we are
        // running on.
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
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } finally {
            frame.setVisible(false);
        }
    }

}
