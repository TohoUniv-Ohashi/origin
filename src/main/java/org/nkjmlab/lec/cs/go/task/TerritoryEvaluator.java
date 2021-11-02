package org.nkjmlab.lec.cs.go.task;

import static org.nkjmlab.lec.cs.go.task.PointState.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	boolean[][] checked;
	/**
	 * 与えられた盤面の状態を評価した結果を返す．
	 *
	 * @param pointStates
	 * @return
	 */
	public EvaluatedResult evaluateTerritory(PointState[][] pointStates) {
	      checked = new boolean[pointStates.length][pointStates.length];

	      for(int i = 0;i < pointStates.length; i++){
	        for(int j = 0;j < pointStates.length; j++){
	          if(pointStates[j][i] != BLANK) continue;
	          checkStart(i,j,pointStates);
	        }
	      }
	      //igoPrint(pointStates);
	      return new EvaluatedResult(pointStates);
	  }

	  void checkStart(int x,int y, PointState[][] pointStates){
	    PointState state = check(x,y,pointStates);
	    for(int i = 0;i < pointStates.length; i++){
	      for(int j = 0;j < pointStates.length; j++){
	        if(checked[j][i] && pointStates[j][i]  == BLANK){
				if(state == BLACK) pointStates[j][i] = BLACK_TERRITORY;
				else if(state == WHITE) pointStates[j][i]  = WHITE_TERRITORY;
				else if(state == UNDEFINED) pointStates[j][i] = UNDEFINED;
	        }
	        checked[j][i] = false;
	      }
	    }
	  }
	  public void igoPrint(PointState[][] f){
	    for(int i = 0;i < f.length; i++){
	      for(int j = 0;j < f.length; j++){

	        switch(f[i][j]){
	          case BLANK:System.out.print("・");break;
	          case BLACK:System.out.print("〇");break;
	          case WHITE:System.out.print("●");break;
	          case BLACK_TERRITORY:System.out.print("黒");break;
	          case WHITE_TERRITORY:System.out.print("白");break;
	          case UNDEFINED:System.out.print("？");break;
	          default:break;
	        }
	      }
	      System.out.println("");
	    }
	  }
	  PointState check(int x,int y, PointState[][] pointStates){
		checked[y][x] = true;
	    return compare(checkUp(x,y,pointStates),compare(checkDown(x,y,pointStates),compare(checkRight(x,y,pointStates),checkLeft(x,y,pointStates))));
	  }
	  PointState compare(PointState a, PointState b){
	      if(a == BLANK) return b;

	      if(b == BLACK){
	        if(a == WHITE) return UNDEFINED;
	        else return a;
	      }
	      if(b == WHITE){
	        if(a == BLACK) return UNDEFINED;
	        else return a;
	      }
	      
	      return a;
	  }

	  PointState checkUp(int x,int y,PointState[][] data){
	    return checkPoint(x,y-1,data);
	  }

	  PointState checkDown(int x,int y,PointState[][] data){
	    return checkPoint(x,y+1,data);
	  }

	  PointState checkRight(int x,int y,PointState[][] data){
	    return checkPoint(x+1,y,data);
	  }

	  PointState checkLeft(int x,int y,PointState[][] data){
	    return checkPoint(x-1,y,data);
	  }

	  PointState checkPoint(int x,int y,PointState[][] data){
	    if(y < 0 || y >= data.length || x < 0 || x >= data.length || checked[y][x]){
	      return BLANK;
	    }
	    if(data[y][x]==BLANK){
	      return check(x,y,data);
	    }
	    else{
		  checked[y][x] = true;
	      return data[y][x];
	    }
	  }
}
