A Kettle User Defined Java Class to help get PDF metadata (just size and pagecount).

1) User Defined Java Class expects the file directory location in the stream, and accessible from the user running the program.

if you run on your local machine, and have files local to your computer, should work as-is. If using windows shares or more advanced VFS approaches, untested.

2) under KETTLE_HOME/lib/, place pdfbox.jar.
 - just need the pdfbox jar: https://pdfbox.apache.org/download.cgi
 - should be a plugin/lib kind of deploy, but for now this is the quick approach


Thanks to Apache for an alternative PDFbox approach (iText was preferred approach until GPL/dual licensed). Note that the pagecout seems rather slow, so may need to evaluate a faster approach.

Thanks to Pentaho/Kettle for an easy to extend ETL tool [LGPL]

Thanks to PDI-CMIS-plugin to place the analyzed PDF files into a CMIS repository [GPL...wish was LGPL]: https://code.google.com/p/pdi-cmis-plugin/
