package patterns;

import model.ProductivityActivity;
import model.Routine;
import model.WellnessActivity;
import model.base.SelfCareActivityBase;

public class ActivityFactory {

    public SelfCareActivityBase create(
            String activityType,
            int id,
            String name,
            Routine routine,
            Integer minutes,
            String intensity,
            Integer difficulty,
            String focusArea
    ) {
        if (activityType == null) {
            throw new IllegalArgumentException("activityType cannot be null");
        }

        switch (activityType.toUpperCase()) {
            case "WELLNESS":
                return new WellnessActivity(
                        id,
                        name,
                        routine,
                        minutes == null ? 1 : minutes,
                        intensity
                );
            case "PRODUCTIVITY":
                return new ProductivityActivity(
                        id,
                        name,
                        routine,
                        difficulty == null ? 1 : difficulty,
                        focusArea
                );
            default:
                throw new IllegalArgumentException("Unknown activity type: " + activityType);
        }
    }
}
