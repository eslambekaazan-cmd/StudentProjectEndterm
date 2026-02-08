package interfaces;

public interface Validatable<T> {
    void validate();

    default boolean isValid() {
        try { validate(); return true; }
        catch (RuntimeException ex) { return false; }
    }

    static String notNullOrTrim(String s) {
        return s == null ? "" : s.trim();
    }
}
