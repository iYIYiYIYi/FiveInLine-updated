package yihuang.FiveInLine;

import java.util.EmptyStackException;
import java.util.Stack;

public class Judger {

	protected static chesses chesstype;
	protected static GameMode gamemode;

	static multiple mul = new multiple();
	static local localplayer = new local();
	//static online ol = new online();
	protected static Stack<Integer> positionX = new Stack<>();
	protected static Stack<Integer> positionY = new Stack<>();

	//---set chess type
	void setChessType(chesses chessType) {
		this.chesstype=chessType;
	}
	//---set game mode
	void setGameMode(GameMode gamemode) {
		this.gamemode=gamemode;
	}

	protected static boolean run(int x, int y) {
		if (gamemode==GameMode.local&&local(x,y)) {
			return true;
		} else if (gamemode==GameMode.multiple&&multiple(x,y)) {
			return true;
		} else if (gamemode == GameMode.online) {
	//		finishing
		}
		return false;
	}

	protected static void Cancel() {
		int x,y,x1,y1;
		try {
			x = positionX.pop();
			y = positionY.pop();
			DataTypes.cancelMap(x, y);
			x1 = positionX.pop();
			y1 = positionY.pop();
			DataTypes.cancelMap(x1, y1);
		} catch (EmptyStackException error) {
			if(error != null)
				System.out.println("不能再悔棋了");
		}
	}

	private static boolean local(int loc1, int loc2) {
		if(chesstype==chesses.white&&localplayer.whiteLocal(loc1, loc2)) {
			return true;
		}
		return false;
	}

	private static boolean multiple(int loc1,int loc2) {
		//local multiple
		if (mul.multi(loc1, loc2)) {
			return true;
		}
		return false;
	}

	private static void online() {
		//finishing
	}
}		

class local {
	//initialize AI
	AIofFIL AI = new AIofFIL();
	
	//---white | first 先手 正常
	boolean whiteLocal(int loc1,int loc2) {
		DataTypes.setMap(chesses.white, loc1, loc2);
		if (DataTypes.judge() != null){
			chesses j = DataTypes.judge();
			String s = null;
			if (j == chesses.black) {
				s = "黑色棋子获胜";
			}
			if (j == chesses.white) {
				s = "白色棋子获胜";
			}
			System.out.println(j+s);
			endGame end = new endGame(" "+s);
			return false;
		}
		AI.main(chesses.black);
		if (DataTypes.judge() != null) {
			chesses j = DataTypes.judge();
			String s = null;
			if (j == chesses.black) {
				s = "黑色棋子获胜";
			}
			if (j == chesses.white) {
				s = "白色棋子获胜";
			}
			System.out.println(j+s);
			endGame end = new endGame(" "+s);
			return false;
		}
		return true;
	}
	//---black | second 后手 无法初始化AI |已被删除
}

class multiple {
	//local multiple 
	void whitemultiple(int loc1,int loc2) {
		DataTypes.setMap(chesses.white, loc1, loc2);	
	}
	void blackmultiple(int loc1,int loc2){
		DataTypes.setMap(chesses.black, loc1, loc2);
	}
	
	boolean multi(int loc1,int loc2) {
		int judgenum=DataTypes.count%2;
		System.out.println(DataTypes.count);
		if(judgenum==0) {
			whitemultiple(loc1, loc2);
		}
		if (judgenum == 1) {
			blackmultiple(loc1, loc2);
		}
		if (DataTypes.judge() == null) {
			return true;
		} else if (DataTypes.judge() != null) {
			chesses j = DataTypes.judge();
			String s = null;
			if (j == chesses.black) {
				s = "黑色棋子获胜";
			}
			if (j == chesses.white) {
				s = "白色棋子获胜";
			}
			System.out.println(j + s);
			endGame end = new endGame(" " + s);
			return false;
		}
		return false;
	}
}

class online {
	public online() {

	}
}