A Kettle User Defined Java Class to help get TIFF metadata (and other raster image data, just not tested).

1) User Defined Java Class expects the file directory location in the stream, and accessible from the user running the program.  
  - if you run on your local machine, and have files local to your computer, should work as-is.  If using windows shares or more advanced VFS approaches, untested.
  
2) under KETTLE_HOME/lib/, place twelvemonkey jars there.
- common-image, common-io, common-lang, imageio-core, imageio-metadata versions 3.0-RC5 tested.
- https://github.com/haraldk/TwelveMonkeys
- should be a plugin/lib kind of deploy, but for now this is the quick approach


Thanks to TwelveMonkeys crew for their independent imageIO implementation [BSD]

Thanks to Pentaho/Kettle for an easy to extend ETL tool [LGPL]

Thanks to PDI-CMIS-plugin to place the analyzed TIFF files into a CMIS repository [GPL...wish was LGPL]: https://code.google.com/p/pdi-cmis-plugin/
