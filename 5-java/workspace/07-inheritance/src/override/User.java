package override;

// �����

// final class ��� �Ұ��� Ŭ����
//public final class User {
public class User {
	private String id;
	private String name;
	private String phone;

	// final �޼��� : ������ �Ұ����� �޼���
//	public final void printUserInfo() {
	public void printUserInfo() {
		System.out.print("\n" + name + ", " + phone);
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
