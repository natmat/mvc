package mvcexample;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class MyControl {
   private MyModel model;
   private PlayAction playAction = new PlayAction();
   private PauseAction pauseAction = new PauseAction();
   private StopAction stopAction = new StopAction();
   private List<MyProgressMonitor> progMonitorList = new ArrayList<MyProgressMonitor>();

   public MyControl(MyModel model) {
      this.model = model;

      model.addPropertyChangeListener(new MyPropChangeListener());
   }

   public void addProgressMonitor(MyProgressMonitor progMonitor) {
      progMonitorList.add(progMonitor);
   }

   public void addView(MySetActions setActions) {
      setActions.setPlayAction(playAction);
      setActions.setPauseAction(pauseAction);
      setActions.setStopAction(stopAction);
   }

   private class MyPropChangeListener implements PropertyChangeListener {
      @Override
      public void propertyChange(PropertyChangeEvent pcEvt) {
         if (MyState.class.getName().equals(pcEvt.getPropertyName())) {
            MyState state = (MyState) pcEvt.getNewValue();

            if (state == MyState.PLAY) {
               playAction.setEnabled(false);
               pauseAction.setEnabled(true);
               stopAction.setEnabled(true);
            } else if (state == MyState.PAUSE) {
               playAction.setEnabled(true);
               pauseAction.setEnabled(false);
               stopAction.setEnabled(true);
            } else if (state == MyState.STOP) {
               playAction.setEnabled(true);
               pauseAction.setEnabled(false);
               stopAction.setEnabled(false);
            }
         }
         if (MyModel.PROGRESS.equals(pcEvt.getPropertyName())) {
            for (MyProgressMonitor progMonitor : progMonitorList) {
               int progress = (Integer) pcEvt.getNewValue();
               progMonitor.setProgress(progress);
            }            
         }
      }
   }

   private class PlayAction extends AbstractAction {
      public PlayAction() {
         super("Play");
         putValue(MNEMONIC_KEY, KeyEvent.VK_P);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         model.play();
      }
   }

   private class StopAction extends AbstractAction {
      public StopAction() {
         super("Stop");
         putValue(MNEMONIC_KEY, KeyEvent.VK_S);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         model.stop();
      }
   }
   private class PauseAction extends AbstractAction {
      public PauseAction() {
         super("Pause");
         putValue(MNEMONIC_KEY, KeyEvent.VK_A);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         model.pause();
      }
   }
}

