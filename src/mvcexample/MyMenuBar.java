package mvcexample;

import java.awt.event.KeyEvent;
import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MyMenuBar implements MySetActions {
   private JMenuItem playMenItem = new JMenuItem();
   private JMenuItem pauseMenuItem = new JMenuItem();
   private JMenuItem stopMenItem = new JMenuItem();
   private JMenuBar menuBar = new JMenuBar();

   public MyMenuBar() {
      JMenu menu = new JMenu("Main Menu");
      menu.setMnemonic(KeyEvent.VK_M);
      menu.add(playMenItem);
      menu.add(pauseMenuItem);
      menu.add(stopMenItem);
      menuBar.add(menu);
   }

   public JMenuBar getMenuBar() {
      return menuBar;
   }

   @Override
   public void setPlayAction(Action playAction) {
      playMenItem.setAction(playAction);
   }

   @Override
   public void setStopAction(Action stopAction) {
      stopMenItem.setAction(stopAction);
   }

   @Override
   public void setPauseAction(Action pauseAction) {
      pauseMenuItem.setAction(pauseAction);
   }

}
