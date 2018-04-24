/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torcedordevotantes;

/**
 *
 * @author Fabian Miranda
 */
public class Matriz {

    public double[][] matriz;
    public int votantes;
    public int candidatos;
    public int cantidadNecesariaParaGanar;

    public Matriz(double[][] PorXCos, int vot, int cand) {
        this.matriz = PorXCos;
        this.candidatos = cand;
        this.votantes = vot;
        this.cantidadNecesariaParaGanar = (int) Math.ceil(vot * 0.7);
    }

    public boolean verficarMatriz() {
        boolean correcto = true;
        double porcentaje;
        for (int i = 0; i < this.matriz.length; i++) {
            porcentaje = 0;
            for (int j = 0; j < this.matriz[i].length - 1; j++) {
                porcentaje = porcentaje + this.matriz[i][j];
            }
            if (porcentaje != 1.0) {
                correcto = false;
                break;
            }
        }
        return correcto;
    }

    public void imprimir(double A[][]) {
        System.out.println("Matriz: ");
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j] + " | ");
            }
            System.out.println("");
        }
    }

    //Calcula la relación entre costo y probabilidad. Se necesita un candidato y una matriz
    public double[][] calcularCostoXProbabilidad(int candidato, double A[][]) {
        candidato = candidato - 1;
        double[][] elección = new double[votantes][3];
        for (int i = 0; i < A.length; i++) {
            elección[i][2] = i;
            elección[i][0] = A[i][candidato];
            elección[i][1] = (1 - A[i][candidato]) * A[i][A[i].length - 1];
        }
        imprimir(elección);
        return elección;
    }

    //Sumatoria de los costos hasta el ~70% de los votantes 
    public double calcularMenorValor(double A[][]) {
        double MenorValor = 0;
        for (int i = 0; i < cantidadNecesariaParaGanar; i++) {
            MenorValor = MenorValor + A[i][1];
        }
        return MenorValor;
    }

    //Ordena el vector usando (bonbón, BURBUJA y bellota)
    public double[][] ordenarVector(double A[][]) {
        double[] AUX;
        for (int j = 0; j < A.length; j++) {
            for (int i = 0; i < A.length - 1; i++) {
                if (A[i][1] > A[i + 1][1]) {
                    AUX = A[i];
                    A[i] = A[i + 1];
                    A[i + 1] = AUX;
                }
            }
        }
        return A;
    }

    public double[][] obtenerVotantesParaTorcer(double[][] A) {
        double[][] Votantes = new double[cantidadNecesariaParaGanar][3];
        for (int i = 0; i < Votantes.length; i++) {
            Votantes[i][0] = A[i][0];
            Votantes[i][1] = A[i][1];
            Votantes[i][2] = A[i][2];
        }
        return Votantes;
    }

}
