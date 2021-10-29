package org.nkjmlab.lec.cs.go.task;

public class TerritoryEvaluator {

	public static void main(String[] args) {
		PointStatesProvider provider = new PointStatesProvider();
		PointState[][] problem = provider.getProblem(0);
		PointState[][] correctAnswer = provider.getCorrectAnswer(0);

		TerritoryEvaluator evaluator = new TerritoryEvaluator();
		EvaluatedResult result = evaluator.evaluateTerritory(problem);
		System.out.println(result.getMessage());

		if (result.isEqual(correctAnswer)) {
			System.out.println("答えと一致しています．");
		} else {
			System.err.println("答えと一致していません．一致していない箇所は以下です=" + result.getDifferences(correctAnswer));
		}

	}

	/**
	 * 与えられた盤面の状態を評価した結果を返す．
	 *
	 * @param pointStates
	 * @return
	 */
	public EvaluatedResult evaluateTerritory(PointState[][] pointStates) {
		PointState[][] result = pointStates;
		return new EvaluatedResult(result);
	}

}
