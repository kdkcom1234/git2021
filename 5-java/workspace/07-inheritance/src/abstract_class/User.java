package abstract_class;

// ����� - �ڽİ�ü�� ������ ��Ÿ���� Ŭ����
// �ڽİ�ü�� �⺻ �ʵ�, �޼���� �߰� �����ؾߵǴ� �޼����� ���븸 ����
// public abstract class Ŭ������
public abstract class User {
	private String id;
	private String name;
	private String phone;

	// �߻� �޼���: �ڽ� Ŭ�������� ������
	// �ڽİ�ü�� �޼��� �ñ״�ó�� �����ϰ� �ϱ� ����
	public abstract void printUserInfo();

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
