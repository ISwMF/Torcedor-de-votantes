/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torcedordevotantes;

import javax.swing.JOptionPane;

/**
 *
 * @author Fabian Miranda
 */
public class TorcedordeVotantes {

    /*
    [0.5][0.4][0.5][1.0][0.6]
    [0.2][0.3][0.5][0.0][0.1]
    [0.3][0.3][0.0][0.0][0.3]
    [17.][8.0][10.][1.0][15.]
    
    [13.6][5.6][5] [1]  [13.5]
     */
    public static void main(String[] args) {
        int vot = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de votantes"));
        int can = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de candidatos"));
        
        Mostrador M = new Mostrador(vot, can);
    }
}
