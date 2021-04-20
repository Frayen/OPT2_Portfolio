package View;

public class MainView extends View{

    public MainView(String[] options) {
        super(options);
    }

    @Override
    public String menu() {
        String result = "Main Menu:";
        result += "\n  exit) Exit Application";
        for (int i = 0; i < options.length; i++) {
            result += "\n  " + i + ") " + options[i];
        }
        return result;
    }
}
