package oh_hecc.game_parts.passage;

/**
 * A set of possible statuses for this passage
 */
public enum PassageStatus {
    /**
     * Use this one if there's no problems with the passage
     */
    NORMAL(true),
    /**
     * Use this one if the passage has no links (it's an end passage)
     */
    END_NODE(true),
    /**
     * use this one after a link gets deleted and it still has the '!! was deleted !!' message in the passage body
     */
    DELETED_LINK(false),

    /**
     * use this one if the passage content is empty
     */
    EMPTY_CONTENT(false);


    /**
     * Whether or not this particular enum is a 'valid' state (aka a 'HECC-UP won't complain about this' state).
     */
    private final boolean validState;

    /**
     * A constructor for the enums.
     *
     * @param validState whether or not this enum is a 'valid' state for a passage.
     */
    PassageStatus(boolean validState) {
        this.validState = validState;
    }

    /**
     * Returns whether or not this enum is valid or not.
     *
     * @return true if it's a 'valid' state, false otherwise
     */
    public boolean isValid() {
        return validState;
    }
}
