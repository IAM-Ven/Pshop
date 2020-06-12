package com.caueruleum.pshop.uploader;

import java.io.IOException;

import com.caueruleum.pshop.uploader.file.PshopFile;

public interface Uploader
{
	/**
	 * Upload a file
	 * @throws IOException if we can`t upload the file. 
	 */
	public void upload(PshopFile image, String uploadPath) throws IOException ;
}
