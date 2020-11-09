
public class Start {
    public static void main (String[] args) {

        String filepath = "Music/smashmouthallstar.wav";

        PlayMusic musicObject = new PlayMusic();
        musicObject.playMusic(filepath);

        Game game = new Game();
        game.play();
    }
}
