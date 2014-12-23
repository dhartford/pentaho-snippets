A Kettle User Defined Java Class to help get TIFF metadata (and other raster image data, just not tested).

1) User Defined Java Class expects the file directory location in the stream, and accessible from the user running the program.  
  - if you run on your local machine, and have files local to your computer, should work as-is.  If using windows shares or more advanced VFS approaches, untested.
  
2) under KETTLE_HOME/plugins/, create a folders 'image-metadata/lib' and place twelvemonkey jars there.
- common-image, common-io, common-lang, imageio-core, imageio-metadata versions 3.0-RC5 tested.
- https://github.com/haraldk/TwelveMonkeys


