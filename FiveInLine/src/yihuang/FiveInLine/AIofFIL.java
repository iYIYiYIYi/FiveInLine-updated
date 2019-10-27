package yihuang.FiveInLine;

import java.util.LinkedList;

class AIofFIL {
	
	private static int[][] chessMapAi = new int[15][15];
	//AIChessMap is used to convenient the calculation
	private static int AiChessType;
	
	protected void main(chesses chessType) {

		if(chessType==chesses.black) {
			AiChessType = 1;
		}
		if(chessType==chesses.white) {
			AiChessType = -1;
		}//initialize AI chess type

		cleanMap();
		getAImap();//initialize AI chess map
		
		int[] XandY = calculate();//initialize loc1 and loc2
		
		setAiMap(XandY[0], XandY[1]);//set chess map
	}
	
	private void getAImap() {
		int i,j;
		for(i=0;i<15;i++) {
			for(j=0;j<15;j++) {
				if(DataTypes.chessMap[i][j]==chesses.black) {
					chessMapAi[i][j]=1;
				}else if(DataTypes.chessMap[i][j]==chesses.white) {
					chessMapAi[i][j]=-1;
				}
			}
		}//initialize AI chess map
	}


	private static int[][][] Locs = new int[15][15][3];//black:Score,X,Y
	private static int[][][] Locw = new int[15][15][3];//white:Score,X,Y
	private static int[][][] ValueMap = new int[15][15][3];

