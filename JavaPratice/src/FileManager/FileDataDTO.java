package FileManager;

import java.math.BigDecimal;
import java.util.Date;

public class FileDataDTO {
	private String name;
	private Date incomeDate;
	private BigDecimal income;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}
	public BigDecimal getIncome() {
		return income;
	}
	public void setIncome(BigDecimal income) {
		this.income = income;
	}

}
