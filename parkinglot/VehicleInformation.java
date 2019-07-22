package parkinglot;

public class VehicleInformation {
	private String vehicleNo;
	private String Color;

	public VehicleInformation(String vehicleNo, String color) {
		super();
		this.vehicleNo = vehicleNo;
		Color = color;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
}
