package models;

import java.util.*;
import javax.persistence.*;
import play.db.jpa.*;

@Entity
/* The @Entity annotation marks this class as a managed JPA entity, 
 * and the Model superclass automatically provides a set of useful JPA helpers. 
 * All fields of this class will be automatically persisted to the database. 
 */
public class User extends Model {
	public String email;
	public String password;
	public String fullName;
	public boolean isAdmin;
	
	public User(String email, String password, String fullname) {
		this.email = email;
		this.password = password;
		this.fullName = fullname;
	}
	
	public static User connect(String email, String password) {
		return find("byEmailAndPassword", email, password).first();
	}
}
