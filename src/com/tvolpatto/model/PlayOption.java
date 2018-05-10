package com.tvolpatto.model;

import java.util.Arrays;
import java.util.List;

/**
 * @author Thyago Volpatto
 * @since 0.1.0 05-10-2018
 */
public enum PlayOption {

    ROCK(1),

    SCISOR(2),

    PAPER(3),

    EXIT(0);

    private Integer code;

    PlayOption(final Integer code) {
        this.code = code;
    }

    public Integer code() {
        return code;
    }

    public static List<PlayOption> getList() {
        return Arrays.asList(ROCK, SCISOR, PAPER, EXIT);
    }

    public static PlayOption lookup(int ordinal) {
        return getList().stream().filter(option -> option.code() == ordinal).findAny().get();
    }
}
