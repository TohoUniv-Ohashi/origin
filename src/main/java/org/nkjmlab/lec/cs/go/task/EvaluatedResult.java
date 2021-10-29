package org.nkjmlab.lec.cs.go.task;

import static org.nkjmlab.lec.cs.go.task.PointState.*;

import java.util.List;

public class EvaluatedResult {

	private final PointState[][] result;

	public EvaluatedResult(PointState[][] result) {
		PointState[][] stub = {
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

		this.result = stub;
	}

	/**
	 * 黒の地の数を返す
	 *
	 * @return
	 */
	public int countBlackTerritory() {
		return 0;
	}

	/**
	 * 白の地の数を返す
	 *
	 * @return
	 */
	public int countWhiteTerritory() {
		return 0;
	}

	/**
	 * 評価結果を表すメッセージを返す．
	 *
	 * @return
	 */
	public String getMessage() {
		return "";
	}

	/**
	 * 引数と同一かを調べて，真偽を返す．
	 *
	 * @param result
	 * @return
	 */
	public boolean isEqual(PointState[][] pointStates) {
		return false;
	}

	/**
	 * 自分の評価結果と引数が一致しない場合，差分を返す．全て一致した場合は，空のListを返す．
	 *
	 * @param pointStates
	 * @return
	 */
	public List<Difference> getDifferences(PointState[][] pointStates) {

		return List.of();
	}

	public PointState[][] getPointStates() {
		return result;
	}

}
