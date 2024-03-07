public enum Colors {
    RESET("\033[0m"), RED("\033[0;31m"), GREEN("\033[0;32m"), YELLOW("\033[0;33m");

    private final String code;

    Colors(String code) {
        this.code = code;
    }
    public String toString() {
        return code;
    }
}
