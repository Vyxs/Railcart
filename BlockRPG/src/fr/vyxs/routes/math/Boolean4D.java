package fr.vyxs.routes.math;

public class Boolean4D extends Vector4D<Boolean> {

	public Boolean4D() {
		super(false, false, false, false);
	}
	
	public Boolean4D(Number x, Number y, Number z, Number w) {
		super(x.equals(0) ? false : true, 
			  y.equals(0) ? false : true,
			  z.equals(0) ? false : true,
			  w.equals(0) ? false : true);
}

	public Boolean4D(boolean b, boolean c, boolean d, boolean e) {
		super(b, c, d, e);
	}

	public static Boolean4D sum(Boolean4D... vector) {
		Boolean4D sum = new Boolean4D();
		for (Boolean4D v : vector)
			sum.add(v);
		return sum;
	}

	private void add(Boolean4D v) {
		this.x = v.getX() == true ? true : this.x;
		this.y = v.getY() == true ? true : this.y;
		this.w = v.getW() == true ? true : this.w;
		this.z = v.getZ() == true ? true : this.z;
	}

	public Boolean getBottom() {
		return z;
	}

	public Boolean getLeft() {
		return w;
	}

	public Boolean getTop() {
		return x;
	}

	public Boolean getRight() {
		return y;
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final SideAllowed other = (SideAllowed) obj;
       
        if (other.hashCode() != this.hashCode())
        	return false;
        
        if (other.getW() != w || other.getX() != x || other.getY() != y || other.getW() != w)
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.x != false ? 2 : 0);
        hash = 53 * hash + (this.y != false ? 4 : 0);
        hash = 53 * hash + (this.z != false ? 8 : 0);
        hash = 53 * hash + (this.w != false ? 16 : 0);
        return hash;
    }
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append('[')
				.append(x ? '1' : '0')
				.append(y ? '1' : '0')
				.append(z ? '1' : '0')
				.append(w ? '1' : '0')
				.append(']')
				.toString();
	}
}
