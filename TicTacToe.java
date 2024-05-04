import java.util.Scanner;

public class TicTacToe {
  
  public static int getValidInt(String prompt) {
    Scanner in = new Scanner(System.in);  
    while(true)
    {
      System.out.print(prompt);
      String input = in.nextLine();
      int num = 0;
      try 
      {
        num = Integer.parseInt(input);
      }
      catch(Exception e)
      {
        System.out.println("유효한 정수가 아닙니다!");
        continue;
      }     
      if(num < 0 || num > 2)
      {
        System.out.println("정수는 0과 2 사이여야 합니다");
        continue;        
      }     
      return num;
    }
  }
  
  public static boolean checkRows(int[][] A) {
    for(int i = 0; i < A.length; i++)
    {
      if((A[i][0] == A[i][1]) && (A[i][1] == A[i][2]) && A[i][0] != 0)
        return true;
    }
    return false;  
  }
  
  public static boolean checkCols(int[][] A) {
    for(int i = 0; i < A[0].length; i++)
    {
      if((A[0][i] == A[1][i]) && (A[1][i] == A[2][i]) && A[0][i] != 0)
        return true;
    }
    return false;  
  }
  
  public static boolean checkDiags(int[][] A) {
    if((A[0][0] == A[1][1]) && (A[1][1] == A[2][2]) && A[0][0] != 0)
      return true;
    else if ((A[0][2] == A[1][1]) && (A[1][1] == A[2][0]) && A[1][1] != 0)
      return true;
    else
      return false;
  }

  public static boolean checkHit(int[][] A) {
    return checkRows(A) || checkCols(A) || checkDiags(A);
  }
  
  public static boolean isFree (int[][] A, int row, int col) {
    return A[row][col] == 0;
  }
  
  public static boolean getWinner(String turnPrompt, int[][] A, int playerNumber) {
    System.out.println(turnPrompt);
    int row = 0, col = 0;
    while(true)
    {
      row = getValidInt("행을 입력하세요: ");
      col = getValidInt("열을 입력하세요: ");  
      if(isFree(A, row, col))
      {
        break;
      }
      System.out.printf("[%d,%d]은 이미 채워져 있습니다!\n", row, col);
    }  
    A[row][col] = playerNumber;     
    return checkHit(A);
  }
  
  public static void printBoard(int[][] A) {
    System.out.println("-------------");
    for (int i = 0; i < 3; i++) {
        System.out.print("| ");
        for (int j = 0; j < 3; j++) {
            System.out.print(A[i][j] + " | ");
        }
        System.out.println();
        System.out.println("-------------");
    }
  }
    
  public static void main(String[] args) {
    
    // 게임 규칙 설명
    System.out.println("틱택토 게임 규칙:");
    System.out.println("1. 두 명의 플레이어가 번갈아가며 0부터 2까지의 숫자로 행과 열을 입력하여 표에 자신의 표시를 합니다.");
    System.out.println("2. 한 행, 열 또는 대각선에 자신의 표시를 먼저 3개 연속으로 만드는 플레이어가 승리합니다.");
    System.out.println("3. 승리자가 나오지 않고 9번의 턴이 모두 소진되면 무승부입니다.");
    System.out.println();
    
    int[][] grid = new int[3][3];
    int foundWinner = 0;
    
    printBoard(grid);
    
    int i = 0;
    while(i < 9)
    {
      if(i % 2 == 0) //Player 1
      {
        if(getWinner("플레이어 1의 차례", grid, 1))
        {
          foundWinner = 1;
          System.out.println("플레이어 1 승리!");
          break;
        }
        printBoard(grid);
        System.out.println();
      }
      else //Player 2
      {
        if(getWinner("플레이어 2의 차례", grid, 2))
        {
          foundWinner = 1;
          System.out.println("플레이어 2 승리!");
          break;
        }
        printBoard(grid);
        System.out.println();
      }
      i++;
    }
    
    if(foundWinner == 0)
      System.out.println("무승부입니다!");
 
  }//end main
}//end class
