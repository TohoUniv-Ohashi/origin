package org.nkjmlab.lec.cs.go.task;

import static org.nkjmlab.lec.cs.go.task.PointState.*;

import java.util.List;

/**
 * 盤面の横方向をx，縦方向をyとする．盤面の左端をx=0とし右端をx=8とする．盤面の上端をy=0とし，下端をy=8とする．
 * PointState[][]はPointState[y][x]とする．すなわち，下図の○はPointState[1][2]と表すものとする．
 *
 * <pre>
 * ●●●●
 * ●●○●
 * ●●●●
 * </pre>
 *
 * @author nkjm
 *
 */
public class PointStatesProvider {

	private final PointState[][] problem0 = { { BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK },
			{ BLANK, BLANK, BLANK, BLANK, BLACK, WHITE, BLANK, BLANK, BLANK } };

	private final PointState[][] correctAnswer0 = {
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, },
			{ BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK_TERRITORY, BLACK, WHITE, WHITE_TERRITORY,
					WHITE_TERRITORY, WHITE_TERRITORY, } };

	private final int[][] problem1 = { { 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 2, 0, 0, 0 } };

	private final int[][] correctAnswer1 = { { 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, },
			{ 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, },
			{ 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, },
			{ 3, 3, 3, 3, 1, 2, 4, 4, 4, } };

	private final int[][] problem2 = { { 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 0, 0, 0, 1, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 1, 2, 0, 0, 0 }, { 0, 1, 1, 1, 1, 2, 2, 2, 2 }, { 1, 2, 2, 2, 2, 1, 1, 1, 1 },
			{ 2, 0, 0, 0, 2, 1, 0, 0, 0 }, { 0, 0, 0, 0, 2, 1, 0, 0, 0 }, { 0, 0, 0, 0, 2, 1, 0, 0, 0 },
			{ 0, 0, 0, 0, 2, 1, 0, 0, 0 } };

	private final int[][] correctAnswer2 = { { 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 3, 3, 3, 1, 2, 4, 4, 4, },
			{ 3, 3, 3, 3, 1, 2, 4, 4, 4, }, { 3, 1, 1, 1, 1, 2, 2, 2, 2, }, { 1, 2, 2, 2, 2, 1, 1, 1, 1, },
			{ 2, 4, 4, 4, 2, 1, 3, 3, 3, }, { 4, 4, 4, 4, 2, 1, 3, 3, 3, }, { 4, 4, 4, 4, 2, 1, 3, 3, 3, },
			{ 4, 4, 4, 4, 2, 1, 3, 3, 3, } };

	private final int[][] problem3 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 1, 0, 1, 0, 0, 0, 0 }, { 1, 1, 0, 1, 0, 1, 0, 1, 1 }, { 2, 1, 1, 2, 1, 1, 1, 1, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2 }, { 0, 0, 0, 0, 2, 0, 2, 0, 2 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

	private final int[][] correctAnswer3 = { { 3, 3, 3, 3, 3, 3, 3, 3, 3, }, { 3, 3, 3, 3, 3, 3, 3, 3, 3, },
			{ 3, 3, 1, 3, 1, 3, 3, 3, 3, }, { 1, 1, 3, 1, 3, 1, 3, 1, 1, }, { 2, 1, 1, 2, 1, 1, 1, 1, 2, },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, }, { 4, 4, 4, 4, 2, 4, 2, 4, 2, }, { 4, 4, 4, 4, 4, 4, 4, 4, 4, },
			{ 4, 4, 4, 4, 4, 4, 4, 4, 4, } };

	/**
	 * 課題: 問題idのリストを返す．
	 *
	 * @return
	 */
	public List<Integer> getProblemIds() {
		return List.of();
	}

	/**
	 * idに対応する盤面状態を取得する．
	 *
	 * @TODO
	 * @param id
	 * @return
	 */
	public PointState[][] getProblem(int id) {
		return problem0;
	}

	/**
	 * idに対応する盤面を評価したときの正答を取得する．
	 *
	 * @TODO
	 * @param id
	 * @return
	 */
	public PointState[][] getCorrectAnswer(int id) {
		return correctAnswer0;
	}

	/**
	 * 与えられたint[][]をPointState[][]に変換する.
	 *
	 * @TODO
	 * @param cells
	 * @return
	 */
	public static PointState[][] convert(int[][] cells) {
		return null;
	}

}
