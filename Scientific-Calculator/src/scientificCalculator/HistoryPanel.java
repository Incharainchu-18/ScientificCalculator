package scientificCalculator;

import javax.swing.*;

public class HistoryPanel extends JPanel {

    JTextArea historyArea;

    public HistoryPanel(){

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        historyArea=new JTextArea(20,15);

        add(new JScrollPane(historyArea));
    }

    public void addHistory(String text){
        historyArea.append(text+"\n");
    }
}