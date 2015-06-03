package thread.executor.demo.travel;

public class TravelQuote {
	private String companyName;
	private double price;
	private String desc;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "TravelQuote [companyName=" + companyName + ", price=" + price
				+ ", desc=" + desc + "]";
	}

}
