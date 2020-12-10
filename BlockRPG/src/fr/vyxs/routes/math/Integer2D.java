package fr.vyxs.routes.math;

public class Integer2D extends Vector2D<Integer> {

	public Integer2D(Integer2D position) {
		super(position.getFirst(), position.getSecond());
	}
	
	public Integer2D(Number first, Number second) {
		super(first.intValue(), second.intValue());
	}

	public Integer2D() {
		super(0, 0);
	}

	public void set(Integer2D pos) {
		this.first = pos.getFirst();
		this.second = pos.getSecond();
	}
	
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Integer2D other = (Integer2D) obj;
       
        if (other.hashCode() != this.hashCode())
        	return false;
        
        if (other.getFirst() != first || other.getSecond() != second)
        	return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 76;
        hash = 53 * hash + this.first;
        hash = 53 * hash + this.second;
        return hash;
    }
	
	@Override
	public String toString() {
		return String.format("{%s;%s}", first, second);
	}

	@Override
	public Vector2D<Integer> getDifference(Vector2D<Integer> target) {
		final boolean isFirstBigger = first > target.getFirst();
		final boolean isSecondBigger = second > target.getSecond();
		final boolean isTargetFirstBigger = target.getFirst() > first;
		final boolean isTargetSecondBigger = target.getSecond() > second;
		
		int x = 0, y = 0;
		
		if (isFirstBigger)
			x = first - target.getFirst();
		if (isTargetFirstBigger)
			x = target.getFirst() - first;
		if (isSecondBigger)
			y = second - target.getSecond();
		if (isTargetSecondBigger)
			y = target.getSecond() - second;
		return new Integer2D(x, y);
	}

	public Integer2D getSum(Integer2D offset) {
		return new Integer2D(first + offset.getFirst(), second + getSecond());
	}
	
	public boolean isZero() {
		return first.intValue() == 0 && second.intValue() == 0;
	}

	public Integer2D normalize() {
		int x = first, y = second;
		x = x > 1 ? 1 : x;
		x = x < -1 ? -1 : x;
		y = y > 1 ? 1 : y;
		y = y < -1 ? -1 : y;
		return new Integer2D(x, y);
	}

}
