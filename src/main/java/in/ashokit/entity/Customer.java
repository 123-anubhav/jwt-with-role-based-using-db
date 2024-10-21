package in.ashokit.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	private String uname;
	private String pwd;
	private Long phno;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "customer_roles", joinColumns = @JoinColumn(name = "customer_id"))
	@Column(name = "role")
	private Set<String> roles; // Add roles

	// Getters and setters
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", uname=" + uname + ", pwd=" + pwd + ", phno=" + phno + ", roles=" + roles
				+ "]";
	}

}
