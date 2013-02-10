package com.fedorvlasov.lazylist;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

public class FileCache {
    
    private File cacheDir;
    
    public FileCache(Context context){
    	// Use the private folder of app as cacheDir
    	cacheDir = new File(context.getFilesDir(), "lazyList");
    	
    	// If the folder doesn't exists (the first time), create it.
        if(!cacheDir.exists()) cacheDir.mkdirs();
    }
    
    public File getFile(String url){
        String filename= md5sum(url); // Identify images by md5code. 
        File f = new File(cacheDir, filename);
        return f;
        
    }
    
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null) return;
        for(File f:files) f.delete();
    }

    /*
     * Return the md5 code of a string
     */
    private static MessageDigest md = null;
    public static String md5sum(String s) {
    	
		try {
			if (md == null) md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());

			byte resultSum[] = md.digest();

			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < resultSum.length; i++) {
				String h = Integer.toHexString(0xFF & resultSum[i]);
				while (h.length() < 2) h = "0" + h;
				hexString.append(h);
			}

			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return null;
	}
}