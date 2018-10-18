package cc.p1.AuxiliaryTools;

/**
 * Auxiliary class to store two kind of objects.
 * 
 * @author Daute Rodríguez Rodríguez (alu0100973914@ull.edu.es)
 * @version 1.0
 * @since 10 oct. 2018
 */
public class Pair<L, R>
{
	/** Left object */
	private final L	left;
	/** Right object */
	private final R	right;

	/**
	 * Constructor
	 * 
	 * @param left
	 * @param right
	 */
	public Pair(L left, R right)
	{
		this.left = left;
		this.right = right;
	}

	/**
	 * Getter method for the left object
	 * 
	 * @return Left object
	 */
	public L getLeft()
	{
		return left;
	}

	/**
	 * Getter method for the right object
	 * 
	 * @return Right object
	 */
	public R getRight()
	{
		return right;
	}

	
	@Override
	public int hashCode()
	{
		return left.hashCode() ^ right.hashCode();
	}

	
	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof Pair))
			return false;
		Pair pairObject = (Pair) o;
		return this.left.equals(pairObject.getLeft())
				&& this.right.equals(pairObject.getRight());
	}

	@Override
	public String toString()
	{
		return "(" + left + ", " + right + ")";
	}
}