package compareoperation.dto;

public class Comparison {

	private String barcode;
	private Long actual;
	private Long expected;
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Long getActual() {
		return actual;
	}
	public void setActual(Long actual) {
		this.actual = actual;
	}
	public Long getExpected() {
		return expected;
	}
	public void setExpected(Long expected) {
		this.expected = expected;
	}
	public Long getDifference() {
		return actual - expected;
	}
}
