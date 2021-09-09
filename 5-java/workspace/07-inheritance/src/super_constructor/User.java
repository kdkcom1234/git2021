package super_constructor;

// »ç¿ëÀÚ
public class User {
	private String id;
	private String name;
	private String phone;

	public void printUserInfo() {
		System.out.println(name + ", " + phone);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
