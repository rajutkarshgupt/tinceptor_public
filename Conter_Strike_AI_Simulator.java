package javaapplication34;

/**
 *
 * @author Utkarsh Gupta
 */
/**
 *
 * @author Utkarsh Gupta
 */
import java.util.*;
class Position
{
    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
}
class Player
{
    private int energy_per_step;
    private int hit_to_die;
    private String strategy;
    private double site;
    private Position position;
    private double orientation;
    private String type;
    private int energy_level;
    private int success=0;
    private int speed;

    public Player(int energy_per_step, int hit_to_die, String strategy, double site, Position position, double orientation, String type, int energy_level,int speed) {
        this.energy_per_step = energy_per_step;
        this.hit_to_die = hit_to_die;
        this.strategy = strategy;
        this.site = site;
        this.position = position;
        this.orientation = orientation;
        this.type = type;
        this.energy_level = energy_level;
        this.speed = speed;
    }

    public Player(){}
    
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public void setHit_to_die() {
        hit_to_die--;
    }
    
    public void Success()
    {
        success++;
    }

    public void setEnergy_level() {
        energy_level-=energy_per_step;
    }
    
    public int getEnergy_per_step() {
        return energy_per_step;
    }

    public int getHit_to_die() {
        return hit_to_die;
    }

    public String getStrategy() {
        return strategy;
    }

    public double getSite() {
        return site;
    }

    public Position getPosition() {
        return position;
    }

    public double getOrientation() {
        return orientation;
    }
    
    public String getType() {
        return type;
    }

    public int getEnergy_level() {
        return energy_level;
    }

    public int getSuccess() {
        return success;
    }

    public int getSpeed() {
        return speed;
    }
    
}
class Aggressive extends Player
{

    public Aggressive( String strategy, double site, Position position, double orientation, String type, int energy_level,int speed) {
        super(2,2, strategy, site, position, orientation, type, energy_level,speed);
    }
    
}
class Cautious extends Player
{

    public Cautious( String strategy, double site, Position position, double orientation, String type, int energy_level,int speed) {
        super(1,1, strategy, site, position, orientation, type, energy_level,speed);
    }
    
}
class Blind extends Player
{

    public Blind( String strategy, double site, Position position, double orientation, String type, int energy_level,int speed) {
        super(3,5, strategy, site, position, orientation, type, energy_level,speed);
    }
    
}
class Environment
{
    private int total_terrorists;
    private int total_counter_terrorists;
    private Position goal;
    private ArrayList<Player> terrorists_array = new ArrayList<Player>();
    private String ter_order;
    private ArrayList<Player> counter_terrorists_array = new ArrayList<Player>();
    private String ct_order;

    public Environment(int total_terrorists, int total_counter_terrorists, Position goal, String ter_order, String ct_order) {
        this.total_terrorists = total_terrorists;
        this.total_counter_terrorists = total_counter_terrorists;
        this.goal = goal;
        this.ter_order = ter_order;
        this.ct_order = ct_order;
    }

    public int getTotal_terrorists() {
        return total_terrorists;
    }

    public int getTotal_counter_terrorists() {
        return total_counter_terrorists;
    }
    
    public Position getGoal() {
        return goal;
    }

    public ArrayList<Player> getTerrorists_array() {
        return terrorists_array;
    }
    
    public Player getTerrorists(int index) {
        return terrorists_array.get(index);
    }

    public String getTer_order() {
        return ter_order;
    }

    public ArrayList<Player> getCounter_terrorists_array() {
        return counter_terrorists_array;
    }
    
    public Player getCounter_terrorists(int index) {
        return counter_terrorists_array.get(index);
    }

    public String getCt_order() {
        return ct_order;
    }

    public void setTerrorists_array(ArrayList<Player> terrorists_array) {
        this.terrorists_array = terrorists_array;
    }

    public void setCounter_terrorists_array(ArrayList<Player> counter_terrorists_array) {
        this.counter_terrorists_array = counter_terrorists_array;
    }
    
