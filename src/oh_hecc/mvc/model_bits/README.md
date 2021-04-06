# **src/oh_hecc/mvc/model_bits**

These are the classes that are the individual parts of the model of connected passages which the
user may interact with when using OH-HECC to edit a .hecc file.

**The classes**

* [`AbstractObject`](./AbstractObject.java)
    * The base class for all the objects in here
    
* [`EditModelObject`](./EditModelObject.java)
    * Like an AbstractObject, but it has a reference to the model itself, via an `EditModelInterface`.
    
* [`ModelButtonObject`](./ModelButtonObject.java)
    * Those buttons at the bottom of the model view, which have some text on them. They are always
      at the bottom of the visible model, rendered above the network of passage, and are resized with
      the viewable `EditModelInterface`.
      
* [`PassageObject`](./PassageObject.java)
    * An object that represents a `PassageEditingInterface`. Rendered as a rectangle, with text on
      it showing the name of the passage it represents, and has a map of `PassageLinkObject`s
      attached to it, pointing to the other `PassageObject`s that this one links to.

* [`PassageLinkObject`](./PassageLinkObject.java)
    * Basically a purple triangle, with a black outline, which points from the midpoint of a source
      `PassageObject` (well, technically, `ObjectWithAPosition`), to the midpoint of the
      `PassageObject` that this link actually links to (obtaining the position of it via UUID, from
      the `EditModelInterface`)
