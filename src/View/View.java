package View;

public abstract class View {
    public String[] options;

    public View(String[] options) {
        this.options = options;
    }

    public String[] getOptions() {
        String[] result = new String[options.length];
        System.arraycopy(options, 0, result, 0, options.length);
        return result;
    }

    public abstract String menu();
}
