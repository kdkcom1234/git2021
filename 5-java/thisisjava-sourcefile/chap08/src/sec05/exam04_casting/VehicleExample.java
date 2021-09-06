package sec05.exam04_casting;

public class VehicleExample {
	public static void main(String[] args) {
		Vehicle vehicle = new Bus();

		vehicle.run();
		//vehicle.checkFare(); (x)

		Bus bus = (Bus) vehicle;  //강제타입변환

		bus.run();
		bus.checkFare();
	}
}
