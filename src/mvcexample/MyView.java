package mvcexample;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class MyView implements MySetActions, MyProgressMonitor {
   private JButton playButton = new JButton();
   private JButton stopButton = new JButton();
   private JButton pauseButton = new JButton();
   private JPanel mainPanel = new JPanel();
   private JProgressBar progressBar = new JProgressBar();

   public MyView() {
      progressBar.setBorderPainted(true);

      JPanel btnPanel = new JPanel(new GridLayout(1, 0, 5, 0));
      btnPanel.add(playButton);
      btnPanel.add(pauseButton);
      btnPanel.add(stopButton);

      mainPanel.setLayout(new BorderLayout(0, 5));
      mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
      mainPanel.add(btnPanel, BorderLayout.CENTER);
      mainPanel.add(progressBar, BorderLayout.PAGE_END);
   }

   @Override
   public void setPlayAction(Action playAction) {
      playButton.setAction(playAction);
   }

   @Override
   public void setStopAction(Action stopAction) {
      stopButton.setAction(stopAction);
   }

   @Override
   public void setPauseAction(Action pauseAction) {
      pauseButton.setAction(pauseAction);
   }

   @Override
   public void setProgress(int progress) {
      progressBar.setValue(progress);
   }

   public JComponent getMainPanel() {
      return mainPanel;
   }

}
