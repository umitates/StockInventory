package compareoperation.dto;

public class Comparison {

	private String barcode;
	private Integer actual;
	private Integer expected;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Integer getActual() {
		return actual;
	}
	public void setActual(Integer actual) {
		this.actual = actual;
	}
	public Integer getExpected() {
		return expected;
	}
	public void setExpected(Integer expected) {
		this.expected = expected;
	}
	public Integer getDifference() {
		return actual - expected;
	}
}
