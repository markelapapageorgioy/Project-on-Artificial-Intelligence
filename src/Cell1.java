import java.util.ArrayList;
import java.util.Random;

class Cell1
{

    int p; // possibility
    int value1;  // value
    int row;  // x coordinate 
    int column;   // y coordinate 
    ArrayList<Cell1> arrayNeigh; // arrayList of geitones
    double cost; 
    private Cell1 Boss;

    public Cell1(int row,int column)
    {
        this.row=row;
        this.column=column;
        this.value();
        this.arrayNeigh =new ArrayList<Cell1>(8);
    }

    public void setBoss(Cell1 Boss)
    {
      this.Boss= Boss;
    }
    public Cell1 getBoss()
    {
      return Boss;
    }

    public void setCost(double cost)
    {
      this.cost= cost;
    }

    public double getCost()
    {
      return cost;
    }

    public void setValue1(int value1)
    {
      this.value1= value1;
    }

    public int getValue1()
    {
      return value1;
    }

    public void setRow(int row)
    {
      this.row= row;
    }

    public int getRow()
    {
      return this.row;
    }

    public void setColumn(int column)
    {
      this.column=column;
    }

    public int getColumn()
    {
      return this.column;
    }

    public int value()   // value of each cell
    {
      Random random = new Random();    
      int k = random.nextInt(6);      // possibility for blocked cells
      if(k==p)
      {
        this.value1=-9;
        return value1;
      }
      else 
      {
        Random val= new Random();
        this.value1=val.nextInt(4)+1;
        return value1;
      }
    }

    public ArrayList<Cell1> getArrayNeigh()  // return the array of neighbors 
    {
      return arrayNeigh;
    }

    public Cell1 getCellNeigh(int x) // return one element of array of neighbors
    {  
      return this.arrayNeigh.get(x);
    }

    public void addNeigh(Cell1 newcell)   // add the neighbor at array of neighbors
    {    
      if(newcell.getValue1()==-9)
      {
        return;
      }
      else
      {
        arrayNeigh.add(newcell);
      }
    }

    public int checkIfNeigh()  // check how many neighbors a cell has
    { 
      int counter=0;
      for(int i=0; i<arrayNeigh.size(); i++)
      {
        if(arrayNeigh.get(i).getValue1()!=-9)
        {
          counter+=1;
        }
      }
      return counter;
    }

}