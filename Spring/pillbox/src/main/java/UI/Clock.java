package UI;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Timer;

public class Clock{

    protected final JLabel time = new JLabel();
    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    private int currentSecond = 0;
    private Calendar calendar;

    private void reset(){
        this.calendar = Calendar.getInstance();
        this.currentSecond = calendar.get(Calendar.SECOND);
    }

    public void setSize(Dimension dimension){
        this.time.setPreferredSize(dimension);
        this.time.setHorizontalAlignment(SwingConstants.CENTER);
        this.time.setFont(new Font(this.time.getFont().getName(), Font.BOLD, 16));
    }
    public void start(){
        reset();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate( new TimerTask(){
            public void run(){
                if( currentSecond == 60 ) {
                    reset();
                }
                time.setText( String.format("%s:%02d", sdf.format(calendar.getTime()), currentSecond ));
                currentSecond++;
            }
        }, 0, 1000 );
    }
}