    public void setTerrorists(Player terrorist) {
        terrorists_array.add(terrorist);
    }
    
    public void setCounter_terrorists(Player counter_terrorist) {
        counter_terrorists_array.add(counter_terrorist);
    }
    
    public void updateTerrorists_array(int index,Player p)
    {
        terrorists_array.set(index, p);
    }
    
    public void updateCounter_terrorists_array(int index,Player p)
    {
        counter_terrorists_array.set(index, p);
    }
 
}
class Sortby_energy_level implements Comparator<Player>
{
    public int compare(Player a, Player b)
    {
        return a.getEnergy_level() - b.getEnergy_level();
    }
}
class Sortby_success implements Comparator<Player>
{
    public int compare(Player a, Player b)
    {
        return a.getSuccess() - b.getSuccess();
    }
}
abstract class Strategy
{
    abstract public Environment move(Environment environment,Player p,int player_index,int turn);
}
class Nearest extends Strategy
{
    public Environment move(Environment environment,Player p,int player_index,int turn)
    {
        Position p1p = p.getPosition();
            double p1x = p1p.getX();
            double p1y = p1p.getY();
            int destination_index = 0;
        double min = 100000;
                    Position destination = environment.getTerrorists_array().get(0).getPosition();
                    for(int i=0;i<environment.getTotal_terrorists();i++)
                    {
                        if(environment.getTerrorists_array().get(i)!=null)
                            {
                                Player p2 = environment.getTerrorists_array().get(i);
                                Position p2p = p2.getPosition();
                                double p2x = p2p.getX();
                                double p2y = p2p.getY();
                                String s1 = p2.getType();
                                String s2 = p.getType(); 
                                        double distance = Math.sqrt(((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y)));
                                        if(distance<min)
                                        {
                                            min = distance;
                                            destination = p2.getPosition();
                                            destination_index = i;
                                        }
                            }
                    }
                    if(player_index!=destination_index)
                    {
                        GameEngine gameengine = new GameEngine(environment);
                        p = gameengine.AImove(p,destination);
                    }
            if(turn%2==0)
            {
                environment.updateTerrorists_array(player_index,p);
            }
            else
            {
                environment.updateCounter_terrorists_array(player_index,p);
            }
        return environment;
    }
}
class Random extends Strategy
{
    public Environment move(Environment environment,Player p,int player_index,int turn)
    {
        int destination_index = 0;
        Position destination = new Position(0.0,0.0);
                    for(int i=0;i<environment.getTerrorists_array().size();i++)
                    {
                        if(player_index!=i)
                        {
                            if(environment.getTerrorists_array().get(i)!=null)
                            {
                                        Player p2 = environment.getTerrorists_array().get(i);
                                        destination = p2.getPosition();
                                        destination_index = i;
                            }
                        }
                    }
                    if(player_index!=destination_index)
                    {
                        GameEngine gameengine = new GameEngine(environment);
                        p = gameengine.AImove(p,destination);
                    }
            if(turn%2==0)
            {
                environment.updateTerrorists_array(player_index,p);
            }
            else
            {
                environment.updateCounter_terrorists_array(player_index,p);
            }
        return environment;
    }
}
class Ahead extends Strategy
{
    public Environment move(Environment environment,Player p,int player_index,int turn)
    {
        int destination_index = 0;
                    double min = 100000;
                    Player p2 = environment.getTerrorists_array().get(0);
                    Position destination = p2.getPosition();
                    Position goal = environment.getGoal();
                    double gx = goal.getX();
                    double gy = goal.getY();
                    for(int i=0;i<environment.getTerrorists_array().size();i++)
                    {
                        if(player_index!=i)
                        {
                            if(environment.getTerrorists_array().get(i)!=null)
                            {
                                p2 = environment.getTerrorists_array().get(i);
                                Position p2p = p2.getPosition();
                                double p2x = p2p.getX();
                                double p2y = p2p.getY();
                                String s1 = p2.getType();
                                String s2 = p.getType();
                                        double distance = Math.sqrt(((gx-p2x)*(gx-p2x))+((gy-p2y)*(gy-p2y)));
                                        if(distance<min)
                                        {
                                            destination = p2.getPosition();
                                            min = distance;
                                            destination_index = i;
                                        }
                            }
                        }
                    }
                    if(player_index!=destination_index)
                    {
                        GameEngine gameengine = new GameEngine(environment);
                        p = gameengine.AImove(p,destination);
                    }
            if(turn%2==0)
            {
                environment.updateTerrorists_array(player_index,p);
            }
            else
            {
                environment.updateCounter_terrorists_array(player_index,p);
            }
        return environment;
    }
}
class Bomb extends Strategy
{
    public Environment move(Environment environment,Player p,int player_index,int turn)
    {
        Position goal = environment.getGoal();
        GameEngine gameengine = new GameEngine(environment);
        p = gameengine.AImove(p,goal);
        if(turn%2==0)
            {
                environment.updateTerrorists_array(player_index,p);
            }
            else
            {
                environment.updateCounter_terrorists_array(player_index,p);
            }
        return environment;
    }
}
class GameEngine
{   
    private Environment environment;

