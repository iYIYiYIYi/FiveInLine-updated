package yihuang.FiveInLine;

public class DataTypes {
	
	protected static chesses chessMap[][] = new chesses[15][15];

	//define chess map
	protected static void cleanMap() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				chessMap[j][i] = null;
			}
		}
	}
	
	protected static void setMap(chesses chessType,int loc1,int loc2) {
		if(empty(loc1,loc2)){
			chessMap[loc1][loc2] = chessType;
			Judger.positionX.push(loc1);
			Judger.positionY.push(loc2);
			count++;
		}
	}
	
	protected static void cancelMap(int loc1,int loc2) {
		chessMap[loc1][loc2] = null;
		count--;
	}
	
	private static boolean empty(int loc1,int loc2) {
		if(chessMap[loc1][loc2]==null) {
			return true;
		}else {
			return false;
		}
	}
	
	public static int count = 0;
	//---counter ,to find which kind of chess is now being set
	
	public static chesses judge() {
		int i,j;
		for(i=0;i<15;i++) {
			for(j=0;j<15;j++) {

		//----------------below judging white
				
				if(i<=10&&j<15&&
						chessMap[i][j]==chesses.white&&
						chessMap[i+1][j]==chesses.white&&
						chessMap[i+2][j]==chesses.white&&
						chessMap[i+3][j]==chesses.white&&
						chessMap[i+4][j]==chesses.white) {
						return chesses.white;
						}
				
				if(i<15&&j<=10&&
						chessMap[i][j]==chesses.white&&
						chessMap[i][j+1]==chesses.white&&
						chessMap[i][j+2]==chesses.white&&
						chessMap[i][j+3]==chesses.white&&
						chessMap[i][j+4]==chesses.white) {
							return chesses.white;
						}
				//-------straight 
				
				if(i<=10&&j<=10&&
						chessMap[i][j]==chesses.white&&
						chessMap[i+1][j+1]==chesses.white&&
						chessMap[i+2][j+2]==chesses.white&&
						chessMap[i+3][j+3]==chesses.white&&
						chessMap[i+4][j+4]==chesses.white) {
							return chesses.white;
						}
				
				if(i>=4&&j<=10&&
						chessMap[i][j]==chesses.white&&
						chessMap[i-1][j+1]==chesses.white&&
						chessMap[i-2][j+2]==chesses.white&&
						chessMap[i-3][j+3]==chesses.white&&
						chessMap[i-4][j+4]==chesses.white) {
							return chesses.white;
						}
				//-------oblique
				
		//-----------------below judging black
				
				if(i<=10&&j<15&&
						chessMap[i][j]==chesses.black&&
						chessMap[i+1][j]==chesses.black&&
						chessMap[i+2][j]==chesses.black&&
						chessMap[i+3][j]==chesses.black&&
						chessMap[i+4][j]==chesses.black) {
							return chesses.black;
						}
				
				if(i<15&&j<=10&&
						chessMap[i][j]==chesses.black&&
						chessMap[i][j+1]==chesses.black&&
						chessMap[i][j+2]==chesses.black&&
						chessMap[i][j+3]==chesses.black&&
						chessMap[i][j+4]==chesses.black) {
							return chesses.black;
						}
				//-------straight 
				
				if(i<=10&&j<=10&&
						chessMap[i][j]==chesses.black&&
						chessMap[i+1][j+1]==chesses.black&&
						chessMap[i+2][j+2]==chesses.black&&
						chessMap[i+3][j+3]==chesses.black&&
						chessMap[i+4][j+4]==chesses.black) {
							return chesses.black;
						}
				
				if(i>=4&&j<=10&&
						chessMap[i][j]==chesses.black&&
						chessMap[i-1][j+1]==chesses.black&&
						chessMap[i-2][j+2]==chesses.black&&
						chessMap[i-3][j+3]==chesses.black&&
						chessMap[i-4][j+4]==chesses.black) {
							return chesses.black;
						}
				//-------oblique				
			}
		}

		return null;
	}

}
