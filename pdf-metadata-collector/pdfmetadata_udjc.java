
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;


import org.apache.pdfbox.pdmodel.PDDocument;


public boolean processRow(StepMetaInterface smi, StepDataInterface sdi) throws KettleException
{
	Object[] r = getRow();
	if (r == null) {
		setOutputDone();
		return false;
	}

    if (first)
    {
        first = false;
    }

	// It is always safest to call createOutputRow() to ensure that your output row's Object[] is large
    // enough to handle any new fields you are creating in this step.
 //   r = createOutputRow(r, outputRowSize);
   r = createOutputRow(r, data.outputRowMeta.size());
	


    String x = get(Fields.In, "pdf_file_source").getString(r);

		if(  !(x.toLowerCase().endsWith("pdf") )){
		return false;
		}



		FileImageInputStream imageInputStream2;
	
		
		try {
			File img = new File(x);
			imageInputStream2 = new FileImageInputStream(img);

		Long bytesize = img.length();
		
		Long pagecount = getPageCount(img);


  
    get(Fields.Out, "pdf_pagecount").setValue(r, pagecount );

    get(Fields.Out, "pdf_bytesize").setValue(r, bytesize );





		} catch (Exception e) {
			throw new KettleException(e);
		}



	// Send the row on to the next step.

    putRow(data.outputRowMeta, r);


	return true;
}



public Long  getPageCount(File myfile){
int result = 0;
try{
PDDocument doc = PDDocument.load(myfile);
 result = doc.getNumberOfPages();
}catch(Exception e){
return 0l;
}

return Long.valueOf(result);


}