    public GameEngine(Environment environment) {
        this.environment = environment;
    }
    
    public int Site(Player p1,Player p2)
    {
        Position p1p = p1.getPosition();
        Position p2p = p2.getPosition();
        double p1x = p1p.getX();
        double p1y = p1p.getY();
        double p2x = p2p.getX();
        double p2y = p2p.getY();
        double gamma = Math.atan2((p2y-p1y), (p2x-p1x));
        double orientation1 = p1.getOrientation();
        double site1 = p1.getSite();
        if(Math.cos(orientation1-gamma)>Math.cos(site1))
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public ArrayList<Player> fire(ArrayList<Player> players_array,int index)
    {
        
        if(players_array.get(index).getHit_to_die()==1)
        {
            players_array.set(index, null);
            return players_array;
        }
        Player p = players_array.get(index);
        p.setHit_to_die();
        players_array.set(index,p);
        return players_array;
    }
    public Player AImove(Player p,Position p2p)
    {
        Position p1p = p.getPosition();
        double p1x = p1p.getX();
        double p1y = p1p.getY();
        double p2x = p2p.getX();
        double p2y = p2p.getY();
        Position goal = new Position(p2x,p2y);
        if(p.getSpeed()<Math.sqrt(((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y))))
        {    
            double x = p1x + (p.getSpeed()*(p2x-p1x)/(Math.sqrt(((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y)))));
            double y = p1y + (p.getSpeed()*(p2y-p1y)/(Math.sqrt(((p1x-p2x)*(p1x-p2x))+((p1y-p2y)*(p1y-p2y)))));
            goal = new Position(x,y);
        }
        p.setPosition(goal);
        double orientation = Math.atan2((p2y-p1y), (p2x-p1x));
        p.setOrientation(orientation);
        p.setEnergy_level();
        if(p.getEnergy_level()==0)
            p=null;
        return p;
    }
    public void play()
    {
        Position goal = environment.getGoal();
        double gx = goal.getX();
        double gy = goal.getY();
        ArrayList<Player> opponents_array = new ArrayList<Player>();
        Player p = new Player();
        ArrayList<Player> terrorists_array = environment.getTerrorists_array();
        /*ArrayList<Player> counter_terrorists_array = environment.getCounter_terrorists_array();
        switch(environment.getTer_order())
                {
                    case "energy":
                        Collections.sort(terrorists_array, new Sortby_energy_level());
                        break;
                    case "success":
                        Collections.sort(terrorists_array, new Sortby_success());
                        break;
                }
        environment.setTerrorists_array(terrorists_array);
        switch(environment.getCt_order())
                {
                    case "energy":
                        Collections.sort(counter_terrorists_array, new Sortby_energy_level());
                        break;
                    case "success":
                        Collections.sort(counter_terrorists_array, new Sortby_success());
                        break;
                }
        environment.setCounter_terrorists_array(counter_terrorists_array);*/
        int turn=0,player_index=0;
        while(turn<10000)
        {
            if(turn%2==0)
            {
                opponents_array = environment.getCounter_terrorists_array();
                switch(environment.getTer_order())
                {
                    case "circular":
                        for(int k=0;k<environment.getTotal_terrorists();k++)
                        {
                            if(environment.getTerrorists(k)!=null)
                            {
                                p = environment.getTerrorists(k);
                                player_index = k;
                                break;
                            }
                        }
                    case "energy":
                        int max = 0;
                        for(int k=0;k<environment.getTotal_terrorists();k++)
                        {
                            if(environment.getTerrorists(k)!=null)
                            {
                                if(environment.getTerrorists(k).getEnergy_level()>max)
                                {
                                    max = environment.getTerrorists(k).getEnergy_level();
                                    p = environment.getTerrorists(k);
                                    player_index = k;
                                }
                            }
                        }
                        break;
                    case "success":
                        max = 0;
                        for(int k=0;k<environment.getTotal_terrorists();k++)
                        {
                            if(environment.getTerrorists(k)!=null)
                            {
                                if(environment.getTerrorists(k).getSuccess()>max)
                                {
                                    max = environment.getTerrorists(k).getSuccess();
                                    p = environment.getTerrorists(k);
                                    player_index = k;
                                }
                            }
                        }
                        break;
                }
                
            }
            else
            {
                opponents_array = environment.getTerrorists_array();
                switch(environment.getTer_order())
                {
                    case "circular":
                        for(int k=0;k<environment.getTotal_counter_terrorists();k++)
                                {
                                    if(environment.getCounter_terrorists(k)!=null)
                                    {
                                        p = environment.getCounter_terrorists(k);
                                        player_index = k;
                                        break;
                                    }
                                }
                    case "energy":
                        int max = 0;
                        for(int k=0;k<environment.getTotal_counter_terrorists();k++)
                        {
                            if(environment.getCounter_terrorists(k)!=null)
                            {
                                if(environment.getCounter_terrorists(k).getEnergy_level()>max)
                                {
                                    max = environment.getCounter_terrorists(k).getEnergy_level();
                                    p = environment.getCounter_terrorists(k);
                                    player_index = k;
                                }
                            }
                        }
                        break;
                    case "success":
                        max = 0;
                        for(int k=0;k<environment.getTotal_counter_terrorists();k++)
                        {
                            if(environment.getCounter_terrorists(k)!=null)
                            {
                                if(environment.getCounter_terrorists(k).getSuccess()>max)
                                {
                                    max = environment.getCounter_terrorists(k).getSuccess();
                                    p = environment.getCounter_terrorists(k);
                                    player_index = k;
                                }
                            }
                        }
                        break;
                }        
            }
            for(int i=0;i<opponents_array.size();i++)
                    {
                            if(opponents_array.get(i)!=null)
                            {
                                Player p2 = opponents_array.get(i);
                                Position p2p = p2.getPosition();
                                double p2x = p2p.getX();
                                double p2y = p2p.getY();
                                String s1 = p2.getType();
                                String s2 = p.getType();
                                        if(Site(p,p2)==1 && s1.compareTo(s2)!=0)
                                        {
                                            opponents_array = fire(opponents_array,i);
                                            p.Success();
                                        }
                            }
                    }
            String strategy = p.getStrategy();
            Position p1p = p.getPosition();
            double p1x = p1p.getX();
            double p1y = p1p.getY();
            int destination_index = 0;
            Strategy ob = new Nearest();
            switch(strategy)
            {
                case "nearest":
                    ob = new Nearest();
                    break;
                case "random":
                    ob = new Random();
                    break;
                case "ahead":
                    ob =  new Ahead();
                    break;
                case "bomb":
                     ob = new Bomb();
                    break;
            }
            environment = ob.move(environment, p, player_index, turn);
            if(turn%2==0)
            {
                environment.setCounter_terrorists_array(opponents_array);
            }
            else
            {
                environment.setTerrorists_array(opponents_array);
            }
            int flag = 1;
            for(int j=0;j<environment.getTotal_terrorists();j++)
            {
                Player player = environment.getTerrorists(j);
                if(player!=null)
                {
                    Position position = player.getPosition();
                    double x = position.getX();
                    double y = position.getY();
                    if(x==gx && y==gy)
                    {
                        System.out.println("TERRORISTS WON");
                        return;
                    }
                    flag = 0;
                }
            }
            if(flag==1)
            {
                System.out.println("COUNTER - TERRORISTS WON");
                return;
            }
            flag = 1;
            for(int j=0;j<environment.getTotal_counter_terrorists();j++)
            {
                Player player = environment.getCounter_terrorists(j);
                if(player!=null)
                    flag = 0;
            }
            if(flag==1)
            {
                System.out.println("TERRORISTS WON");
                return;
            }
            turn++;
        }
        System.out.println("COUNTER - TERRORISTS WON");
    }
}
class JavaApplication34 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double pi = Math.PI;
        Scanner sc = new Scanner(System.in);
        int total_terrorists = sc.nextInt();
        int total_counter_terrorists = sc.nextInt();
        double gx = sc.nextDouble();
        double gy = sc.nextDouble();
        Position goal = new Position(gx,gy);
        String ter_order = sc.next();
        String ct_order = sc.next();
        Environment environment = new Environment(total_terrorists,total_counter_terrorists,goal,ter_order,ct_order);
        for(int i=0;i<total_terrorists;i++)
        {
            String type = sc.next();
            String strategy = sc.next();
            double site = sc.nextDouble();
            site*=pi;
            site/=180;
            double px = sc.nextDouble();
            double py = sc.nextDouble();
            Position position = new Position(px,py);
            double orientation = sc.nextDouble();
            orientation*=pi;
            orientation/=180;
            int energy_level = sc.nextInt();
            int speed = sc.nextInt();
            switch(type)
            {
                case "aggressive":
                    Aggressive aggressive = new Aggressive( strategy, site, position, orientation, "TERRORISTS", energy_level, speed);
                    environment.setTerrorists(aggressive);
                    break;
                case "cautious":
                    Cautious cautious = new Cautious( strategy, site, position, orientation, "TERRORISTS", energy_level, speed);
                    environment.setTerrorists(cautious);
                    break;
                case "blind":
                    Blind blind = new Blind( strategy, site, position, orientation, "TERRORISTS", energy_level, speed);
                    environment.setTerrorists(blind);
                    break;
            }
        }
        for(int i=0;i<total_counter_terrorists;i++)
        {
            String type = sc.next();
            String strategy = sc.next();
            double site = sc.nextDouble();
            site*=pi;
            site/=180;
            double px = sc.nextDouble();
            double py = sc.nextDouble();
            Position position = new Position(px,py);
            double orientation = sc.nextDouble();
            orientation*=pi;
            orientation/=180;
            int energy_level = sc.nextInt();
            int speed = sc.nextInt();
            switch(type)
            {
                case "aggressive":
                    Aggressive aggressive = new Aggressive( strategy, site, position, orientation, "COUNTER-TERRORISTS", energy_level, speed);
                    environment.setCounter_terrorists(aggressive);
                    break;
                case "cautious":
                    Cautious cautious = new Cautious( strategy, site, position, orientation, "COUNTER-TERRORISTS", energy_level, speed);
                    environment.setCounter_terrorists(cautious);
                    break;
                case "blind":
                    Blind blind = new Blind( strategy, site, position, orientation, "COUNTER-TERRORISTS", energy_level, speed);
                    environment.setCounter_terrorists(blind);
                    break;
            }
        }
        GameEngine gameengine = new GameEngine(environment);
        gameengine.play();
    }
}
