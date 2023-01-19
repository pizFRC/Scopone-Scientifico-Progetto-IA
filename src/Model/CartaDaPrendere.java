package Model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("DaPrendere")
public class CartaDaPrendere {
	@Param(1)
	private String seed;
	@Param(0)
	private Integer value;
	
	@Param(3)
	private String seed2;
	@Param(2)
	private Integer value2;
	
	public CartaDaPrendere() {
		
	}
	public CartaDaPrendere(String seed,Integer value) {
		this.seed=seed;
		this.value=value;
	}
	
	
	public String getSeed() {
		return seed;
	}
	public void setSeed(String seed) {
		this.seed = seed;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getSeed2() {
		return seed2;
	}
	public void setSeed2(String seed) {
		this.seed2 = seed;
	}
	public Integer getValue2() {
		return value2;
	}
	public void setValue2(Integer value) {
		this.value2 = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seed == null) ? 0 : seed.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaDaPrendere other = (CartaDaPrendere) obj;
		if (seed == null) {
			if (other.seed != null)
				return false;
		} else if (!seed.equals(other.seed))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DaPrendere("+this.value.toString()+","+this.seed+","+ this.value2.toString()+","+this.seed2+")";
	}
}
