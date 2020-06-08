package dm_lifesaver;

import java.util.*;

public class cr_calculator {
	layout apariencia = new layout();

	static int nivel;
	static int jugadores;
	static String difficultad;
	static String resultado;

	public static void challenge() {
		int[][] crTable = { { 50, 100, 150, 200, 250 }, 
							{ 75, 150, 225, 300, 375 },
							{ 100, 200, 300, 400, 500},
							{ 175, 350, 525, 700, 875},
							{ 275, 550, 825, 1100, 1375},};

		int x = 1;

		int y = 0;

		if (difficultad.contentEquals("trivial")) {
			y = 0;
		}
		;
		if (difficultad.contentEquals("minimal")) {
			y = 1;
		}
		;
		if (difficultad.contentEquals("moderate")) {
			y = 2;
		}
		;
		if (difficultad.contentEquals("challenging")) {
			y = 3;
		}
		;
		if (difficultad.contentEquals("extreme")) {
			y = 4;
		}
		;

		int contador = crTable[nivel - 1][y];
		int multi = contador * jugadores;
		resultado = Integer.toString(multi);

	};

}
