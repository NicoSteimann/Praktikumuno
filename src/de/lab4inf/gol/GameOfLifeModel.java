/*
 * Project: GoL
 *
 * Copyright (c) 2004-2022,  Prof. Dr. Nikolaus Wulff
 * University of Applied Sciences, Muenster, Germany
 * Lab for computer sciences (Lab4Inf).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package de.lab4inf.gol;

/**
 * Game of Life model class holding the internal state of a NxM GoL field.
 *
 * @author nwulff
 */
public class GameOfLifeModel {
    // state of the  cells
    private boolean[][] actualState;
    // actual generation counter
    private int generation;

    /**
     * Constructor for a GoL model instance.
     *
     * @param n the number of rows
     * @param m the number of columns
     */
    public GameOfLifeModel(int n, int m) {
        if (n < 1 || m < 1) {
            System.err.printf("n: %d, m: %d illegal values\n", n, m);
            System.exit(-1);
        }
        generation = 0;
        actualState = new boolean[n][m];
    }

    /**
     * Number of columns of this GoL.
     *
     * @return number of columns
     */
    public int columns() {
        return actualState[0].length;
    }

    /**
     * Number of rows of this GoL.
     *
     * @return number of rows
     */
    public int rows() {
        return actualState.length;
    }

    /**
     * Get the internal state of cell (j,k).
     *
     * @param j row index
     * @param k column index
     * @return state of the cell
     */
    public boolean get(int j, int k) {
        return actualState[j][k];
    }

    /**
     * Set the internal state of cell (j,k).
     *
     * @param j     row index
     * @param k     column index
     * @param value to set
     */
    public void set(int j, int k, boolean value) {
        actualState[j][k] = value;
    }

    /**
     * Indicate if this GoL is alive, i.e. there is any living cell.
     *
     * @return live indicator
     */
    public boolean isAlive() {
        for (int i = 0; i < rows(); i++) {
            for (int j = 0; columns() > j; j++) {
                if (actualState[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Set the given pattern at place (r,c).
     *
     * @param r       starting row
     * @param c       starting column
     * @param pattern to set
     */
    public void setPattern(int r, int c, boolean[][] pattern) {
        int u, v;
        u = pattern.length;
        v = pattern[0].length;
        for (int pRow = 0; pRow < u; pRow++) {
            for (int pCol = 0; pCol < v; pCol++) {
                set(r + pRow, c + pCol, pattern[pRow][pCol]);
            }
        }
    }

    /**
     * Get the actual generation counter.
     *
     * @return generation counter
     */
    public int getGeneration() {
        return generation;
    }

    /**
     * Calculate the next GoL generation.
     */
    public void nextGeneration() {
        boolean[][] kopie = new boolean[rows()][columns()];
        for (int i = 0; i < rows(); i++) {

            for (int j = 0; j < columns(); j++) {
               int nachbarAnzahl = nachbarnZaehlen(i,j);
                if (actualState[i][j]) {
                    kopie[i][j] = nachbarAnzahl==3 || nachbarAnzahl==2;
                }
                else {
                    kopie[i][j] = nachbarAnzahl==3;
                }

            }
        }
        actualState = kopie ;
        generation++;
    }

    public int nachbarnZaehlen(int i, int j){
        int zaehler=0;
        if(pruefung(i+1,j)){
            zaehler=zaehler+1;
        } if(pruefung(i+1,j+1)){
            zaehler=zaehler+1;
        }if(pruefung (i+1,j-1)){
            zaehler=zaehler+1;
        }if(pruefung(i,j+1)){
            zaehler=zaehler+1;
        }if(pruefung(i,j-1)){
            zaehler=zaehler+1;
        }if(pruefung(i-1,j+1)){
            zaehler=zaehler+1;
        }if(pruefung(i-1,j)){
            zaehler=zaehler+1;
        }if(pruefung(i-1,j-1)){
            zaehler=zaehler+1;
        }

       return zaehler;
    }
    public boolean pruefung(int i,int j){
        if(i<rows() && i>=0 && j<columns() && j>=0) {
            return actualState[i][j];
        }

        return false;
    }
}

