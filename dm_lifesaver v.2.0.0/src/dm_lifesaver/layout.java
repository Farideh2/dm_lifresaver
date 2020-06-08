package dm_lifesaver;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class layout extends JFrame implements ActionListener {

	private JButton calcular;
	private JButton tirar;
	private JButton añadir;
	private JButton dañar;

	private JRadioButton d4, d6, d8, d10, d12, d20;

	private JLabel dificultad, numero, level, info, info2, texto;

	private JTextField difficulty;
	private JTextField nivel;
	private JTextField jugadores2;
	private JTextField resultado2;
	private JTextField dado;
	private JTextField cantidad;
	private JTextField vida, name;

	private Panel panelSup, panelInf, panelInfInf, panelSup2, panelInf2;

	private JFrame main;

	private JPanel panelGeneral, panelGeneral2, panelGeneral3;

	private JTabbedPane tabbedPanel;

	private JTable monsters;

	DefaultTableModel table;

	private Image icono;

	int nDados;
	public int result;
	public int total;
	String[] nombre = {};

	@SuppressWarnings("unchecked")
	public layout() {

		main = new JFrame("Dm lifesaver");
		main.setLayout(new FlowLayout());
		tabbedPanel = new JTabbedPane();

		final String APP_ICON_PATH = "icons/dm_lifesaver.png";
		URL url = layout.class.getClassLoader().getResource("icons/dm_lifesaver.png");

		try {

			System.out.println("Path for file is :- \"" + url + "\"");
			icono = new ImageIcon(url).getImage();
			main.setIconImage(icono);
		} catch (Exception e) {
			System.out.println("Application icon not found");
		}

		d4 = new JRadioButton("d4");
		d6 = new JRadioButton("d6");
		d8 = new JRadioButton("d8");
		d10 = new JRadioButton("d10");
		d12 = new JRadioButton("d12");
		d20 = new JRadioButton("d20");

		ButtonGroup bgButton = new ButtonGroup();
		bgButton.add(d4);
		bgButton.add(d6);
		bgButton.add(d8);
		bgButton.add(d10);
		bgButton.add(d12);
		bgButton.add(d20);

		dificultad = new JLabel("dificultad");
		numero = new JLabel("numero");
		level = new JLabel("nivel");
		texto = new JLabel("La experiencia es: ");
		info = new JLabel("Indica el nivel, numero y dificultad");
		info2 = new JLabel("trivial, minimal, moderate, challenging, extreme");

		jugadores2 = new JTextField();
		difficulty = new JTextField();
		nivel = new JTextField();
		resultado2 = new JTextField();
		dado = new JTextField();
		cantidad = new JTextField();
		vida = new JTextField(0);
		name = new JTextField();

		jugadores2.setColumns(5);
		difficulty.setColumns(5);
		nivel.setColumns(5);
		resultado2.setColumns(3);
		resultado2.setEditable(false);
		dado.setColumns(10);
		dado.setEditable(false);
		cantidad.setColumns(10);

		panelGeneral = new JPanel();
		panelGeneral2 = new JPanel();
		panelGeneral3 = new JPanel();

		panelSup = new Panel();
		panelInf = new Panel();
		panelInfInf = new Panel();
		panelSup2 = new Panel();
		panelInf2 = new Panel();

		table = new DefaultTableModel();

		table.addColumn("enemy");
		table.addColumn("life");
		table.addRow(new Object[] { "enemy", "life" });
		monsters = new JTable(table);

		tirar = new JButton(new AbstractAction("roll") {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				final SwingWorker worker = new SwingWorker() {
					@Override
					protected Object doInBackground() throws Exception {
						int x = 01;
						int y = Integer.parseInt(cantidad.getText());
						total = 0;

						if (d4.isSelected()) {
							dice.dados = 4;
						} else if (d6.isSelected()) {
							dice.dados = 6;
						} else if (d8.isSelected()) {
							dice.dados = 8;
						} else if (d10.isSelected()) {
							dice.dados = 10;
						} else if (d12.isSelected()) {
							dice.dados = 12;
						} else if (d20.isSelected()) {
							dice.dados = 20;
						}

						do {
							dice.dados();
							result = dice.resultado;
							total = result + total;
							x++;
						} while (y >= x);

						return total;
					}
				};
				worker.execute();
				dado.setText(Integer.toString(total));
			}
		});

		añadir = new JButton(new AbstractAction("add") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Object[] monster = { "vida", vida, "nombre" };

				String name = JOptionPane.showInputDialog(monster, null);
				String life = vida.getText();

				table.addRow(new Object[] { name, life });

			}

		});

		calcular = new JButton(new AbstractAction("calculate") {
			@Override
			public void actionPerformed(ActionEvent e) {
				cr_calculator.difficultad = difficulty.getText();
				cr_calculator.nivel = Integer.parseInt(nivel.getText());
				cr_calculator.jugadores = Integer.parseInt(jugadores2.getText());
				cr_calculator.challenge();
				resultado2.setText(cr_calculator.resultado);
			}
		});

		dañar = new JButton(new AbstractAction("damage") {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String data = null;

				int[] row = monsters.getSelectedRows();

				if (row.length == 0) {

					JOptionPane.showMessageDialog(null, "please selecta a row", null, JOptionPane.WARNING_MESSAGE);

				} else {

					for (int i = 0; i < row.length; i++) {
						data = (String) monsters.getValueAt(row[i], 1);
					}

					String daño = JOptionPane.showInputDialog("How much damage??");

					int resultado = (Integer.parseInt(data)) - (Integer.parseInt(daño));

					table.setValueAt(resultado, monsters.getSelectedRow(), 1);

				}

			}

		});

		panelGeneral.setBackground(Color.LIGHT_GRAY);
		panelGeneral.setLayout(new GridLayout(5, 1));

		panelGeneral.add(info);
		panelGeneral.add(info2);
		panelGeneral.add(panelSup);
		panelGeneral.add(panelInf);
		panelGeneral.add(panelInfInf);

		panelSup2.setBackground(Color.LIGHT_GRAY);
		panelSup2.setLayout(new GridLayout(1, 6, 2, 2));
		panelSup2.setPreferredSize(new Dimension(2, 2));

		panelGeneral2.setBackground(Color.LIGHT_GRAY);
		panelGeneral2.setLayout(new GridLayout(5, 1));

		panelSup2.add(d4);
		panelSup2.add(d6);
		panelSup2.add(d8);
		panelSup2.add(d10);
		panelSup2.add(d12);
		panelSup2.add(d20);
		panelInf2.add(cantidad);
		panelInf2.add(tirar);
		panelInf2.add(dado);
		panelGeneral2.add(panelSup2, BoxLayout.X_AXIS);
		panelGeneral2.add(panelInf2, BoxLayout.X_AXIS);

		panelGeneral3.setBackground(Color.LIGHT_GRAY);

		panelGeneral3.add(monsters);
		panelGeneral3.add(añadir);
		panelGeneral3.add(dañar);

		panelSup.add(numero, BoxLayout.X_AXIS);
		panelSup.add(dificultad, BoxLayout.X_AXIS);
		panelSup.add(level, BoxLayout.X_AXIS);

		panelInf.add(jugadores2, BoxLayout.X_AXIS);
		panelInf.add(difficulty, BoxLayout.X_AXIS);
		panelInf.add(nivel, BoxLayout.X_AXIS);

		panelInfInf.add(resultado2, BoxLayout.X_AXIS);
		panelInfInf.add(texto, BoxLayout.X_AXIS);
		panelInfInf.add(calcular, BoxLayout.X_AXIS);

		tabbedPanel.addTab("Cr calculator", panelGeneral);
		tabbedPanel.addTab("dice roller", panelGeneral2);
		tabbedPanel.addTab("monsters", panelGeneral3);

		tabbedPanel.setVisible(true);
		main.add(tabbedPanel);
		main.setVisible(true);
		main.setSize(342, 245);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
