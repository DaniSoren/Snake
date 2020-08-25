public class Main {

    public static void main(String[] args) throws Exception {
        
        GameMode gm = new GameMode(3, 4, 1000);
        gm.play(GameType.AUTO);
    }
}