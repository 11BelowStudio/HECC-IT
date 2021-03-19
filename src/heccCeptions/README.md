# **src/heccCeptions**

These are some custom exceptions that various parts of HECC-IT throw when there's a problem with the .hecc files.

* [HeccCeption](./HeccCeption.java)
  * This is an abstract superclass for all the other HeccCeptions within this package.
  * This is mostly here, so, if multiple types of HeccCeption are expected, I can just catch `HeccCeption e`.
    
* [DeletedLinkPresentException](./DeletedLinkPresentException.java)
  * Thrown if a link to a passage which is known to be deleted is present in a passage.

* [DuplicatePassageNameException](./DuplicatePassageNameException.java)
  * Thrown if two passages share the same name.
  
* [EmptyPassageException](./EmptyPassageException.java)
  * Thrown by HECC-UP if a passage has no content.
  
* [InvalidMetadataDeclarationException](./InvalidMetadataDeclarationException.java)
  * Thrown if some metadata is invalid.
  
* [InvalidPassageNameException](./InvalidPassageNameException.java)
  * Thrown if a passage has an invalid name.
  
* [MissingStartingPassageException](./MissingStartingPassageException.java)
  * Thrown if the declared start passage is AWOL
  
* [NoMatchException](./NoMatchException.java)
  * A generic exception, thrown if nothing matches a particular regex somewhere.
  
* [NoPasssagesException](./NoPassagesException.java)
  * Thrown if a .hecc file has no passages.
  
* [UndefinedPassageException](./UndefinedPassageException.java)
  * Thrown if a passage in a .hecc file contains a link to a passage which doesn't exist.