	private int[] calculate() {
		int[] XandY = new int[2];
		
		//calculation method | white < 0||black > 0|
		final int FiveB = 100000;
		final int FiveW = 100000;
		final int FourB = 800;
		final int FourW = 800;
		final int ThreeB = 370;
		final int ThreeW = 380;
		final int TwoB = 160;
		final int TwoW = 130;
		final int OneB = 70;
		final int OneW = 50;
		final int minus = 45;


                /*
                 * now we need to import an AI to this game,
                 * this is a judge system
                 * which scans every dot in the chessmap and then give it a score
                 * which stores in a vector array
                 */
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {

			    if (chessMapAi[j][i]==0) {

//			        FiveB condition
                    if ((i<=10
                            && chessMapAi[j][i + 1] == 1
                            && chessMapAi[j][i + 2] == 1
                            && chessMapAi[j][i + 3] == 1
                            && chessMapAi[j][i + 4] == 1) ||
                            (i<=11&&i>=1
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i + 1] == 1
                                    && chessMapAi[j][i + 2] == 1
                                    && chessMapAi[j][i + 3] == 1)||
                            (i>=2&&i<=12
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1
                                    && chessMapAi[j][i + 1] == 1
                                    && chessMapAi[j][i + 2] == 1)||
                            (i>=3&&i<=13
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1
                                    && chessMapAi[j][i - 3] == 1
                                    && chessMapAi[j][i + 1] == 1)||
                            (i>=4&&i<=14
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1
                                    && chessMapAi[j][i - 3] == 1
                                    && chessMapAi[j][i - 4] == 1)||
                            (j<=10
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1
                                    && chessMapAi[j + 3][i] == 1
                                    && chessMapAi[j + 4][i] == 1) ||
                            (j>=1&&j<=11
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1
                                    && chessMapAi[j + 3][i] == 1) ||
                            (j>=2&&j<=12
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1) ||
                            (j>=3&&j<=13
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1
                                    && chessMapAi[j - 3][i] == 1
                                    && chessMapAi[j + 1][i] == 1) ||
                            (j>=4&&j<=14
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1
                                    && chessMapAi[j - 3][i] == 1
                                    && chessMapAi[j - 4][i] == 1) ||
                            (i<=10&&j<=10
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1
                                    && chessMapAi[j + 3][i + 3] == 1
                                    && chessMapAi[j + 4][i + 4] == 1) ||
                            (i<=11&&j<=11&&i>=1&&j>=1
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1
                                    && chessMapAi[j + 3][i + 3] == 1)||
                            (i<=12&&j<=12&&i>=2&&j>=2
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1)||
                            (i<=13&&j<=13&&i>=3&&j>=3
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1
                                    && chessMapAi[j - 3][i - 3] == 1
                                    && chessMapAi[j + 1][i + 1] == 1)||
                            (i<=14&&j<=14&&i>=4&&j>=4
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1
                                    && chessMapAi[j - 3][i - 3] == 1
                                    && chessMapAi[j - 4][i - 4] == 1)||
                            (i>=4&&j<=10
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1
                                    && chessMapAi[j + 3][i - 3] == 1
                                    && chessMapAi[j + 4][i - 4] == 1) ||
                            (i>=3&&j<=11&&i<=13&&j>=1
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1
                                    && chessMapAi[j + 3][i - 3] == 1) ||
                            (i>=2&&j<=12&&i<=12&&j>=2
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1) ||
                            (i>=1&&j<=13&&i<=11&&j>=3
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1
                                    && chessMapAi[j - 3][i + 3] == 1
                                    && chessMapAi[j + 1][i - 1] == 1) ||
                            (i<=10&&j>=4
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1
                                    && chessMapAi[j - 3][i + 3] == 1
                                    && chessMapAi[j - 4][i + 4] == 1) ) {

                        Locs[j][i][0] = j;
                        Locs[j][i][1] = i;
                        Locs[j][i][2] = Locs[j][i][2] + FourB;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
                    }

//                    FourB condition
                    if ((i<=11											//0
                            && chessMapAi[j][i + 1] == 1
                            && chessMapAi[j][i + 2] == 1
                            && chessMapAi[j][i + 3] == 1) ||
                            (i>=1&&i<=12
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i + 1] == 1
                                    && chessMapAi[j][i + 2] == 1)||
                            (i>=2&&i<=13
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1
                                    && chessMapAi[j][i + 1] == 1)||
                            (i>=3&&i<=14
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1
                                    && chessMapAi[j][i - 3] == 1)||
                            (j<=11										//1
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1
                                    && chessMapAi[j + 3][i] == 1) ||
                            (j>=1&&j<=12
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1) ||
                            (j>=2&&j<=13
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1
                                    && chessMapAi[j + 1][i] == 1) ||
                            (j>=3
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1
                                    && chessMapAi[j - 3][i] == 1) ||
                            (j<=11&&i<=11								//2
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1
                                    && chessMapAi[j + 3][i + 3] == 1) ||
                            (j<=12&&j>=1&&i<=12&&i>=1
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1)||
                            (j<=13&&j>=2&&i<=13&&i>=2
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1
                                    && chessMapAi[j + 1][i + 1] == 1)||
                            (j>=3&&i>=3
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1
                                    && chessMapAi[j - 3][i - 3] == 1)||
                            (j<=11&&i>=3								//3
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1
                                    && chessMapAi[j + 3][i - 3] == 1) ||
                            (j<=12&&j>=1&&i<=13&&i>=2
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1) ||
                            (j<=13&&j>=2&&i<=12&&i>=1
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1
                                    && chessMapAi[j + 1][i - 1] == 1) ||
                            (j>=3&&i<=11
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1
                                    && chessMapAi[j - 3][i + 3] == 1) ) {

                        Locs[j][i][0] = j;
                        Locs[j][i][1] = i;
                        Locs[j][i][2] = Locs[j][i][2] + ThreeB;

						if (i < 11
								&& chessMapAi[j][i + 1] == 1
								&& chessMapAi[j][i + 2] == 1
								&& chessMapAi[j][i + 3] == 1
								&& chessMapAi[j][i + 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (i > 0 && i < 12
								&& chessMapAi[j][i + 1] == 1
								&& chessMapAi[j][i + 2] == 1
								&& chessMapAi[j][i + 3] == 1
								&& chessMapAi[j][i - 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (i < 10&&i>1
								&& chessMapAi[j][i + 1] == 1
								&& chessMapAi[j][i + 2] == 1
								&& chessMapAi[j][i - 1] == 1
								&& chessMapAi[j][i - 2] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (i < 10&&i>=1
								&& chessMapAi[j][i + 1] == 1
								&& chessMapAi[j][i + 2] == 1
								&& chessMapAi[j][i - 1] == 1
								&& chessMapAi[j][i + 3] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (i > 3 && i <= 14
								&& chessMapAi[j][i - 1] == 1
								&& chessMapAi[j][i - 2] == 1
								&& chessMapAi[j][i - 3] == 1
								&& chessMapAi[j][i - 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (i >= 3 && i < 14
								&& chessMapAi[j][i - 1] == 1
								&& chessMapAi[j][i - 2] == 1
								&& chessMapAi[j][i - 3] == 1
								&& chessMapAi[j][i + 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}										//sign
						if (j < 11
								&& chessMapAi[j + 1][i] == 1
								&& chessMapAi[j + 2][i] == 1
								&& chessMapAi[j + 3][i] == 1
								&& chessMapAi[j + 4][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (j > 0 && j < 12
								&& chessMapAi[j + 1][i] == 1
								&& chessMapAi[j + 2][i] == 1
								&& chessMapAi[j + 3][i] == 1
								&& chessMapAi[j - 1][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (j < 10&&j>1
								&& chessMapAi[j + 1][i] == 1
								&& chessMapAi[j + 2][i] == 1
								&& chessMapAi[j - 1][i] == 1
								&& chessMapAi[j - 2][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (j < 10&&j>=1
								&& chessMapAi[j + 1][i] == 1
								&& chessMapAi[j + 2][i] == 1
								&& chessMapAi[j - 1][i] == 1
								&& chessMapAi[j + 3][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] -minus;
						}
						if (j > 3 && j <= 14
								&& chessMapAi[j - 1][i] == 1
								&& chessMapAi[j - 2][i] == 1
								&& chessMapAi[j - 3][i] == 1
								&& chessMapAi[j - 4][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j >= 3 && j < 14
								&& chessMapAi[j - 1][i] == 1
								&& chessMapAi[j - 2][i] == 1
								&& chessMapAi[j - 3][i] == 1
								&& chessMapAi[j + 1][i] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}										//another sign
						if (j < 11 && i < 11
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j + 2][i + 2] == 1
								&& chessMapAi[j + 3][i + 3] == 1
								&& chessMapAi[j + 4][i + 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j > 0 && i >0 && j<=11 && i <=11
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j + 2][i + 2] == 1
								&& chessMapAi[j + 3][i + 3] == 1
								&& chessMapAi[j - 1][i - 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j < 12 && j >= 1 && i < 12 && i >= 1
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j + 2][i + 2] == 1
								&& chessMapAi[j + 3][i + 3] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j <= 12 && j > 1 && i <= 12 && i > 1
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j + 2][i + 2] == 1
								&& chessMapAi[j - 2][i - 2] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j < 13 && j >= 2 && i < 13 && i >= 2
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j - 2][i - 2] == 1
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j + 2][i + 2] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j <= 13 && j > 2 && i <= 13 && i > 2
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j - 2][i - 2] == 1
								&& chessMapAi[j + 1][i + 1] == 1
								&& chessMapAi[j - 3][i - 3] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j > 3 && i > 3
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j - 2][i - 2] == 1
								&& chessMapAi[j - 3][i - 3] == 1
								&& chessMapAi[j - 4][i - 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j >= 3 && i >= 3 && i <= 13 && j <= 13
								&& chessMapAi[j - 1][i - 1] == 1
								&& chessMapAi[j - 2][i - 2] == 1
								&& chessMapAi[j - 3][i - 3] == 1
								&& chessMapAi[j + 1][i + 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}											//another another sign
						if (j < 11 && i > 3
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j + 2][i - 2] == 1
								&& chessMapAi[j + 3][i - 3] == 1
								&& chessMapAi[j + 4][i - 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j <= 11 && i >= 3 && j > 3 && i < 11
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j + 2][i - 2] == 1
								&& chessMapAi[j + 3][i - 3] == 1
								&& chessMapAi[j - 1][i + 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j < 12 && j >= 1 && i <= 13 && i > 2
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j + 2][i - 2] == 1
								&& chessMapAi[j + 3][i - 3] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j <= 12 && j > 1 && i < 13 && i >= 2
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j + 2][i - 2] == 1
								&& chessMapAi[j - 2][i + 2] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j < 13 && j >= 2 && i <= 12 && i > 1
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j - 2][i + 2] == 1
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j + 2][i - 2] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j <= 13 && j > 2 && i < 12 && i >= 1
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j - 2][i + 2] == 1
								&& chessMapAi[j + 1][i - 1] == 1
								&& chessMapAi[j - 3][i + 3] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j > 3 && i < 11
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j - 2][i + 2] == 1
								&& chessMapAi[j - 3][i + 3] == 1
								&& chessMapAi[j - 4][i + 4] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if (j >= 3 && i <= 11 && j<14 && i >0
								&& chessMapAi[j - 1][i + 1] == 1
								&& chessMapAi[j - 2][i + 2] == 1
								&& chessMapAi[j - 3][i + 3] == 1
								&& chessMapAi[j + 1][i - 1] == -1) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}

                    }

//                    threeB condition
                    if ((i<=12
                            && chessMapAi[j][i + 1] == 1
                            && chessMapAi[j][i + 2] == 1) ||
                            (i>=1&&i<=13
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i + 1] == 1) ||
                            (i>=2
                                    && chessMapAi[j][i - 1] == 1
                                    && chessMapAi[j][i - 2] == 1) ||
                            (j<=12
                                    && chessMapAi[j + 1][i] == 1
                                    && chessMapAi[j + 2][i] == 1) ||
                            (j>=1&&j<=13
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j + 1][i] == 1) ||
                            (j>=2
                                    && chessMapAi[j - 1][i] == 1
                                    && chessMapAi[j - 2][i] == 1) ||
                            (j<=12&&i<=12
                                    && chessMapAi[j + 1][i + 1] == 1
                                    && chessMapAi[j + 2][i + 2] == 1) ||
                            (j<=13&&i<=13&&j>=1&&i>=1
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j + 1][i + 1] == 1) ||
                            (j>=2&&i>=2
                                    && chessMapAi[j - 1][i - 1] == 1
                                    && chessMapAi[j - 2][i - 2] == 1) ||
                            (j<=12&&i>=2
                                    && chessMapAi[j + 1][i - 1] == 1
                                    && chessMapAi[j + 2][i - 2] == 1) ||
                            (j>=1&&j<=13&&i>=1&&i<=13
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j + 1][i - 1] == 1) ||
                            (j>=2&&i<=12
                                    && chessMapAi[j - 1][i + 1] == 1
                                    && chessMapAi[j - 2][i + 2] == 1)) {

                        Locs[j][i][0] = j;
                        Locs[j][i][1] = i;
                        Locs[j][i][2] = Locs[j][i][2] + TwoB;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}

                    }

//                    twoB condition
                    if ((i<=13
                            && chessMapAi[j][i + 1] == 1) ||
                            (i>=1
                                    && chessMapAi[j][i - 1] == 1) ||
                            (j<=13
                                    && chessMapAi[j + 1][i] == 1) ||
                            (j>=1
                                    && chessMapAi[j - 1][i] == 1) ||
                            (j<=13&&i<=13
                                    && chessMapAi[j + 1][i + 1] == 1) ||
                            (j>=1&&i>=1
                                    && chessMapAi[j - 1][i - 1] == 1) ||
                            (j<=13&&i>=1
                                    && chessMapAi[j + 1][i - 1] == 1) ||
                            (j>=1&&i<=13
                                    && chessMapAi[j - 1][i + 1] == 1)) {

                        Locs[j][i][0] = j;
                        Locs[j][i][1] = i;
                        Locs[j][i][2] = Locs[j][i][2] + OneB;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locs[j][i][2] = Locs[j][i][2] - minus;
						}

                    }

//                    we still need the value of white

                    //			        FiveW condition
                    if ((i<=10
                            && chessMapAi[j][i + 1] == -1
                            && chessMapAi[j][i + 2] == -1
                            && chessMapAi[j][i + 3] == -1
                            && chessMapAi[j][i + 4] == -1) ||
                            (i<=11&&i>=1
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i + 1] == -1
                                    && chessMapAi[j][i + 2] == -1
                                    && chessMapAi[j][i + 3] == -1)||
                            (i>=2&&i<=12
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1
                                    && chessMapAi[j][i + 1] == -1
                                    && chessMapAi[j][i + 2] == -1)||
                            (i>=3&&i<=13
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1
                                    && chessMapAi[j][i - 3] == -1
                                    && chessMapAi[j][i + 1] == -1)||
                            (i>=4&&i<=14
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1
                                    && chessMapAi[j][i - 3] == -1
                                    && chessMapAi[j][i - 4] == -1)||
                            (j<=10
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1
                                    && chessMapAi[j + 3][i] == -1
                                    && chessMapAi[j + 4][i] == -1) ||
                            (j>=1&&j<=11
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1
                                    && chessMapAi[j + 3][i] == -1) ||
                            (j>=2&&j<=12
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1) ||
                            (j>=3&&j<=13
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1
                                    && chessMapAi[j - 3][i] == -1
                                    && chessMapAi[j + 1][i] == -1) ||
                            (j>=4&&j<=14
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1
                                    && chessMapAi[j - 3][i] == -1
                                    && chessMapAi[j - 4][i] == -1) ||
                            (i<=10&&j<=10
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1
                                    && chessMapAi[j + 3][i + 3] == -1
                                    && chessMapAi[j + 4][i + 4] == -1) ||
                            (i<=11&&j<=11&&i>=1&&j>=1
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1
                                    && chessMapAi[j + 3][i + 3] == -1)||
                            (i<=12&&j<=12&&i>=2&&j>=2
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1)||
                            (i<=13&&j<=13&&i>=3&&j>=3
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1
                                    && chessMapAi[j - 3][i - 3] == -1
                                    && chessMapAi[j + 1][i + 1] == -1)||
                            (i<=14&&j<=14&&i>=4&&j>=4
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1
                                    && chessMapAi[j - 3][i - 3] == -1
                                    && chessMapAi[j - 4][i - 4] == -1)||
                            (i>=4&&j<=10
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1
                                    && chessMapAi[j + 3][i - 3] == -1
                                    && chessMapAi[j + 4][i - 4] == -1) ||
                            (i>=3&&j<=11&&i<=13&&j>=1
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1
                                    && chessMapAi[j + 3][i - 3] == -1) ||
                            (i>=2&&j<=12&&i<=12&&j>=2
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1) ||
                            (i>=1&&j<=13&&i<=11&&j>=3
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1
                                    && chessMapAi[j - 3][i + 3] == -1
                                    && chessMapAi[j + 1][i - 1] == -1) ||
                            (i<=10&&j>=4
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1
                                    && chessMapAi[j - 3][i + 3] == -1
                                    && chessMapAi[j - 4][i + 4] == -1) ) {

                        Locw[j][i][0] = j;
                        Locw[j][i][1] = i;
                        Locw[j][i][2] = Locw[j][i][2] + FourW;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
                    }

//                    FourW condition
                    if ((i<=11
                            && chessMapAi[j][i + 1] == -1
                            && chessMapAi[j][i + 2] == -1
                            && chessMapAi[j][i + 3] == -1) ||
                            (i>=1&&i<=12
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i + 1] == -1
                                    && chessMapAi[j][i + 2] == -1)||
                            (i>=2&&i<=13
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1
                                    && chessMapAi[j][i + 1] == -1)||
                            (i>=3&&i<=14
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1
                                    && chessMapAi[j][i - 3] == -1)||
                            (j<=11
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1
                                    && chessMapAi[j + 3][i] == -1) ||
                            (j>=1&&j<=12
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1) ||
                            (j>=2&&j<=13
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1
                                    && chessMapAi[j + 1][i] == -1) ||
                            (j>=3
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1
                                    && chessMapAi[j - 3][i] == -1) ||
                            (j<=11&&i<=11
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1
                                    && chessMapAi[j + 3][i + 3] == -1) ||
                            (j<=12&&j>=1&&i<=12&&i>=1
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1)||
                            (j<=13&&j>=2&&i<=13&&i>=2
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1
                                    && chessMapAi[j + 1][i + 1] == -1)||
                            (j>=3&&i>=3
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1
                                    && chessMapAi[j - 3][i - 3] == -1)||
                            (j<=11&&i>=3
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1
                                    && chessMapAi[j + 3][i - 3] == -1) ||
                            (j<=12&&j>=1&&i<=13&&i>=2
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1) ||
                            (j<=13&&j>=2&&i<=12&&i>=1
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1
                                    && chessMapAi[j + 1][i - 1] == -1) ||
                            (j>=3&&i<=11
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1
                                    && chessMapAi[j - 3][i + 3] == -1)
                    ) {

                        Locw[j][i][0] = j;
                        Locw[j][i][1] = i;
                        Locw[j][i][2] = Locw[j][i][2] + ThreeW;
						if (i < 11
								&& chessMapAi[j][i + 1] == -1
								&& chessMapAi[j][i + 2] == -1
								&& chessMapAi[j][i + 3] == -1
								&& chessMapAi[j][i + 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (i > 0 && i < 12
								&& chessMapAi[j][i + 1] == -1
								&& chessMapAi[j][i + 2] == -1
								&& chessMapAi[j][i + 3] == -1
								&& chessMapAi[j][i - 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (i < 10&&i>1
								&& chessMapAi[j][i + 1] == -1
								&& chessMapAi[j][i + 2] == -1
								&& chessMapAi[j][i - 1] == -1
								&& chessMapAi[j][i - 2] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (i < 10&&i>=1
								&& chessMapAi[j][i + 1] == -1
								&& chessMapAi[j][i + 2] == -1
								&& chessMapAi[j][i - 1] == -1
								&& chessMapAi[j][i + 3] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (i > 3 && i <= 14
								&& chessMapAi[j][i - 1] == -1
								&& chessMapAi[j][i - 2] == -1
								&& chessMapAi[j][i - 3] == -1
								&& chessMapAi[j][i - 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (i >= 3 && i < 14
								&& chessMapAi[j][i - 1] == -1
								&& chessMapAi[j][i - 2] == -1
								&& chessMapAi[j][i - 3] == -1
								&& chessMapAi[j][i + 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}										//sign
						if (j < 11
								&& chessMapAi[j + 1][i] == -1
								&& chessMapAi[j + 2][i] == -1
								&& chessMapAi[j + 3][i] == -1
								&& chessMapAi[j + 4][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (j > 0 && j < 13
								&& chessMapAi[j + 1][i] == -1
								&& chessMapAi[j + 2][i] == -1
								&& chessMapAi[j + 3][i] == -1
								&& chessMapAi[j - 1][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (j < 10&&j>1
								&& chessMapAi[j + 1][i] == -1
								&& chessMapAi[j + 2][i] == -1
								&& chessMapAi[j - 1][i] == -1
								&& chessMapAi[j - 2][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (j < 10&&j>=1
								&& chessMapAi[j + 1][i] == -1
								&& chessMapAi[j + 2][i] == -1
								&& chessMapAi[j - 1][i] == -1
								&& chessMapAi[j + 3][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] -minus;
						}
						if (j > 3 && j <= 14
								&& chessMapAi[j - 1][i] == -1
								&& chessMapAi[j - 2][i] == -1
								&& chessMapAi[j - 3][i] == -1
								&& chessMapAi[j - 4][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j >= 3 && j < 14
								&& chessMapAi[j - 1][i] == -1
								&& chessMapAi[j - 2][i] == -1
								&& chessMapAi[j - 3][i] == -1
								&& chessMapAi[j + 1][i] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}										//another sign
						if (j < 11 && i < 11
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j + 2][i + 2] == -1
								&& chessMapAi[j + 3][i + 3] == -1
								&& chessMapAi[j + 4][i + 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j > 0 && i >0 && j<=11 && i <=11
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j + 2][i + 2] == -1
								&& chessMapAi[j + 3][i + 3] == -1
								&& chessMapAi[j - 1][i - 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j < 12 && j >= 1 && i < 12 && i >= -1
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j + 2][i + 2] == -1
								&& chessMapAi[j + 3][i + 3] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j <= 12 && j > 1 && i <= 12 && i > -1
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j + 2][i + 2] == -1
								&& chessMapAi[j - 2][i - 2] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j < 13 && j >= 2 && i < 13 && i >= 2
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j - 2][i - 2] == -1
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j + 2][i + 2] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j <= 13 && j > 2 && i <= 13 && i > 2
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j - 2][i - 2] == -1
								&& chessMapAi[j + 1][i + 1] == -1
								&& chessMapAi[j - 3][i - 3] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j > 3 && i > 3
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j - 2][i - 2] == -1
								&& chessMapAi[j - 3][i - 3] == -1
								&& chessMapAi[j - 4][i - 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j >= 3 && i >= 3 && i <= 13 && j <= 13
								&& chessMapAi[j - 1][i - 1] == -1
								&& chessMapAi[j - 2][i - 2] == -1
								&& chessMapAi[j - 3][i - 3] == -1
								&& chessMapAi[j + 1][i + 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}											//another another sign
						if (j < 11 && i > 3
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j + 2][i - 2] == -1
								&& chessMapAi[j + 3][i - 3] == -1
								&& chessMapAi[j + 4][i - 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j <= 11 && i >= 3 && j > 3 && i < 11
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j + 2][i - 2] == -1
								&& chessMapAi[j + 3][i - 3] == -1
								&& chessMapAi[j - 1][i + 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j < 12 && j >= 1 && i <= 13 && i > 2
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j + 2][i - 2] == -1
								&& chessMapAi[j + 3][i - 3] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j <= 12 && j > 1 && i < 13 && i >= 2
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j + 2][i - 2] == -1
								&& chessMapAi[j - 2][i + 2] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j < 13 && j >= 2 && i <= 12 && i > 1
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j - 2][i + 2] == -1
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j + 2][i - 2] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j <= 13 && j > 2 && i < 12 && i >= 1
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j - 2][i + 2] == -1
								&& chessMapAi[j + 1][i - 1] == -1
								&& chessMapAi[j - 3][i + 3] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j > 3 && i < 11
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j - 2][i + 2] == -1
								&& chessMapAi[j - 3][i + 3] == -1
								&& chessMapAi[j - 4][i + 4] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if (j >= 3 && i <= 11 && j<14 && i >0
								&& chessMapAi[j - 1][i + 1] == -1
								&& chessMapAi[j - 2][i + 2] == -1
								&& chessMapAi[j - 3][i + 3] == -1
								&& chessMapAi[j + 1][i - 1] == 1) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}

                    }

//                    threeW condition
                    if ((i<=12
                            && chessMapAi[j][i + 1] == -1
                            && chessMapAi[j][i + 2] == -1) ||
                            (i>=1&&i<=13
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i + 1] == -1) ||
                            (i>=2
                                    && chessMapAi[j][i - 1] == -1
                                    && chessMapAi[j][i - 2] == -1) ||
                            (j<=12
                                    && chessMapAi[j + 1][i] == -1
                                    && chessMapAi[j + 2][i] == -1) ||
                            (j>=1&&j<=13
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j + 1][i] == -1) ||
                            (j>=2
                                    && chessMapAi[j - 1][i] == -1
                                    && chessMapAi[j - 2][i] == -1) ||
                            (j<=12&&i<=12
                                    && chessMapAi[j + 1][i + 1] == -1
                                    && chessMapAi[j + 2][i + 2] == -1) ||
                            (j<=13&&i<=13&&j>=1&&i>=1
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j + 1][i + 1] == -1) ||
                            (j>=2&&i>=2
                                    && chessMapAi[j - 1][i - 1] == -1
                                    && chessMapAi[j - 2][i - 2] == -1) ||
                            (j<=12&&i>=2
                                    && chessMapAi[j + 1][i - 1] == -1
                                    && chessMapAi[j + 2][i - 2] == -1) ||
                            (j>=1&&j<=13&&i>=1&&i<=13
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j + 1][i - 1] == -1) ||
                            (j>=2&&i<=12
                                    && chessMapAi[j - 1][i + 1] == -1
                                    && chessMapAi[j - 2][i + 2] == -1)) {

                        Locw[j][i][0] = j;
                        Locw[j][i][1] = i;
                        Locw[j][i][2] = Locw[j][i][2] + TwoW;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}

                    }

//                    twoW condition
                    if ((i<=13
                            && chessMapAi[j][i + 1] == -1) ||
                            (i>=1
                                    && chessMapAi[j][i - 1] == -1) ||
                            (j<=13
                                    && chessMapAi[j + 1][i] == -1) ||
                            (j>=1
                                    && chessMapAi[j - 1][i] == -1) ||
                            (j<=13&&i<=13
                                    && chessMapAi[j + 1][i + 1] == -1) ||
                            (j>=1&&i>=1
                                    && chessMapAi[j - 1][i - 1] == -1) ||
                            (j<=13&&i>=1
                                    && chessMapAi[j + 1][i - 1] == -1) ||
                            (j>=1&&i<=13
                                    && chessMapAi[j - 1][i + 1] == -1)) {

                        Locw[j][i][0] = j;
                        Locw[j][i][1] = i;
                        Locw[j][i][2] = Locw[j][i][2] + OneW;
						if ((15 - i <= 1 ) || ( i < 1) || (j < 1) || (15 - j <= 1 )) {
							Locw[j][i][2] = Locw[j][i][2] - minus;
						}

                    }

                }

			}
		}

        /*
         * now it seems we have found the value map
         * and we still need the Max and Min value
         */

        for (int i1 = 0; i1 < 15; i1++) {
            for (int j1 = 0; j1 < 15; j1++) {

                ValueMap[j1][i1][0] = j1;
                ValueMap[j1][i1][1] = i1;
                ValueMap[j1][i1][2] = Locs[j1][i1][2]+Locw[j1][i1][2];
				System.out.printf("%5d ",ValueMap[j1][i1][2]);
            }System.out.println();
        }

        int Max=0,Min=0;
        for (int ib = 0; ib < 15; ib++) {
            for (int jb = 0; jb < 15; jb++) {
                if(Max<=ValueMap[jb][ib][2])
                    Max = ValueMap[jb][ib][2];
                if(Min>=ValueMap[jb][ib][2])
                    Min = ValueMap[jb][ib][2];
            }
        }

