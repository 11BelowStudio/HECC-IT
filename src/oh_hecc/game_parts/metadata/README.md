# src/oh_hecc/game_parts/metadata

Various classes for the metadata for the HECCIN' Game


* [SharedMetadata](./SharedMetadata.java)
    * Interface which exposes some generic getter methods/regexes for the metadata.
    
* [MetadataReadingInterface](./MetadataReadingInterface.java)
    * Interface which just holds some more static regexes/validation methods, intended for
      reading the metadata.
      
* [MetadataEditingInterface](./MetadataEditingInterface.java)
    * Interface for the `EditableMetadata` which exposes various setter methods
      (with validation), so the metadata can be, y'know, edited.

* [EditableMetadata](./EditableMetadata.java)
    * An object that represents some editable metadata.
    
* [EditableMetadataTests](./EditableMetadataTests.java)
    * Unit tests for aforementioned `EditableMetadata`, to ensure that the setters and getters
      work as intended, and that the setters reject invalid inputs.