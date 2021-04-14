package oh_hecc.mvc.model_bits;

import java.util.UUID;

/**
 * Interface for an object that has a UUID
 */
public interface ObjectWithUUID {

    /**
     * The UUID of this object
     * @return the UUID that belongs to this object
     */
    UUID getTheUUID();
}
