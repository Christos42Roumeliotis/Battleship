
package newpackage;
import java.io.InputStream;
import javafx.scene.Parent;

public class Ship extends Parent {
    public int type,points;
    public boolean vertical = true;
    public int[] Arraypoints ={50,100,100,250,350};
    public int[] ArrayBonus ={0,0,250,500,1000};
    private int health;
    
    public Ship(int type, boolean vertical) {
        this.type = type;
        this.vertical = vertical;
        health = type;
       
    }

   

/** 
 * this method is used when a ship is hit and it accumulates its damage
* <points>
* return points of each ship
*
* @param health    the current health of the ship
* @param points     the points of destruction for current ship
*                
* @param Arrarpoints     the array of score for a succesful hit according to type
*                 
* @param ArrarBonus    the array of bonus for a succesful sink according to type
*                
* @return          <code>points</code>  
*                  
* @see             points
* @author      CHRISTOS 
*@since           1.0
*/
    public int hit() {
        health--;
        if (health > 0 )
        points = points + Arraypoints[type-1];
        else
        points = points + Arraypoints[type-1]+ ArrayBonus[type-1];
        
        
        
       System.out.println("HIT type "+type +" REMAINING SHOTS TO SINK " +health +" POINTS "+points);
     return points;
    }
    
    /** 
 * this method is used when a ship is hit and check if sunk
* <isAlive>
* If the current output representation is not yet sunk then
* the method will return false 
*
* @param health    the current health of the ship
* @param typethe  type of ship
*                 
* @param ArrarBonus    the array of bonus for a succesful sink according to type
*                
* @return          <code>true</code> if the ship is completely 
*                  damaged and  
*                  <code>false</code> otherwise. 
*                  
* @see             points
* @author      CHRISTOS 
*@since           1.0
*/

    public boolean isAlive() {
        if (health == 0 ){
             System.out.println("YOY SUNK TYPE  "+type + " BONUS POINTS " +  ArrayBonus[type-1] );}
        return health > 0;
    }
}