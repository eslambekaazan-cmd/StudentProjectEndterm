package interfaces;

public interface Printable {
    void printInfo();

    default void printSeparator() {
        System.out.println("------------------------------");
    }

    static void printHeader(String title) {
        System.out.println("\n=== " + title.toUpperCase() + " ===");
    }
}
