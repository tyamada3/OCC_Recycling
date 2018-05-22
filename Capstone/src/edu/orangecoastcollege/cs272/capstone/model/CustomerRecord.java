package edu.orangecoastcollege.cs272.capstone.model;

public class CustomerRecord {
	
	private int customerId;
	private double plastic;
	private double aluminum;
	private double glass;
	private double money;
	
	public CustomerRecord(int customerId, double plastic, double aluminum, double glass, double money) {
		super();
		this.customerId = customerId;
		this.plastic = plastic;
		this.aluminum = aluminum;
		this.glass = glass;
		this.money = money;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public double getPlastic() {
		return plastic;
	}

	public void setPlastic(double plastic) {
		this.plastic = plastic;
	}

	public double getAluminum() {
		return aluminum;
	}

	public void setAluminum(double aluminum) {
		this.aluminum = aluminum;
	}

	public double getGlass() {
		return glass;
	}

	public void setGlass(double glass) {
		this.glass = glass;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(aluminum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + customerId;
		temp = Double.doubleToLongBits(glass);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(money);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(plastic);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerRecord other = (CustomerRecord) obj;
		if (Double.doubleToLongBits(aluminum) != Double.doubleToLongBits(other.aluminum))
			return false;
		if (customerId != other.customerId)
			return false;
		if (Double.doubleToLongBits(glass) != Double.doubleToLongBits(other.glass))
			return false;
		if (Double.doubleToLongBits(money) != Double.doubleToLongBits(other.money))
			return false;
		if (Double.doubleToLongBits(plastic) != Double.doubleToLongBits(other.plastic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CustomerRecord [customerId=" + customerId + ", plastic=" + plastic + ", aluminum=" + aluminum
				+ ", glass=" + glass + ", money=" + money + "]";
	}
	
	
	
	
	
}