package com.caueruleum.pshop.uploader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.caueruleum.pshop.uploader.file.PshopFile;

@Component
public class UploaderImpl implements Uploader
{

	@Override
	public void upload(PshopFile file, String uploadPath) throws IOException
	{
		String finalPath = uploadPath + file.getPath();
		
		Path path = Paths.get(finalPath);
		
		Files.write(path, file.getFile());
		
		
	}

}
