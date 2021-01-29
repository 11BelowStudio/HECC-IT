package oh_hecc.game_parts.passage;

/**
 * A set of possible statuses for this passage
 */
public enum PassageStatus {
    /**
     * Use this one if there's no problems with the passage
     */
    NORMAL,
    /**
     * Use this one if the passage has no links (it's an end passage)
     */
    END_NODE,
    /**
     * use this one after a link gets deleted and it still has the '!! was deleted !!' message in the passage body
     */
    DELETED_LINK,

    /**
     * use this one if the passage content is empty
     */
    EMPTY_CONTENT
}
