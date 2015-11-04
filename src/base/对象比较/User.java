package 对象比较;

public class User {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean equals(User user){
		return this.name.equals(user.getName());
	}
	
}
