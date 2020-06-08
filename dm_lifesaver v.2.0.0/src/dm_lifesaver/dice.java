package dm_lifesaver;

import javax.swing.*;

public class dice extends JFrame {
	protected static int resultado = 0;
	public static int dados;
	public static int numero;

	static int x = 1, y = 0;
	public static int dado;

	public static void dados() {
			dado = (int) (Math.random() * dados + 1);
			x++;
			resultado = dado;

	}
}
