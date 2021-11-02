package org.nkjmlab.lec.cs.go.task;
import static org.nkjmlab.lec.cs.go.task.PointState.*;
import java.util.Arrays;
import java.util.ArrayList;

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
		
		ArrayList<int[]> blackStones=new ArrayList<>();
		ArrayList<int[]> whiteStones=new ArrayList<>();
		ArrayList<int[]> blackTerritory=new ArrayList<>();
		ArrayList<int[]> whiteTerritory=new ArrayList<>();
		ArrayList<int[]> undefined=new ArrayList<>();
		//黒石、白石の位置を探し、保存
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[0].length;j++) {
				if(result[i][j]==WHITE) {
					int[] point = {i,j};
					whiteStones.add(point);
				}
				if(result[i][j]==BLACK) {
					int[] point = {i,j};
					blackStones.add(point);
				}
			}
		}
		//１：白石の隣のデフォルト目を『仮白地』と設定
		for(int i=0;i<whiteStones.size();i++) {
			int[] point=whiteStones.get(i);
			onSide(result,point[0],point[1],BLANK,WHITE_TERRITORY);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		
		//2：黒石の隣のデフォルト目を『仮黒地』、仮白地を『無地』に設定
		for(int i=0;i<blackStones.size();i++) {
			int[] point=blackStones.get(i);
			onSide(result,point[0],point[1],BLANK,BLACK_TERRITORY);
			onSide(result,point[0],point[1],WHITE_TERRITORY,UNDEFINED);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		
		//3:仮白地の隣のデフォルト目を仮白地にする。をできなくなるまで繰り返す
		for(int i=0;i<whiteTerritory.size();i++) {
			int[] point=whiteTerritory.get(i);
			onSide(result,point[0],point[1],BLANK,WHITE_TERRITORY);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		
		//4:仮黒地の隣のデフォルト目を仮黒地にする。をできなくなるまで繰り返す。
		for(int i=0;i<blackTerritory.size();i++) {
			int[] point=blackTerritory.get(i);
			onSide(result,point[0],point[1],BLANK,BLACK_TERRITORY);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		//隣接する仮黒地と仮白地を『無地』に設定する
		ArrayList<int[]> toN_point = new ArrayList<>();
			//黒地に隣接する白地を取得
		for(int i=0;i<blackTerritory.size();i++) {
			int[] point=blackTerritory.get(i);
			ArrayList<int[]> w_point = onSide(result,point[0],point[1],WHITE_TERRITORY,WHITE_TERRITORY);
			if(w_point.size()>0) {
				toN_point.add(point);
			}
		}
			//白地に隣接する白地を取得
		for(int i=0;i<whiteTerritory.size();i++) {
			int[] point=whiteTerritory.get(i);
			ArrayList<int[]> b_point = onSide(result,point[0],point[1],BLACK_TERRITORY,BLACK_TERRITORY);
			if(b_point.size()>0) {
				toN_point.add(point);
			}
		}
			//取得した地を『無地』に設定
		for(int i=0;i<toN_point.size();i++) {
			int[] point=toN_point.get(i);
			result[point[0]][point[1]]=UNDEFINED;
			ArrayList<int[]> chenge_Point= new ArrayList<int[]>();
			chenge_Point.add(point);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		
		//5:無地の隣の石の無い目を無地にする。をできなくなるまで繰り返す。
		for(int i=0;i<undefined.size();i++) {
			int[] point=undefined.get(i);
			//黒地を無地に
			onSide(result,point[0],point[1],BLACK_TERRITORY,UNDEFINED);
			//白地を無地に
			onSide(result,point[0],point[1],WHITE_TERRITORY,UNDEFINED);
			//arrayList更新
			listUpdate(result,blackTerritory,whiteTerritory,undefined);
		}
		
		/*チェック用
		//6:最後に仮白地と仮黒地を数え上げ、それぞれの数を白地と黒地の数とする
		System.out.println("計算した黒地："+blackTerritory.size());
		System.out.println("計算した白地："+whiteTerritory.size());
		PointState[][] board = result;
		for(int y=0;y<board.length;y++) {
			for(int x=0;x<board[0].length;x++) {
				if(x>0) {
					System.out.print(":");
				}
				System.out.print(board[y][x]);
			}
			System.out.println();
		}
		System.out.println();
		*/
		
		return new EvaluatedResult(result);
	}
	
	//指定した座標の隣が指定したstateBeforeであれば,隣座標をstateAfterに変更する。
	//変換した座標のArrayListを返す
	private ArrayList<int[]> onSide(PointState[][] result,int x,int y,PointState stateBefore,PointState stateAfter) {
		ArrayList<int[]> chenge_XYs=new ArrayList<>();
		for(int i=-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				//斜めと自分の場所を取り除く
				if(i!=0&&j!=0 || i==0&&j==0) {
					continue;
				}
				if(x+i<0||y+j<0) {
					continue;
				}
				if(x+i>=result[0].length||y+j>=result.length) {
					continue;
				}
				//System.out.println(i+","+j);
				//上下左右のstateBをstateAに変更
				if(result[x+i][y+j]==stateBefore) {
					result[x+i][y+j]=stateAfter;
					int[] point= {x+i,y+j};
					chenge_XYs.add(point);
				}
			}
		}
		return chenge_XYs;
	}
	
	//resultから各Listを更新する
	private void listUpdate(PointState[][] result,ArrayList<int[]> black,ArrayList<int[]> white,ArrayList<int[]> undefined) {
		ArrayList<int[]> chengePoints = new ArrayList<>();
		//変更があった座標を抜き出す
		for(int i=0;i<result.length;i++) {
			for(int j=0;j<result[0].length;j++) {
				boolean on = false;
				int[] checkPoint = {i,j};
				switch(result[i][j]) {
				case BLACK_TERRITORY:for(int[] point:black) {if(Arrays.equals(point,checkPoint)) {on=true;}}break;
				case WHITE_TERRITORY:for(int[] point:white) {if(Arrays.equals(point,checkPoint)) {on=true;}}break;
				case UNDEFINED:for(int[] point:undefined) {if(Arrays.equals(point,checkPoint)) {on=true;}}break;
				default:break;
				}
				if(on==false)chengePoints.add(checkPoint);
			}
		}
		
		//変更があった座標を、各LISTから削除＆追加する
		for(int i=0;i<chengePoints.size();i++) {
			int[] point = chengePoints.get(i);
			switch(result[point[0]][point[1]]) {
			case BLACK_TERRITORY:black.add(chengePoints.get(i));white.removeIf(p -> Arrays.equals(p,point));undefined.removeIf(p -> Arrays.equals(p,point));break;
			case WHITE_TERRITORY:white.add(chengePoints.get(i));black.removeIf(p -> Arrays.equals(p,point));undefined.removeIf(p -> Arrays.equals(p,point));break;
			case UNDEFINED:undefined.add(chengePoints.get(i));white.removeIf(p -> Arrays.equals(p,point));black.removeIf(p -> Arrays.equals(p,point));break;
			default :break;
			}
		}
	}
}
