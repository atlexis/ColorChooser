package mainPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorChooserAlphaCSS extends JPanel {
	
	private int red;
	private int green;
	private int blue;
	private int alpha;
	
	private JLabel lblColorString;

	public ColorChooserAlphaCSS() {
		initUI();
	}
	
	private void initUI() {
		
		JPanel pnlColor = new JPanel(new BorderLayout());
		JLabel lblColor = new JLabel();
		lblColor.setPreferredSize(new Dimension(200,200));
		lblColorString = new JLabel();
		lblColorString.setHorizontalAlignment((JLabel.CENTER));
		pnlColor.add(lblColor, BorderLayout.CENTER);
		pnlColor.add(lblColorString, BorderLayout.SOUTH);
		
		
		JSlider sldRed = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		JSlider sldGreen = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		JSlider sldBlue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		JSlider sldAlpha = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 255);
		
		red = sldRed.getValue();
		green = sldGreen.getValue();
		blue = sldBlue.getValue();
		alpha = sldAlpha.getValue();
		updateColorStringLabel();
		
		sldRed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				red = sldRed.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue(), sldAlpha.getValue()));
				updateColorStringLabel();
			}
		});
		
		sldGreen.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				green = sldGreen.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue(), sldAlpha.getValue()));
				updateColorStringLabel();
			}
		});
		
		sldBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				blue = sldBlue.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue(), sldAlpha.getValue()));
				updateColorStringLabel();
			}
		});
		
		sldAlpha.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				alpha = sldAlpha.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue(), sldAlpha.getValue()));
				updateColorStringLabel();
			}
		});
		
		JPanel pnlSliders = new JPanel();
		pnlSliders.setLayout(new GridLayout(4,1));
		pnlSliders.add(sldRed);
		pnlSliders.add(sldGreen);
		pnlSliders.add(sldBlue);
		pnlSliders.add(sldAlpha);
		
		lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue(), sldAlpha.getValue()));
		lblColor.setOpaque(true);
		
		setLayout(new BorderLayout());
		add(pnlColor, BorderLayout.CENTER);
		add(pnlSliders, BorderLayout.SOUTH);	
	}
	
	private void updateColorStringLabel() {
		float cssAlpha = (float) alpha / 255;
		lblColorString.setText(String.format("%02X" + "%02X" + "%02X" + "x" + "%.1f", red, green, blue, cssAlpha));
	}
}
