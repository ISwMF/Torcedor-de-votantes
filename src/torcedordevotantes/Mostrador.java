/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torcedordevotantes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Fabian Miranda
 */
public class Mostrador extends JFrame implements ActionListener {
    
    public JTextField[] Porcentajes;
    public JTextField[] Costos;
    public int votantes;
    public int candidatos;
    
    public Mostrador(int votantes, int candidatos) {
        
        this.votantes = votantes;
        this.candidatos = candidatos;
        
        Porcentajes = new JTextField[candidatos * votantes];
        Costos = new JTextField[votantes];
        
        JPanel PanelPorcentaje = new JPanel();
        JPanel PanelAceptar = new JPanel();
        JPanel PanelCostos = new JPanel();
        
        this.setLayout(new BorderLayout());
        PanelCostos.setLayout(new GridLayout(0, (votantes + 1)));
        PanelPorcentaje.setLayout(new GridLayout((candidatos + 1), (votantes + 1)));
        PanelAceptar.setLayout(new GridLayout());
        
        int recorrido = (candidatos + 1) * (votantes + 1);
        int j = 0;
        for (int i = 0; i < recorrido; i++) {
            if (i == 0) {
                PanelPorcentaje.add(new Label("C/V"));
            } else if (i <= votantes) {
                PanelPorcentaje.add(new Label("V" + i));
            } else if (i % (votantes + 1) == 0) {
                PanelPorcentaje.add(new Label("C" + (i/votantes)));
            } else {
                Porcentajes[j] = new JTextField();
                PanelPorcentaje.add(Porcentajes[j]);
                j++;
            }
        }
        j = 0;
        for (int i = 0; i < (votantes + 1); i++) {
            if (i == 0) {
                PanelCostos.add(new Label("Valor"));
            } else {
                Costos[j] = new JTextField();
                PanelCostos.add(Costos[j]);
                j++;
            }
            
        }
        JButton Enviar = new JButton("Aceptar");
        JButton Atrás = new JButton("Otra matriz");
        Atrás.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            TorcedordeVotantes.main(null);
        });
        Enviar.addActionListener(this);
        PanelAceptar.add(Enviar);
        PanelAceptar.add(Atrás);
        
        this.add(PanelPorcentaje, BorderLayout.PAGE_START);
        this.add(PanelCostos, BorderLayout.CENTER);
        this.add(PanelAceptar, BorderLayout.PAGE_END);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((votantes * 70), (candidatos * 85));
        this.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        int ganador = Integer.parseInt(JOptionPane.showInputDialog("¿Quién quiere que gane?"));
        if (ganador > votantes || ganador == 0) {
            JOptionPane.showMessageDialog(rootPane, "¿Haz olvidado tu cerebro en casa?");
        } else {
            double[][] matriz = new double[votantes][candidatos + 1];
            
            int k = 0;
            for (int i = 0; i < Porcentajes.length; i++) {
                if (i % candidatos == 0) {
                    try {
                        matriz[k][candidatos] = Double.parseDouble(Costos[k].getText());
                        k++;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
            
            int j = 0;
            k = 0;
            for (int i = 0; i < Porcentajes.length; i++) {
                if (j == votantes - 1) {
                    try {
                        matriz[j][k] = Double.parseDouble(Porcentajes[i].getText());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    j = 0;
                    k++;
                } else {
                    try {
                        matriz[j][k] = Double.parseDouble(Porcentajes[i].getText());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    j++;
                }
            }
            Matriz m = new Matriz(matriz, votantes, candidatos);
            
            if (m.verficarMatriz()) {
                mostrarPosiblesTorcidos(m.obtenerVotantesParaTorcer(m.ordenarVector(m.calcularCostoXProbabilidad(ganador, matriz))));
                double o = m.calcularMenorValor(m.ordenarVector(m.calcularCostoXProbabilidad(ganador, matriz)));
                JOptionPane.showMessageDialog(rootPane, "El menor valor es: " + o);
            } else {
                JOptionPane.showMessageDialog(rootPane, "La matriz es erronea");
            }
        }
    }
    
    public void mostrarPosiblesTorcidos(double[][] A) {
        JFrame Torcidos = new JFrame();
        System.out.println(A.length);
        Torcidos.setLayout(new GridLayout((A.length + 1), 0));
        JButton Aceptar = new JButton("Aceptar");
        JLabel[] Votantes = new JLabel[A.length];
        for (int i = 0; i < Votantes.length; i++) {
            
            Votantes[i] = new JLabel("El votante " + (int) (A[i][2] + 1) + " se deja torcer con " + A[i][1]);
            Torcidos.add(Votantes[i]);
        }
        Torcidos.add(Aceptar);
        Aceptar.addActionListener((ActionEvent e) -> {
            Torcidos.setVisible(false);
        });
        Torcidos.setSize(500, 60 * A.length);
        Torcidos.setLocationRelativeTo(null);
        Torcidos.setVisible(true);
    }
}
