package ch.iso.m322.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import ch.iso.m322.controller.AddExerciseListener;
import ch.iso.m322.controller.AddTrackerListener;
import ch.iso.m322.controller.ExerciseLoaderListener;
import ch.iso.m322.controller.TrackerLoaderListener;
import ch.iso.m322.model.Exercise;
import ch.iso.m322.model.Tracker;
import ch.iso.m322.ressources.FrameConstants;

public class MyFrame extends JFrame {

	private static final long serialVersionUID = 2438104279704561527L;

	private MyMenu myMenuBar;
	private JTextField myName;
	private JTextField exercise;
	private JList<Exercise> exerciseJList;
	private JButton focusElement;
	private JTable TrackerTable;
	private JComboBox<Integer> rpe;
	private JSlider weight;
	private String date;

	public MyFrame() throws IOException {
		super(FrameConstants.FRAME_TITLE);

		this.myMenuBar = new MyMenu();
		this.setJMenuBar(this.myMenuBar);

		this.add(createContent());
	}

	// main panel
	private JPanel createContent() throws IOException {
		final JPanel content = new JPanel(new GridLayout(1, 2, 5, 5));
		content.setBackground(new Color(255, 255, 255));

		content.add(contentTrackingTable());
		content.add(contentDataForTable());

		return content;
	}

	private JPanel contentTrackingTable() {
		final JPanel content = new JPanel(new BorderLayout(5, 5));

		String[] title = { FrameConstants.FRAME_TABLE_NAME, FrameConstants.FRAME_TABLE_EXERCISE,
				FrameConstants.FRAME_TABLE_DATE, FrameConstants.FRAME_TABLE_WEIGHT, FrameConstants.FRAME_TABLE_RPE };

		final JTable table = new JTable(new DefaultTableModel(title, 0));

		final DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

		Iterator<Tracker> data = new TrackerLoaderListener();
		while (data.hasNext()) {
			tableModel.addRow(data.next().toStringArray());
		}

		setGetTrackerTable(table);

		JScrollPane sp = new JScrollPane(table);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		content.add(sp);

		return content;
	}

	private JPanel contentDataForTable() throws IOException {
		final JPanel content = new JPanel(new GridLayout(1, 2, 3, 3));

		content.add(contentRightLeft());
		content.add(contentRightRight());

		return content;
	}

	private JPanel contentRightLeft() throws IOException {
		final JPanel content = new JPanel(new BorderLayout(5, 5));
		final JPanel content2 = new JPanel(new GridLayout(5, 1, 5, 5));
		final JPanel content3 = new JPanel(new BorderLayout(5, 5));
		String file = MyFrame.class.getResource("chae.jpg").getPath();
		//System.out.println(file);
		BufferedImage myPicture = ImageIO.read(new File(file));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setSize(new Dimension(10, 10));
		content3.add(picLabel, BorderLayout.CENTER);
		String file1 = MyFrame.class.getResource("penchod.png").getPath();
		BufferedImage myPicture1 = ImageIO.read(new File(file1));
		JLabel picLabel1 = new JLabel(new ImageIcon(myPicture1));
		picLabel1.setSize(new Dimension(10, 3));
		content3.add(picLabel, BorderLayout.CENTER);
		content2.add(content3);
		content2.add(contentWeightSlider());
		content2.add(contentRPE());
		content2.add(contentAddNameAddToTable());
		content2.add(picLabel1);
		content.add(content2, BorderLayout.CENTER);
		return content;
	}

	private JPanel contentRightRight() {
		final JPanel content = new JPanel(new BorderLayout(5, 5));
		content.add(contentExercise());

		return content;
	}

	private JPanel contentExercise() {
		final JPanel content = new JPanel(new BorderLayout(5, 5));
		final JPanel content2 = new JPanel(new GridLayout(4, 1, 5, 5));

		DefaultListModel<Exercise> listModel = new DefaultListModel<>();
		JList<Exercise> list = new JList<>(listModel);

		Iterator<Exercise> data = new ExerciseLoaderListener();
		while (data.hasNext()) {
			listModel.addElement(data.next());
		}
		
		final JLabel lex = new JLabel();
		lex.setText("Exercise");
		
		setExerciseJList(list);


		final JLabel lExercise = new JLabel();
		lExercise.setText("Neue Exercise");

		final JTextField tExercise = new JTextField();
		this.setExercise(tExercise);

		final JButton bAdd = new JButton(FrameConstants.FRAME_BUTTON_ADD);
		bAdd.addActionListener(new AddExerciseListener(this.exercise));

		setFocusElement(bAdd);
		content2.add(lExercise);
		content2.add(tExercise);
		content2.add(bAdd);
		content.add(lex, BorderLayout.NORTH);
		content.add(list, BorderLayout.CENTER);
		content.add(content2, BorderLayout.SOUTH);

		return content;
	}