//        now we have got the Max value and Min value of each map
//        Its time to find out which location should be the answer
        LinkedList<Integer> LocationX = new LinkedList<>();
        LinkedList<Integer> LocationY = new LinkedList<>();
        for (int i2 = 0; i2 < 15; i2++) {
            for (int j2 = 0; j2 < 15; j2++) {
                if (ValueMap[j2][i2][2] == Max) {
                    LocationX.add(j2);
                    LocationY.add(i2);
                }
            }
        }


//        now we have got all of the potential positions
//        then we need to make a desision on which position to be the answer+
        if (LocationX.size() == 1) {
            XandY[0]=LocationX.getFirst();
            XandY[1]=LocationY.getFirst();
        } else if (LocationX.size() > 1) {
            XandY[0]=LocationX.get(LocationX.size()/2);
            XandY[1]=LocationY.get(LocationY.size()/2);
        }

		return XandY;
	}

	private void cleanMap() {
		this.Locs = new int[15][15][3];
		this.Locw = new int[15][15][3];
		this.ValueMap = new int[15][15][3];
		this.chessMapAi = new int[15][15];
	}
	
	private void setAiMap(int loc1,int loc2) {
		chesses chessType = chesses.black;
		if(AiChessType==-1)
			chessType=chesses.white;
		DataTypes.setMap(chessType, loc1, loc2);
	}
}