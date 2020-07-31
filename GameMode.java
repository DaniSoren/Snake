import java.util.Scanner;

enum GameType {
    AUTO, SOLO
}

public class GameMode {
    private Game game;

    private int ms;

    public GameMode(int m, int n, int ms) throws Exception {
        game = new Game(m, n);
        this.ms = ms;
    }

    public void play(GameType type) throws InterruptedException {
        switch (type) {
            case AUTO:
                autoPlay();
                break;
            case SOLO:
                soloPlay();
                break;

            default:
                break;
        }
        game.reset();
    }

    private void autoPlay() throws InterruptedException {
        while (!game.gameWon()) {
            Thread.sleep(ms);

            Direction next = game.getNextMove();
            game.update(next);
        }
    }

    private void soloPlay() {
        Scanner sc = new Scanner(System.in);
        while (!game.gameLost()) {
            char next = sc.next().toLowerCase().toCharArray()[0];
            switch (next) {
                case 'w':
                    game.update(Direction.UP);
                    break;
                case 'a':
                    game.update(Direction.LEFT);
                    break;
                case 's':
                    game.update(Direction.DOWN);
                    break;
                case 'd':
                    game.update(Direction.RIGHT);
                    break;
                default:
                    break;
            }
        }
    }
}