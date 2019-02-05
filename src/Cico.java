public class Cico {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
    public static final String CYAN = "\033[0;36m";


    public Cico(){
        System.out.println("^._.^");
    }

    public void meow(int count){
        for (int i = 0; i < count; i++){
//            System.out.println(CYAN + "Meow" + ANSI_RESET);
            System.out.println("\033[0;36mMeow\u001B[0m");
        }
    }

}
