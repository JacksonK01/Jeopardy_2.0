import java.io.File;

public class StartUp {
    //This creates needed folders for the program to access
    public static void load() {
        File folder = new File(Main.dirPath+"/JeopardyGameBoards");
        boolean dirCreated = folder.mkdir();
        System.out.println(Main.dirPath + " Successfully Created");

        folder = new File(Main.dirPath+"/SavedGames");
        dirCreated = folder.mkdir();
        System.out.println(Main.dirPath + " Successfully Created");
    }
}
