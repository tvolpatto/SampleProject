package com.tvolpatto.ctrl;

import com.tvolpatto.ctrl.exception.GameException;
import com.tvolpatto.model.PlayOption;
import com.tvolpatto.model.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Thyago Volpatto
 * @since 0.1.0 05-10-2018
 */
public class GameCtrl {

    private static final String DEFAULT_NAME = "Player 1";
    private static final String VICTORY = "VICTORY";
    private static final String DEUCE = "DEUCE";
    private static final String LOST = "LOST";

    private static Player player;
    private Boolean keepPlaying = Boolean.TRUE;

    public GameCtrl() {
        runGame();
    }

    /**
     * Call all the methods to execute the game.
     */
    public void runGame() {
        createPlayer();

        while (keepPlaying) {
            printOptions();

            final PlayOption playerPlayed = playerPlay();
            final PlayOption computerPlayed = computerPlay();

            final String result = executePlay(playerPlayed, computerPlayed);

            System.out.println(result);

        }
    }

    /**
     * Read the player's name, if it's blank uses the default value.
     *
     * @return player's name as a String
     */
    private String getPlayerName() {
        final Scanner readPlayerName = new Scanner(System.in);

        System.out.print("Please inform your name: ");


        final String playerName = readPlayerName.nextLine();

        return (playerName != null && playerName.trim().length() > 0) ? playerName : DEFAULT_NAME;

    }

    /**
     * Creates a new player
     */
    private void createPlayer() {
        player = new Player();
        player.setName(getPlayerName());
    }

    /**
     * To print the game options.
     */
    private void printOptions() {
        PlayOption[] options = PlayOption.values();

        for (int i = 0; i < options.length; i++) {
            System.out.println(options[i].code() + " - " + options[i].name());
        }
    }

    /**
     * To read the player chosen option.
     *
     * @return {@link PlayOption} - chosen option
     */
    private PlayOption playerPlay() {
        final Scanner readPlayerName = new Scanner(System.in);

        System.out.print("Choose your option: ");

        try {
            final Integer optionPlayed = readPlayerName.nextInt();

            return PlayOption.lookup(optionPlayed);

        } catch (Exception e) {
            throw new GameException("Invalid Option!", e);

        }
    }

    /**
     * To read the computer chosen option.
     *
     * @return {@link PlayOption} - chosen option
     */
    private PlayOption computerPlay() {
        final List<PlayOption> options = PlayOption.getList();

        Random random = new Random();

        return options.get(random.nextInt(options.size()));
    }

    /**
     * Compares the player and computer plays to send the result.
     *
     * @param {@link PlayOption} playerPlayed
     * @param {@link PlayOption} computerPlayed
     * @return the result as a String
     */
    private String executePlay(final PlayOption playerPlayed, final PlayOption computerPlayed) {

        if( playerPlayed.equals(PlayOption.EXIT)) {
            keepPlaying = Boolean.FALSE;
            return "";
        }
        System.out.println(player.getName() + " chose: " + playerPlayed.name());
        System.out.println("Computer chose: " + computerPlayed.name());

        if (playerPlayed.code() == computerPlayed.code()) {
            return DEUCE;

        } else {
            switch (playerPlayed) {

                case ROCK:
                    if (computerPlayed.equals(PlayOption.SCISOR)) {
                        return VICTORY;
                    }
                    break;
                case SCISOR:
                    if (computerPlayed.equals(PlayOption.PAPER)) {
                        return VICTORY;
                    }
                    break;
                case PAPER:
                    if (computerPlayed.equals(PlayOption.ROCK)) {
                        return VICTORY;
                    }
                    break;
            }

            return LOST;
        }
    }
}
