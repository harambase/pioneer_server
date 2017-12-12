package NewCore;
import java.util.HashMap;
import java.util.Map;

public abstract class Person{
	public String name;
	public String id, active;
	private Map<String,String> personInfo;
	
	public Person(){
		name = null;
		id = null;
		active = null;
		this.personInfo = new HashMap<String,String>();
	}
	
	public void addPerson(String id, String name){
		personInfo.put(id, name);
	}
	public String getPersonName(String id){
		return personInfo.get(id);
	}
	public void setName(String name){
		this.name = name;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void display(){
		System.out.println("This person's name is " +  this.name + "\n" 
				+"This person's id is " + this.id);
	}
	
	public abstract String formingForISF();
}
