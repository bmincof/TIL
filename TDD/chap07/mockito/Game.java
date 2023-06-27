package appendixC.mockito;

public class Game {
    private GameNumGen gameNumGen;
    private String answer;

    public Game(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    public void init(GameLevel level) {
        answer = gameNumGen.generate(level);
    }
}
