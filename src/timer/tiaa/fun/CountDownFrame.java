package timer.tiaa.fun;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class CountDownFrame extends JFrame // extract layout to own class
{
	private static final long serialVersionUID = 1L;

	private Timer timer;
	private JLabel count;
	private JLabel message;

	private Countdown countdown;

	public CountDownFrame(Countdown countdown) {
		this.countdown = countdown;
		setUndecorated(true);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setAlwaysOnTop(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				setUndecorated(!isUndecorated());
				revalidate();
				setVisible(true);
			}
		});
		add(initText());
		startTimer();
		setVisible(true);
	}

	private JPanel initText() // rename and private
	{
		count = new JLabel();
		count.setFont(new Font("Arial Black", Font.BOLD, 200));
		count.setHorizontalAlignment(SwingConstants.CENTER);
		count.setBackground(Color.RED);
		message = new JLabel();
		message.setFont(new Font("Arial Black", Font.BOLD, 100));
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.add(count);
		panel.add(message);
		panel.setBackground(Color.WHITE);
		message.setHorizontalAlignment(SwingConstants.CENTER);
		return panel;
	}

	private void startTimer() // rename
	{
		count.setText(countdown.toReadableString()); // extract method
		message.setText(countdown.getMessage());
		timer = new Timer(1000, (ActionListener) new TimerListener());
		timer.setRepeats(true);
		timer.start();
	}

	private class TimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			countdown.decrement(); // separate layout from logic
			count.setForeground(countdown.isCloseToEnd() ? Color.RED : Color.BLACK); // extract conditions
			if (countdown.isFinished()) { // extract conditions 
				count.setText("");
				message.setText("Timeout!!!");
				timer.stop();
			} else {
				count.setText(countdown.toReadableString());
			}
		}
	}
}