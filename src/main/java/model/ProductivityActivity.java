package model;

import model.base.SelfCareActivityBase;

public class ProductivityActivity extends SelfCareActivityBase {
    private int difficulty; // 1..5
    private String focusArea;

    public ProductivityActivity(int id, String name, Routine routine, int difficulty, String focusArea) {
        super(id, name, routine);
        this.difficulty = difficulty;
        this.focusArea = focusArea;
    }

    @Override
    public String getCategory() {
        return "PRODUCTIVITY";
    }

    @Override
    public int estimateScore() {
        return Math.max(1, difficulty) * 10;
    }

    public int getDifficulty() { return difficulty; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }

    public String getFocusArea() { return focusArea; }
    public void setFocusArea(String focusArea) { this.focusArea = focusArea; }

    @Override
    public void validate() {
        super.validate();
        if (difficulty < 1 || difficulty > 5)
            throw new exception.InvalidInputException("difficulty must be 1..5");
    }
}
