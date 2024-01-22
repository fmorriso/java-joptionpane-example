public class GameStatus {
    private int hoursDriven = 0;
    /**
     * @return the hoursDriven
     */
    public int getHoursDriven() {
        return hoursDriven;
    }
    /**
     * @param hoursDriven the hoursDriven to set
     */
    public void setHoursDriven(int hoursDriven) {
        this.hoursDriven = hoursDriven;
    }
    private double milesDriven = 0;
    /**
     * @return the milesDriven
     */
    public double getMilesDriven() {
        return milesDriven;
    }
    /**
     * @param milesDriven the milesDriven to set
     */
    public void setMilesDriven(double milesDriven) {
        this.milesDriven = milesDriven;
    }

    private double fuelRemaining = 100;
    /**
     * @return the fuelRemaining
     */
    public double getFuelRemaining() {
        return fuelRemaining;
    }
    /**
     * @param fuelRemaining the fuelRemaining to set
     */
    public void setFuelRemaining(double fuelRemaining) {
        this.fuelRemaining = fuelRemaining;
    }


    /** Updates the hours driven by adding the additional hours to the existing total.
     * @param additionallHoursDriven - the amount of additional hours driven
     */
    public void updateHoursDriven(int additionallHoursDriven) {
        this.hoursDriven += additionallHoursDriven;
    }

    /** Updates the miles driven by adding the additional miles to the existing total.
     * @param additionalMilesDriven - the amount of additional miles driven.
     */
    public void updateMilesDriven(double additionalMilesDriven)
    {
        this.milesDriven += additionalMilesDriven;
    }
}
