package controller;

import interfaces.Printable;
import model.ProductivityActivity;
import model.Routine;
import model.RoutineType;
import model.WellnessActivity;
import model.base.SelfCareActivityBase;
import service.interfaces.ActivityService;

import java.util.List;
import java.util.Scanner;

public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    public void runCli() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("""
                \n1) List all
                2) List sorted by score (desc)
                3) Get by id
                4) Create demo Wellness
                5) Create demo Productivity
                6) Delete
                0) Exit
                """);

            System.out.print("Choose: ");
            String choice = sc.nextLine();

            try {
                switch (choice) {
                    case "1" -> printList(activityService.getAll());
                    case "2" -> printList(activityService.getAllSortedByScoreDesc());
                    case "3" -> {
                        System.out.print("id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        activityService.getById(id).printInfo();
                    }
                    case "4" -> {
                        RoutineType t = new RoutineType(1, "Morning");
                        Routine r = new Routine(1, "Glow Up Morning", t);
                        SelfCareActivityBase a = new WellnessActivity(0, "Cold shower", r, 5, "medium");
                        activityService.create(a).printInfo();
                    }
                    case "5" -> {
                        RoutineType t = new RoutineType(1, "Morning");
                        Routine r = new Routine(1, "Glow Up Morning", t);
                        SelfCareActivityBase a = new ProductivityActivity(0, "Plan day", r, 3, "planning");
                        activityService.create(a).printInfo();
                    }
                    case "6" -> {
                        System.out.print("id: ");
                        int id = Integer.parseInt(sc.nextLine());
                        activityService.delete(id);
                        System.out.println("Deleted.");
                    }
                    case "0" -> { return; }
                    default -> System.out.println("Unknown option");
                }
            } catch (RuntimeException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            }
        }
    }

    private void printList(List<SelfCareActivityBase> list) {
        Printable.printHeader("Activities");
        for (SelfCareActivityBase a : list) {
            a.printInfo();
            a.printSeparator();
        }
    }
}
