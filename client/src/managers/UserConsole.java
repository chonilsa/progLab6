package managers;

public class UserConsole implements Console {


    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }

    @Override
    public void print(Object obj) {
        System.out.print(obj);
    }

    @Override
    public void printErr(Object obj) {
        System.err.print("Error:" + obj);
    }
}
