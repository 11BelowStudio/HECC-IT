# src/oh_hecc/mvc/

Various model-view-controller-y classes used for the OH-HECC GUI

* [OhHeccNetworkFrame](./OhHeccNetworkFrame.java)
    * This class is responsible for setting up the JFrame which holds the view of the editable
      OH-HECC passage model.
      
* [Model](./Model.java)
    * An abstract superclass for `PassageModel`, wraps the generic 'model'-y things that aren't
      specific to the OH-HECC 'model' stuff.
      
* [PassageModel](./PassageModel.java)
    * The actual model showing the network of connected passages which the user can interact with
      and such.
    
* [ControllableModelInterface](./ControllableModelInterface.java)
    * An interface for the model to expose some methods called when there's a mouse event or
      a key event, allowing it to be controlled.
      
* [ModelController](./ModelController.java)
    * This class basically controls the `PassageModel`, via the `ControllableModelInterface`
      interface.
      
* [EditModelInterface](./EditModelInterface.java)
    * An interface for the model that exposes a few methods which are used by a couple of the
      actual objects that are being modelled.
      
* [model_bits](./model_bits)
    * A package full of the objects that are shown in the `PassageModel`.
    
* ~~[View](./View.java)~~
    * `@Deprecated`
    * Initially intended to be a view of the model. Rendered somewhat redundant in practice
      because the`Model` is a subclass of `JComponent` itself.
      
* ~~[ViewableModelInterface](./ViewableModelInterface.java)~~
    * `@Deprecated`
    * Intended to be an interface through which the `View` could render the `Model`, but
      ultimately made redundant because `Model` extends `JComponent`