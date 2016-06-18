package mvcexample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import javax.swing.Timer;
import javax.swing.event.SwingPropertyChangeSupport;

public class MyModel {
   public final static String PROGRESS = "progress";
   protected static final int MAX_PROGRESS = 100; 
   private MyState state = null;
   private SwingPropertyChangeSupport pcSupport = new SwingPropertyChangeSupport(
         this);
   private Timer timer;
   private int progress = 0;

   public MyState getState() {
      return state;
   }

   public void setState(MyState state) {
      MyState oldValue = this.state;
      MyState newValue = state;
      this.state = newValue;
      pcSupport.firePropertyChange(MyState.class.getName(), oldValue, newValue);
   }

   public int getProgress() {
      return progress;
   }

   public void setProgress(int progress) {
      Integer oldValue = this.progress;
      Integer newValue = progress;
      this.progress = newValue;
      pcSupport.firePropertyChange(PROGRESS, oldValue, newValue);
   }

   public void play() {
      MyState oldState = getState();
      setState(MyState.PLAY);

      if (oldState == MyState.PAUSE) {
         if (timer != null) {
            timer.start();
            return;
         }
      }
      int timerDelay = 50;
      // simulate playing ....
      timer = new Timer(timerDelay, new ActionListener() {
         int timerProgress = 0;

         @Override
         public void actionPerformed(ActionEvent actEvt) {
            timerProgress++;
            setProgress(timerProgress);
            if (timerProgress >= MAX_PROGRESS) {
               setProgress(0);
               MyModel.this.stop();
            }
         }
      });
      timer.start();
   }

   public void pause() {
      setState(MyState.PAUSE);
      if (timer != null && timer.isRunning()) {
         timer.stop();
      }
   }

   public void stop() {
      setState(MyState.STOP);
      setProgress(0);
      if (timer != null && timer.isRunning()) {
         timer.stop();
      }
      timer = null;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      pcSupport.removePropertyChangeListener(listener);
   }
}
