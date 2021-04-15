# **src/oh_hecc/mvc/model_bits**

These are the classes that are the individual parts of the model of connected passages which the
user may interact with when using OH-HECC to edit a .hecc file.

## The classes

* [AbstractObject](./AbstractObject.java)
    * The base class for all the objects in here

* [ModelButtonObject](./ModelButtonObject.java)
    * Those buttons at the bottom of the model view, which have some text on them. They are always
      at the bottom of the visible model, rendered above the network of passage, and are resized with
      by the `PassageModel` when it gets resized.
      
* [StringObject](./StringObject.java)
    * An `AbstractObject` that basically renders a string, with either left, right, or centered
      alignment, around a given position, with the text being white with an 'outline' (in the form
      of four, slightly-offset copies of that string, rendered in black, being rendered before the
      white text copy, looking not entirely unlike an outline)

* [EditModelObject](./EditModelObject.java)
    * Like an AbstractObject, but it has a reference to the model itself, via an `EditModelInterface`.
    
* [PassageObject](./PassageObject.java)
    * An object that represents a `ModelBitsPassageInterface`. Rendered as a rectangle, with text on
      it showing the name of the passage it represents, and has a map of `PassageLinkObject`s
      attached to it, pointing to the other `PassageObject`s that this one links to.

* [PassageLinkObject](./PassageLinkObject.java)
    * Basically a purple triangle, with a black outline, which points from the midpoint of a source
      `PassageObject` (well, technically, `ObjectWithAPosition`), to the midpoint of the
      `PassageObject` that this link actually links to (obtaining the position of it via UUID, from
      the `EditModelInterface`)


## The interfaces

### Highest-level interfaces

* [ClickableObject](./ClickableObject.java)
    * An object that can be clicked.
        * `boolean wasClicked(Point clickLocation)`

* [DrawableObject](./DrawableObject.java)
  * An object that can be drawn.
        * `void draw(Graphics2D g)`
    
* [MoveableObject](./MoveableObject.java)
    * An object that can be moved.
        * `void move(Vector2D moveVector)`
    
* [ObjectWithAPosition](./ObjectWithAPosition.java)
    * An object that has a position
        * `Vector2D getPosition()`
    
* [ObjectWithUUID](./ObjectWithUUID.java)
    * An object that has a UUID
        * `UUID getTheUUID()`
    
* [ResizeableObject](./ResizeableObject.java)
    * An object that can be resized
        * `void resize(int w, int h)`
    
* [UpdatableObject](./UpdatableObject.java)
    * An object that can be updated
        * `void update()`
    
### Child interfaces

* [ClickableObjectWithUUID.java](./ClickableObjectWithUUID.java)
    * Extends `ClickableObject` and `ObjectWithUUID`
    
* [DrawableObjectWithText](./DrawableObjectWithText.java)
    * Extends `DrawableObject`, and has a `void drawText(Graphics2D g)` method
    
* [DrawablePassageObject](./DrawablePassageObject.java)
    * Extends `DrawableObjectWithText`, and has a `void drawLinks(Graphics2D g)` method.
    
* [ObjectWithAPositionAndUUID](./ObjectWithAPositionAndUUID.java)
    * Extends `ObjectWithAPosition` and `ObjectWithUUID`
    
* [SelectableObject](./SelectableObject.java)
    * Extends `MoveableObject` and `ClickableObject`.
    * Also has `void nowSelected()` to 'select' it and `void deselected()` to 'deselect' it