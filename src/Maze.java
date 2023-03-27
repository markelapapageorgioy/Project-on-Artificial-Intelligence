import java.util.ArrayList;
import java.lang.Math;
import java.util.*;
import java.util.Scanner;

class Maze
{
  private int N1; // size of maze
  private Cell1[][] theMaze; // array of cells for the maze

  public Maze(int N,int p)
  {
    this.N1 = N; // size
    theMaze = new Cell1[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        theMaze[i][j] = new Cell1(i, j);
        theMaze[i][j].value();  // give values to cells of maze

      }
    }
    for (int i = 0; i < theMaze.length; i++) {                  // NEIGHBORS
      for (int j = 0; j < theMaze[i].length; j++) {
        // pano aristera gonia
        if (i == 0 && j == 0) {
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// dejia
          theMaze[i][j].addNeigh(theMaze[i + 1][j + 1]);// dejia
        } // kato aristera gonia
        else if (i == N1 - 1 && j == 0) {
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i - 1][j + 1]); // diagwnia
        } // pano dejia gonia
        else if (i == 0 && j == N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i + 1][j - 1]); // diagwnia
        } // kato dejia gonia
        else if (i == N1 - 1 && j == N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i - 1][j - 1]); // diagwnia
        } // pano toixoma
        else if (i == 0 && j > 0 && j < N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// dejia
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i + 1][j - 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i + 1][j + 1]);  // diagwnia
        } // kato toixoma
        else if (i == N1 - 1 && j > 0 && j < N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// dejia
          theMaze[i][j].addNeigh(theMaze[i - 1][j - 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i - 1][j + 1]); // diagwnia
        } // aristero toixoma
        else if (j == 0 && i > 0 && i < N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// dejia
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i + 1][j + 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i - 1][j + 1]); // diagwnia
        } // dejio toixoma
        else if (j == N1 - 1 && i > 0 && i < N1 - 1) {
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i - 1][j - 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i + 1][j - 1]); // diagwnia
        } // endiamesa
        else {
          theMaze[i][j].addNeigh(theMaze[i - 1][j]);// pano
          theMaze[i][j].addNeigh(theMaze[i + 1][j]);// kato
          theMaze[i][j].addNeigh(theMaze[i][j - 1]);// aristera
          theMaze[i][j].addNeigh(theMaze[i][j + 1]);// dejia
          theMaze[i][j].addNeigh(theMaze[i - 1][j - 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i - 1][j + 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i + 1][j - 1]); // diagwnia
          theMaze[i][j].addNeigh(theMaze[i + 1][j + 1]); // diagwnia
        }
      }
    }
  }

  public double movement(Cell1 now, Cell1 neigh) // cell now is the cell that we are now
  {
    double D = Math.abs(now.getValue1() - neigh.getValue1());
    if (neigh.getValue1() == -9)
    {
      return 0;
    } 
    else 
    {
      if (now.getRow() == neigh.getRow() || now.getColumn() == neigh.getColumn())
      {
        return D + 1;  // katheta h orizontia = 1
      }
      else
      {
        return D + 0.5; // diagonia = 0.5
      }
    }
  }

  public double initHeuristic(Cell1 cellNow , Cell1 G1, Cell1 G2)  //find the euretiki sunarthsh 
  {
    double h1temp=Math.abs(cellNow.getRow()-G1.getRow()) + Math.abs(cellNow.getColumn()-G1.getColumn());
    double h2temp=Math.abs(cellNow.getRow()-G2.getRow()) + Math.abs(cellNow.getColumn()-G2.getColumn());
    double h1= 1*h1temp+(0.5-2*1)* Math.min(Math.abs(cellNow.getRow()-G1.getRow()), Math.abs(cellNow.getColumn()-G1.getColumn()));
    double h2= 1*h2temp+(0.5-2*1)* Math.min(Math.abs(cellNow.getRow()-G2.getRow()), Math.abs(cellNow.getColumn()-G2.getColumn()));
    double heuristics = Math.max(h1,h2);
    return heuristics;
  }

  public int vali(int row, int column)
  {
    return theMaze[row][column].getValue1(); // return the value of each cell at maze
  }

  public Cell1 vali1(int row, int column)
  {
    return theMaze[row][column]; // return just the cell 
  }

  public ArrayList<Cell1> UCS(Cell1 S, Cell1 G1, Cell1 G2)   // UCS
  {
    ArrayList<Cell1> notVisited = new ArrayList<Cell1>(); 
    ArrayList<Cell1> visited = new ArrayList<Cell1>(); 
    ArrayList<Cell1> distancee = new ArrayList<Cell1>(); 

    notVisited.add(S); 
    boolean flag1 = false;
    boolean flag2 = false;
    while (true) {
        if(notVisited == null){
            return null; 
        }
      Cell1 taken = notVisited.get(notVisited.size()-1); 
                                       
      double minimumCost = taken.getCost(); 
      for (int i = 0; i < notVisited.size(); i++) {
        if (notVisited.get(i).getCost() < minimumCost) {
          taken = notVisited.get(i);
          minimumCost = notVisited.get(i).getCost();
        }
      }
      if (taken == G1) { 
        flag1 = true;
        visited.add(taken);
      }
      if (taken == G2) { 
        flag2 = true;
        visited.add(taken);
      }
      if(G1==G2)
      {
        flag1 = true;
        flag2 = true;
      }
      if (flag1 == true && flag2 == true) {
        if (G1.getCost() < G2.getCost()) { 
                                           
          Cell1 boss = G1;
          distancee.add(boss);
          while (boss != null) {
            boss = boss.getBoss();
            distancee.add(boss);
          }
          return distancee;
        } else if (G2.getBoss() != null) {
          Cell1 boss = G2; 
          distancee.add(boss);
          while (boss != null) {
            boss = boss.getBoss();
            distancee.add(boss);
          }
          return distancee;
        }
      }
      notVisited.remove(taken); 
      visited.add(taken);
      if (taken.checkIfNeigh() > 0) 
      {
        for (int j = 0; j < taken.checkIfNeigh(); j++) {
          Cell1 child = taken.getCellNeigh(j);
          double costChild = taken.getCost() + movement(taken, child);
          if (!visited.contains(child)) { 
            if (!notVisited.contains(child)) {
              child.setBoss(taken); 
              child.setCost(costChild); 
              notVisited.add(child); 
            } else {
                if (child.getCost() > costChild) { 
                                                
                    child.setBoss(taken);
                    child.setCost(costChild);
                    notVisited.add(child);
                }
            }
          }
        }
      }
    }
  }

  public ArrayList<Cell1> A_Star(Cell1 S, Cell1 G1, Cell1 G2)     //A_STAR
  {
    ArrayList<Cell1> A_notVisited = new ArrayList<Cell1>(); 
    ArrayList<Cell1> A_visited = new ArrayList<Cell1>(); 
    ArrayList<Cell1> A_distancee = new ArrayList<Cell1>(); 

    A_notVisited.add(S); 
    boolean flag1 = false;
    boolean flag2 = false;
    while (true) {
        if(A_notVisited == null){
            return null; 
        }
      Cell1 taken = A_notVisited.get(A_notVisited.size()-1); 
                                       
      double minimumCost = taken.getCost() + initHeuristic(taken,G1,G2); 
      for (int i = 0; i < A_notVisited.size(); i++) {
        if (A_notVisited.get(i).getCost() < minimumCost) {
          taken = A_notVisited.get(i);
          minimumCost = A_notVisited.get(i).getCost() +initHeuristic(A_notVisited.get(i),G1,G2);
        }
      }
      if (taken == G1) { 
        flag1 = true;
        A_visited.add(taken);
      }
      if (taken == G2) { 
        flag2 = true;
        A_visited.add(taken);
      }
      if(G1==G2)
      {
        flag1 = true;
        flag2 = true;
      }
      if (flag1 == true && flag2 == true) {
        if (G1.getCost() < G2.getCost()) { 
                                           
          Cell1 boss = G1;
          A_distancee.add(boss);
          while (boss != null) {
            boss = boss.getBoss();
            A_distancee.add(boss);
          }
          return A_distancee;
        } else if (G2.getBoss() != null) {
          Cell1 boss = G2; 
          A_distancee.add(boss);
          while (boss != null) {
            boss = boss.getBoss();
            A_distancee.add(boss);
          }
          return A_distancee;
        }
      }
      A_notVisited.remove(taken); 
      A_visited.add(taken);
      if (taken.checkIfNeigh() > 0) 
      {
        for (int j = 0; j < taken.checkIfNeigh(); j++) {
          Cell1 child = taken.getCellNeigh(j);
          double costChild = taken.getCost() + movement(taken, child);
          if (!A_visited.contains(child)) { 
            if (!A_notVisited.contains(child)) { 
              child.setBoss(taken); 
              child.setCost(costChild); 
              A_notVisited.add(child); 
            } else {
                if (child.getCost() > costChild) { 
                                                
                    child.setBoss(taken);
                    child.setCost(costChild);
                    A_notVisited.add(child);
                }
            }
          }
        }
      }
    }
  }

  public static void main(String[] args)
  {

   
    Scanner input = new Scanner(System.in);
    System.out.println("Give N");
    int number = input.nextInt();
    System.out.println("give the possibility as integer from 1-5 ");
    int possibol = input.nextInt();
    Maze mymaze = new Maze(number,possibol);
    for (int i = 0; i < number; i++) {
      for (int j = 0; j < number; j++) {
        System.out.print("(" + i + "," + j + ") = " + mymaze.vali(i, j) + " ,");
      }
      System.out.println();
    }
    System.out.println("give the x coordinate for S with value >0");
    int x = input.nextInt();
    System.out.println("give the y coordinate for S with value >0");
    int y = input.nextInt();
    Cell1 S = mymaze.vali1(x, y);
    System.out.println("give the x coordinate for G1 with value >0");
    int x1 = input.nextInt();
    System.out.println("give the y coordinate for G1 with value >0");
    int y1 = input.nextInt();
    Cell1 G1 = mymaze.vali1(x1, y1);
    System.out.println("give the x coordinate for G2 with value >0");
    int x2 = input.nextInt();
    System.out.println("give the y coordinate for G2 wtih value >0");
    int y2 = input.nextInt();
    Cell1 G2 = mymaze.vali1(x2, y2);
    System.out.println("S = (" + x + "," + y + ")");
    System.out.println(" G1 = (" + x1 + "," + y1 + ") ");
    System.out.println(" G2 = ( " + x2 + "," + y2 + ")");
    System.out.println();
    ArrayList<Cell1> distancee = new ArrayList<Cell1>();
    distancee = mymaze.UCS(S, G1, G2);
    ArrayList<Cell1> A_distance = new ArrayList<Cell1>();
    A_distance = mymaze.A_Star(S,G1,G2);

    long startTime = System.nanoTime();
    for (int j = 0; j<distancee.size()-1 ;j++)
    {
        System.out.print("UCS path = (" + distancee.get(j).getRow() + "," + distancee.get(j).getColumn() + ")"); 
    }
    System.out.println();
    System.out.println("\n UCS_cost = " + distancee.get(0).getCost()); 
    long stopTime = System.nanoTime();
    long executionTime = stopTime - startTime;
    double sec = (double) executionTime / 1_000_000_000;
    System.out.println("executionTime in sec is : "+sec+" seconds ");

    System.out.println(); 

    long startTime2 = System.nanoTime();
    for (int j = 0; j<A_distance.size()-1 ;j++)
    {
        System.out.print("A_Star path = (" + A_distance.get(j).getRow() + "," + A_distance.get(j).getColumn() + ")"); 
    }
    System.out.println();
    System.out.println("\nA_Star_cost = " + A_distance.get(0).getCost());  
    long stopTime2 = System.nanoTime();
    long executionTime2 = stopTime2 - startTime2;
    double sec2 = (double) executionTime2 / 1_000_000_000;
    System.out.println("executionTime in sec is : "+sec2 +" seconds"); 
  }
}
