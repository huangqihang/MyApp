package thread.executor.demo.travel;

import java.util.Date;

public class TravelInfo {
	private String target;
	private Date travelDate;
	
	public TravelInfo(String target, Date travelDate) {
		this.target = target;
		this.travelDate = travelDate;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	
}
