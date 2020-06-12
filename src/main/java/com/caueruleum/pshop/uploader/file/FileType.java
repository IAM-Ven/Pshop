package com.caueruleum.pshop.uploader.file;

public enum FileType
{
	IMAGE("img/");
	
	private final String path;
	
	private FileType(String path) 
	{
		this.path = path;
	}
	
	public String getPath() 
	{
		return this.path;
	}
	
}
