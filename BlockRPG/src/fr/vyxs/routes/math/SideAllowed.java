package fr.vyxs.routes.math;

public class SideAllowed extends Boolean4D {

	public SideAllowed(boolean b, boolean c, boolean d, boolean e) {
		super(b, c, d, e);
		// TODO Auto-generated constructor stub
	}
	
	public SideAllowed(Number i, Number j, Number k, Number l) {
		super(i, j, k, l);
	}

	public SideAllowed(Boolean4D sum) {
		super(sum.getX(), sum.getY(), sum.getZ(), sum.getW());
	}

	public SideAllowed() {
		super();
	}

	public boolean isTopAllowed() {
		return this.getX();
	}
	
	public boolean isBottomAllowed() {
		return this.getZ();
	}
	
	public boolean isRightAllowed() {
		return this.getY();
	}
	
	public boolean isLeftAllowed() {
		return this.getW();
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

	public int sumOfTrue() {
		int sum = 0;
		sum += x ? 1 : 0;
		sum += y ? 1 : 0;
		sum += z ? 1 : 0;
		sum += w ? 1 : 0;
		return sum;
	}
}
