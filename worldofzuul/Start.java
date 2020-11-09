
public class Start {
    public static void main (String[] args) {

        String filepath = "Music/GameMusic.wav";

        PlayMusic musicObject = new PlayMusic();
        musicObject.playMusic(filepath);

        Game game = new Game();
        game.play();
    }
}
