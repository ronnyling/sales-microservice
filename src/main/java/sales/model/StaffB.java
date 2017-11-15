package sales.model;


import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@lombok.Getter
@lombok.Setter
@ToString
@Entity // This tells Hibernate to make a table out of this class
public class StaffB {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer eID;
	private String fName;
	private	String lName;
	private String gender;
	private Integer age;
	private String email;
	private String jobTitle;
	private String careerLvl;
	private String location;


}
