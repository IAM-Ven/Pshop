package com.caueruleum.pshop.model;

/**
 * All the authorities in the db as a model
 * @author caueruleum
 *
 */
public enum AuthorityType 
{
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private final String type;
	
	private AuthorityType(String type) 
	{
		this.type = type;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public static AuthorityType fromString(String text) 
	{
        for (AuthorityType auth : AuthorityType.values()) 
        {
            if (auth.type.equalsIgnoreCase(text)) 
            {
                return auth;
            }
        }
        return null;
    }
	
	/**
	 * Check if the authority is in the enum.
	 * @param authority String the text to check
	 * @return boolean true if exits.
	 */
	public static boolean exists(String authority) 
	{
		for (AuthorityType auth : AuthorityType.values()) 
        {
            if (auth.type.equalsIgnoreCase(authority)) 
            {
                return true;
            }
        }
		
		return false;
	}
	
}
