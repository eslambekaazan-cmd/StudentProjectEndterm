package model;

import model.base.SelfCareActivityBase;

public class WellnessActivity extends SelfCareActivityBase {
    private int minutes;
    private String intensity; // low/medium/high

    public WellnessActivity(int id, String name, Routine routine, int minutes, String intensity) {
        super(id, name, routine);
        this.minutes = minutes;
        this.intensity = intensity;
    }

    @Override
    public String getCategory() {
        return "WELLNESS";
    }

    @Override
    public int estimateScore() {
        int mult;
        String v = (intensity == null) ? "" : intensity.toLowerCase();
        switch (v) {
            case "high": mult = 3; break;
            case "medium": mult = 2; break;
            default: mult = 1;
        }
        return Math.max(1, minutes) * mult;
    }

    public int getMinutes() { return minutes; }
    public void setMinutes(int minutes) { this.minutes = minutes; }

    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }

    @Override
    public void validate() {
        super.validate();
        if (minutes <= 0) throw new exception.InvalidInputException("minutes must be > 0");
    }
}
