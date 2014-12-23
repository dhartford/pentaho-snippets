
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.stream.FileImageInputStream;


import com.twelvemonkeys.imageio.metadata.CompoundDirectory;
import com.twelvemonkeys.imageio.metadata.Directory;
import com.twelvemonkeys.imageio.metadata.exif.EXIFReader;
import com.twelvemonkeys.imageio.metadata.exif.Rational;

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
	


    String x = get(Fields.In, "ImageReference").getString(r);
    
    //filtering to only tiff files, change if need other types.
		if(  !(x.toLowerCase().endsWith("tif") || x.toLowerCase().endsWith("tiff"))){
		return false;
		}



		FileImageInputStream imageInputStream2;
		com.twelvemonkeys.imageio.metadata.Directory exifDirectory = null;
		
		try {
			File img = new File(x);
			imageInputStream2 = new FileImageInputStream(img);

		Long bytesize = img.length();
		
		CompoundDirectory IFDs = (CompoundDirectory) new EXIFReader().read(imageInputStream2); 
		exifDirectory = IFDs.getDirectory(0);
		Long pagecount = getPageCount(IFDs);
		Long compression = getFirstCompression(exifDirectory);
		Long colorcount = getFirstColorCount(exifDirectory);
		Long xres = getFirstXresolution(exifDirectory);
		Long yres = getFirstYresolution(exifDirectory);


    get(Fields.Out, "img_compression").setValue(r, compression );
    get(Fields.Out, "img_pagecount").setValue(r, pagecount );
    get(Fields.Out, "img_colorcount").setValue(r, colorcount );
    get(Fields.Out, "img_xres").setValue(r, xres );
    get(Fields.Out, "img_yres").setValue(r, yres );
    get(Fields.Out, "img_bytesize").setValue(r, bytesize );





		} catch (Exception e) {
			throw new KettleException(e);
		}



	// Send the row on to the next step.

    putRow(data.outputRowMeta, r);


	return true;
}



public Long  getFirstCompression(com.twelvemonkeys.imageio.metadata.Directory exifDirectory){

		Long  result = ( (Integer)exifDirectory.getEntryById(259).getValue() ).longValue();
return result;

}

public Long  getPageCount(com.twelvemonkeys.imageio.metadata.CompoundDirectory IFD){

		Long  result = ( (Integer)IFD.directoryCount() ).longValue();
return result;

}


public Long  getFirstXresolution(com.twelvemonkeys.imageio.metadata.Directory exifDirectory){

		Long  result = ((Rational)exifDirectory.getEntryById(282).getValue() ).longValue();
return result;

}
public Long  getFirstYresolution(com.twelvemonkeys.imageio.metadata.Directory exifDirectory){

		Long  result = ((Rational)exifDirectory.getEntryById(283).getValue() ).longValue();
return result;

}


public Long  getFirstColorCount(com.twelvemonkeys.imageio.metadata.Directory exifDirectory){
	//actually bit sampling, but relates to color count
try{
		Long  result = ( (Integer)exifDirectory.getEntryById(258).getValue() ).longValue();
		return result;

}catch(Exception e){
//do nothing
}
return null;

}
