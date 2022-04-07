package service.aplication.dto.bolao.api;

import java.util.ArrayList;

public class Filters {
	  private String permission;
	  ArrayList<Object> status = new ArrayList<Object>();
	  private float limit;


	 // Getter Methods 

	  public String getPermission() {
	    return permission;
	  }

	  public float getLimit() {
	    return limit;
	  }

	  //Setter Methods 
	  public void setPermission( String permission ) {
	    this.permission = permission;
	  }

	  public void setLimit( float limit ) {
	    this.limit = limit;
	  }
}