	private JPanel contentWeightSlider() {
		final JPanel content = new JPanel(new BorderLayout(5, 5));

		JLabel minWeight = new JLabel("0 KG");
		JLabel maxWeight = new JLabel("500 KG");
		final JLabel currWeight = new JLabel("0 KG");

		JSlider slider = new JSlider(0, 500);

		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider) e.getSource();
				currWeight.setText(String.valueOf(s.getValue()) + " KG");

			}
		});

		this.weight = slider;
		slider.setValue(0);
		JPanel labelsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		labelsPanel.add(minWeight);
		labelsPanel.add(currWeight);
		labelsPanel.add(maxWeight);

		content.add(labelsPanel, BorderLayout.CENTER);
		content.add(slider, BorderLayout.SOUTH);

		return content;
	}

	private JPanel contentRPE() {
	    final JPanel content = new JPanel(new GridLayout(2, 1, 0, 0));
	    final JPanel content3 = new JPanel(new BorderLayout());
	    final JPanel content2 = new JPanel(new BorderLayout());
	    JComboBox<Integer> comboBox = new JComboBox<>();
	    JLabel lRPE = new JLabel();
	    lRPE.setText("RPE");
	    // Add data
	    for (int i = 1; i <= 10; i++) {
	        comboBox.addItem(i);
	    }

	    this.rpe = comboBox;
	    content2.add(lRPE, BorderLayout.CENTER);
	    content3.add(comboBox, BorderLayout.NORTH);
	    content.add(content2);
	    content.add(content3);
	    return content;
	}

	private JPanel contentAddNameAddToTable() {
		final JPanel content = new JPanel(new GridLayout(2, 1, 5, 5));
		final JPanel contentTop = new JPanel(new GridLayout(2, 1, 5, 5));
		final JPanel contentLow = new JPanel(new GridLayout(2, 1, 5, 5));
		final JButton tAddTable = new JButton("Add");
		tAddTable.addActionListener(new AddTrackerListener());
		final JLabel lAddTable = new JLabel("Add To TABLE");
		final JLabel lName = new JLabel("Name");
		final JTextField tName = new JTextField();
		this.setMyName(tName);
		contentTop.add(lName);
		contentTop.add(tName);
		contentLow.add(lAddTable);
		contentLow.add(tAddTable);
		content.add(contentTop);
		content.add(contentLow);
		return content;
	}

	public String setDate() {
		Date currentDate = new Date();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		return dateFormat.format(currentDate);

	}

	public JTextField getExercise() {
		return exercise;
	}

	public void setExercise(JTextField exercise) {
		this.exercise = exercise;
	}

	public JTextField getMyName() {
		return myName;
	}

	public void setMyName(JTextField myName) {
		this.myName = myName;
	}

	public JButton getFocusElement() {
		return focusElement;
	}

	private void setFocusElement(JButton focusElement) {
		this.focusElement = focusElement;
	}

	public JList<Exercise> getExerciseJList() {
		return exerciseJList;
	}

	public void setExerciseJList(JList<Exercise> exerciseJList) {
		this.exerciseJList = exerciseJList;
	}

	public JTable getGetTrackerTable() {
		return TrackerTable;
	}

	public void setGetTrackerTable(JTable getTrackerTable) {
		this.TrackerTable = getTrackerTable;
	}

	public String getDate() {
		return date;
	}

	public Tracker getData() {
		String name = this.myName.getText();
		Exercise exercise = this.exerciseJList.getSelectedValue();
		String date = this.setDate();
		int weight = this.weight.getValue();
		int rpe = (int) this.rpe.getSelectedItem();
		return new Tracker(name, exercise, date, weight, rpe);
	}

}
