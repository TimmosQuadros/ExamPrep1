package threadsObserver;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import randomperson.RandomUser;
import randomperson.RandomUserGenerator;

public class RandomUserControl extends Thread {
    RandomUser random;
    List<UserObserver> observers = new ArrayList<>();
    
    public void registerObserver(UserObserver o){
		observers.add(o);
	}
  
  public RandomUser fetchRandomUser() {
   RandomUser user= null;
    try {
      user = RandomUserGenerator.getRandomUser();
    } catch (InterruptedException ex) {
      Logger.getLogger(RandomUserControl.class.getName()).log(Level.SEVERE, null, ex);
    }
   return user;
  }
  
  public void notifyAllObservers(){
		for(UserObserver observer : observers){
			observer.dataready(random);
		}
	} 

    @Override
    public synchronized void start() {
        super.start(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run() {
        super.run(); //To change body of generated methods, choose Tools | Templates.
        random=fetchRandomUser();
        notifyAllObservers();
    }
  
  
}
