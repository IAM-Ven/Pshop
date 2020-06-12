package com.caueruleum.pshop.uploader.file;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class PshopFile
{
	
	public static class PshopFileBuilder
	{
		private FileType type;
		private MultipartFile multipart;
		
		public PshopFileBuilder withType(FileType type) 
		{
			this.type = type;
			
			return this;
		}
		
		public PshopFileBuilder withMultipart(MultipartFile multipart) throws FileNotFoundException
		{
			if(multipart.isEmpty()) 
			{
				throw new FileNotFoundException();
			}
			this.multipart = multipart;
			
			return this;
		}
		
		public FileType getType()
		{
			return type;
		}

		public MultipartFile getMultipart()
		{
			return multipart;
		}

		
		
		public PshopFile build() throws IOException
		{
			byte[] fileBytes = this.multipart.getBytes();
			String path = this.type.getPath() + this.multipart.getOriginalFilename();
			
			PshopFile pshopFile = new PshopFile(fileBytes, path);
			
			return pshopFile;
			
		}
		
	}
	
	// The file in bytes
	private final byte[] file;
	
	// In what sub folder to upload it.
	private final String path;
	
	private PshopFile(byte[] file, String path) 
	{
		this.file = file;
		this.path = path;
	}

	public byte[] getFile()
	{
		return file;
	}

	public String getPath()
	{
		return path;
	}
	
}
