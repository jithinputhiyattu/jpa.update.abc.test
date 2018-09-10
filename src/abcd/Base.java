package abcd;



import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@MappedSuperclass

public abstract class Base implements  Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient boolean modified;
	private transient boolean active;
	
	 @Id
	    @Column(name="uuid", nullable = false, updatable = false)
	    protected String uuid = UUID.randomUUID().toString();
	public Base() {
		this.modified = false;
		this.active = false;
		
	}
	
	  @Temporal(TemporalType.TIMESTAMP)
	    @Column(updatable = false)
	    protected Calendar createdOn;

	    @Column(length = 255, updatable = false)
	    protected String createdBy;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(updatable = true)
	    protected Calendar changedOn;

	    @Column(length = 255, updatable = true)
	    protected String changedBy;

	public boolean isModified() {
		return modified;
	}
	
	   private void setCreatedAndChangedByToCurrentUser() {
	        
	            createdBy = "jithin";
	            changedBy = "jithin";
	        
	    }
	   
	   private void setChangedByToCurrentUser() {
	        
		   changedBy = "puthiyattu";
       
   }
	   
	   @PostLoad
	   private void postLoad(){
		   //System.out.println("activating");
		   //this.active = true;
	   }
	
	  private void setCreatedAndChangedOnToCurrentDate() {
		   createdOn = Calendar.getInstance();
		   createdOn.setTime(new Date());
		   changedOn = createdOn;
	    
	  }
	  
	    public Calendar getCreatedOn() {
	        return createdOn;
	    }

	    public void setCreatedOn(Calendar createdOn) {
	    	this.createdOn = createdOn;
	    }
	  
	   public String getChangedBy() {
	        return changedBy;
	    }

	    public void setChangedBy(String changedBy) {
	    	this.changedBy = changedBy;
	    }

	  
	  public String getCreatedBy() {
	        return createdBy;
	    }

	    public void setCreatedBy(String createdBy) {
	    	this.createdBy = createdBy;
	    }
	  
	   public Calendar getChangedOn() {
	        return changedOn;
	    }

	    public void setChangedOn(Calendar changedOn) {
	    	this.changedOn = changedOn;
	    }
	  
	    private void setChangedOnToCurrentDate() {
	        changedOn = Calendar.getInstance();
	        changedOn.setTime(new Date());
	    }  

	public void setModified(boolean modified) {
		this.modified = modified;
	}
	@PreUpdate
	public void preUpdate() {
		this.modified = true;
		setChangedByToCurrentUser();
		setChangedOnToCurrentDate();
		System.out.println("preUpdate ------ updating -- " + this.getClass());
	}
	
	@PrePersist
	public void prePersist() {
		this.modified = true;
		setCreatedAndChangedByToCurrentUser();
		setCreatedAndChangedOnToCurrentDate();
		System.out.println("prePersist ------- persisting -- "  + this.getClass() );
	}
	
	
		public void ubdating() {
			this.active = true;
		}
	
	   public String getUuid() {
	        return uuid;
	    }

	    public void setUuid(String uuid) {
	        this.uuid = uuid;
	    }

		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}
}
