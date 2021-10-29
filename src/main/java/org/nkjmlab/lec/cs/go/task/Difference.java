package org.nkjmlab.lec.cs.go.task;

public class Difference {

	/**
	 * 縦方向
	 */
	public final int y;
	/**
	 * 横方向
	 */
	public final int x;
	/**
	 * 期待される正答
	 */
	public final PointState expected;
	/**
	 * プログラムが実際に判定した答え
	 */
	public final PointState actual;

	public Difference(int x, int y, PointState expected, PointState actual) {
		this.y = y;
		this.x = x;
		this.actual = actual;
		this.expected = expected;
	}

	@Override
	public String toString() {
		return "Difference [y=" + y + ", x=" + x + ", actual=" + actual + ", expected=" + expected + "]";
	}

}
