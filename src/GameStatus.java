public class GameStatus {
    private int hoursDriven = 0;
    private double milesDriven = 0;
    private double fuelRemaining = 100;

    /**
     * @return the hours driven
     */
    public int getHoursDriven() {
        return hoursDriven;
    }

    /**
     * @param hoursDriven the hours driven to set
     */
    public void setHoursDriven(int hoursDriven) {
        this.hoursDriven = hoursDriven;
    }

    /**
     * @return the miles driven
     */
    public double getMilesDriven() {
        return milesDriven;
    }

    /**
     * @param milesDriven the miles driven to set
     */
    public void setMilesDriven(double milesDriven) {
        this.milesDriven = milesDriven;
    }

    /**
     * @return the fuel remaining
     */
    public double getFuelRemaining() {
        return fuelRemaining;
    }

    /**
     * @param fuelRemaining the fuel remaining to set
     */
    public void setFuelRemaining(double fuelRemaining) {
        this.fuelRemaining = fuelRemaining;
    }

    /**
     * Updates the hours driven by adding the additional hours to the existing
     * total.
     *
     * @param additionallHours - the amount of additional hours driven
     */
    public void updateHoursDriven(int additionallHours) {
        this.hoursDriven += additionallHours;
    }

    /**
     * Updates the miles driven by adding the additional miles to the existing
     * total.
     *
     * @param additionalMilesDriven - the amount of additional miles driven.
     */
    public void updateMilesDriven(double additionalMilesDriven) {
        this.milesDriven += additionalMilesDriven;
    }
}
