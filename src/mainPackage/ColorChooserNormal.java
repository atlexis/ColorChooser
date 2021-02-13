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

/**
 * Main mode of the application.
 * Choose color from RGB-sliders and see the Hex-code and a preview of the color
 * @author Alexander Libot
 *
 */
@SuppressWarnings("serial")
public class ColorChooserNormal extends ColorChooser {

	private JLabel lblColorString;

	public ColorChooserNormal() {
		initUI();
	}
	
	/**
	 * Initializes the UI
	 */
	private void initUI() {
		
		//Color preview
		JPanel pnlColor = new JPanel(new BorderLayout());
		JLabel lblColor = new JLabel(); //Color preview
		lblColor.setPreferredSize(new Dimension(200,200));
		lblColorString = new JLabel();
		lblColorString.setHorizontalAlignment((JLabel.CENTER));
		pnlColor.add(lblColor, BorderLayout.CENTER);
		pnlColor.add(lblColorString, BorderLayout.SOUTH);
		
		//Sliders
		JSlider sldRed = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		JSlider sldGreen = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		JSlider sldBlue = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 0);
		
		red = sldRed.getValue();
		green = sldGreen.getValue();
		blue = sldBlue.getValue();
		updateColorStringLabel();
		
		sldRed.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				red = sldRed.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue()));
				updateColorStringLabel();
			}
		});
		
		sldGreen.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				green = sldGreen.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue()));
				updateColorStringLabel();
			}
		});
		
		sldBlue.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				blue = sldBlue.getValue();
				lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue()));
				updateColorStringLabel();
			}
		});
		
		JPanel pnlSliders = new JPanel();
		pnlSliders.setLayout(new GridLayout(3,1));
		pnlSliders.add(sldRed);
		pnlSliders.add(sldGreen);
		pnlSliders.add(sldBlue);
		
		//Init color-preview
		lblColor.setBackground(new Color(sldRed.getValue(), sldGreen.getValue(), sldBlue.getValue()));
		lblColor.setOpaque(true);
		
		//Init app-panel
		setLayout(new BorderLayout());
		add(pnlColor, BorderLayout.CENTER);
		add(pnlSliders, BorderLayout.SOUTH);	
	}
	
	/**
	 * Update the hex-code label with values from sliders.
	 */
	private void updateColorStringLabel() {
		lblColorString.setText(String.format("%02X" + "%02X" + "%02X", red, green, blue));
	}
}
