package com.shopsphere.swing;
import javax.swing.*;import java.awt.*;
public class AdminLauncher {
 public static void main(String[] args){SwingUtilities.invokeLater(()->{
  try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}catch(Exception ignored){}
  JFrame frame=new JFrame("ShopSphere - Desktop Admin Demo");frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setSize(680,420);frame.setLocationRelativeTo(null);
  JPanel root=new JPanel(new BorderLayout(12,12));root.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
  JLabel title=new JLabel("ShopSphere Administration",SwingConstants.CENTER);title.setFont(title.getFont().deriveFont(Font.BOLD,24f));root.add(title,BorderLayout.NORTH);
  String[] columns={"Module","Status"};Object[][] rows={{"Product management","Demo module"},{"Category management","Demo module"},{"Customer management","Demo module"},{"Order management","Demo module"},{"Inventory","Use web catalog / network demo"}};
  JTable table=new JTable(rows,columns);root.add(new JScrollPane(table),BorderLayout.CENTER);
  JButton close=new JButton("Close");close.addActionListener(e->frame.dispose());JPanel bottom=new JPanel(new FlowLayout(FlowLayout.RIGHT));bottom.add(close);root.add(bottom,BorderLayout.SOUTH);
  frame.setContentPane(root);frame.setVisible(true);
 });}
}
