package iblog.core.model;


public class Blogger {

	private Integer id;
	private String name;
	
	public Blogger() {
		
	}
	
	public Blogger(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Blogger [id=" + id + ", name=" + name + "]";
	}
	

}
