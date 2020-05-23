/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;
import utils.*;
import algoritmoGenetico.AlgoritmoGenetico;
import algoritmoGenetico.Main;
import cromosoma.Cromosoma;




public class Vista extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form Vista
	 */
	public Vista() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        evaluarButton = new javax.swing.JButton();
        mejorAbsolutoText = new javax.swing.JLabel();
        peorAbsoluto = new javax.swing.JLabel();
        numOperaciones = new javax.swing.JLabel();
        
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
       
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
     
        tamPoblacionText = new javax.swing.JTextField();
        numGeneracionesText = new javax.swing.JTextField();
        mutacionBox = new javax.swing.JComboBox<>();
        seleccionBox = new javax.swing.JComboBox<>();
        cruceBox = new javax.swing.JComboBox<>();
        porcentajeCrucesText = new javax.swing.JTextField();
        porcentajeMutacionesText = new javax.swing.JTextField();
        porcentajeUniformeText = new javax.swing.JTextField();
       // numGenesBox = new javax.swing.JTextField();
        precisionText = new javax.swing.JTextField();
        elitismoText = new javax.swing.JTextField();
        graficaPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Algoritmo genetico");

        evaluarButton.setText("Evaluar poblacion");
        evaluarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                evaluarButtonActionPerformed(evt);
            }
        });

        mejorAbsolutoText.setText("Mejor absoluto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        

       

        jLabel1.setText("Tamano de poblacion:");
        jLabel2.setText("Numero de generaciones:");
        jLabel3.setText("Porcentaje de cruces:");
        jLabel4.setText("Porcentaje uniforme:");
        jLabel5.setText("Fichero:");
        jLabel6.setText("Elitismo:");
        jLabel7.setText("Tipo de seleccion:");
        jLabel8.setText("Mutacion:");
        jLabel9.setText("Tipo de cruce:");
        //jLabel10.setText("Numero genes:");
        jLabel11.setText("Porcentaje de mutaciones:");
        
        tamPoblacionText.setText("100");
        tamPoblacionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamPoblacionTextActionPerformed(evt);
            }
        });
        
        numGeneracionesText.setText("100");
        numGeneracionesText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	numGeneracionesTextActionPerformed(evt);
            }
        });
        
        porcentajeCrucesText.setText("0.6");
        porcentajeCrucesText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                porcentajeCrucesTextActionPerformed(evt);
            }
        });
        
        porcentajeMutacionesText.setText("0.05");
        porcentajeMutacionesText.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		porcentajeMutacionesTextActionPerformed(evt);
        	}
        });
        
        porcentajeUniformeText.setText("0");
        porcentajeUniformeText.addActionListener(new java.awt.event.ActionListener() {
        	public void actionPerformed(java.awt.event.ActionEvent evt) {
        		porcentajeUniformeTextActionPerformed(evt);
        	}
        });
        
        precisionText.setText("datos12.txt");
        precisionText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precisionTextActionPerformed(evt);
            }
        });
        
        elitismoText.setText("0.03");
        elitismoText.setToolTipText("");
        elitismoText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                elitismoTextActionPerformed(evt);
            }
        });

        seleccionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELPROPIA", "TORNEO", "ESTOCASTICO", "RANKING", "TRUNCAMIENTO10", "TRUNCAMIENTO50", "RULETA"}));
        seleccionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	seleccionBoxActionPerformed(evt);
            }
        });

        mutacionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PROPIA", "INTERCAMBIO", "INVERSION", "HEURISTICA" , "INSERCION"}));
        mutacionBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	mutacionBoxActionPerformed(evt);
            }
        });
        
        cruceBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CRUCEPROPIO", "OX", "CO", "CX" ,"ERX" ,"OX_PP","PMX"}));
        cruceBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cruceBoxActionPerformed(evt);
            }
        });
        
//        numGenesBox.setText("2");
//        numGenesBox.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//            	numGenesBoxTextActionPerformed(evt);
//            }
//        });
      
        
	
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(precisionText, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tamPoblacionText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(numGeneracionesText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(porcentajeCrucesText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(porcentajeMutacionesText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(porcentajeUniformeText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                     //.addComponent(numGenesBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(elitismoText, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mutacionBox, 0, 92, Short.MAX_VALUE)
                                    .addComponent(seleccionBox, 0, 1, Short.MAX_VALUE)
                                    .addComponent(cruceBox, 0, 1, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mutacionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seleccionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cruceBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tamPoblacionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numGeneracionesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentajeCrucesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(porcentajeMutacionesText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))                    
                    .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                	.addComponent(porcentajeUniformeText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(precisionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(elitismoText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    //.addComponent(numGenesBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        
        graficaPanel.setEnabled(false);
        graficaPanel.setVisible(true);

        javax.swing.GroupLayout graficaPanelLayout = new javax.swing.GroupLayout(graficaPanel);
        graficaPanel.setLayout(graficaPanelLayout);
        graficaPanelLayout.setHorizontalGroup(
            graficaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );
        graficaPanelLayout.setVerticalGroup(
            graficaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 377, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(379, 379, 379)
                        .addComponent(evaluarButton)
                        .addGap(45, 45, 45))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(mejorAbsolutoText, javax.swing.GroupLayout.PREFERRED_SIZE, 1785, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(peorAbsoluto, javax.swing.GroupLayout.PREFERRED_SIZE, 1785, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(numOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(graficaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(131, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(graficaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(mejorAbsolutoText, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(peorAbsoluto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(numOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(evaluarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

	// Codigo que se ejecuta al presionar el boton de "Evaluar poblacion"
	private void evaluarButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_evaluarButtonActionPerformed
		int tamPoblacion, numGeneraciones;
		double probabilidadCruce, probabilidadMutacion, probabilidadUniforme, elitismo;
		String precision;
		TipoSeleccion tipo_seleccion;
		TipoCruce tipo_cruce;
		TipoMutacion tipo_mutacion;

		AlgoritmoGenetico algoritmo;
		
		
		try {
			// Almacenamos cada valor introducido por el usuario en una variable
			tamPoblacion = Integer.parseInt(this.tamPoblacionText.getText());
			numGeneraciones = Integer.parseInt(this.numGeneracionesText.getText());
			//numGenes = Integer.parseInt(this.numGenesBox.getText());
			probabilidadCruce = Double.parseDouble(this.porcentajeCrucesText.getText());
			probabilidadMutacion = Double.parseDouble(this.porcentajeMutacionesText.getText());
			probabilidadUniforme = Double.parseDouble(this.porcentajeUniformeText.getText());
			precision = this.precisionText.getText();
			elitismo = Double.parseDouble(this.elitismoText.getText());
			tipo_seleccion = TipoSeleccion.valueOf(this.seleccionBox.getSelectedItem().toString());
			tipo_cruce = TipoCruce.valueOf(this.cruceBox.getSelectedItem().toString());
			tipo_mutacion = TipoMutacion.valueOf(this.mutacionBox.getSelectedItem().toString());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Valor no valido", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		
		
		

                algoritmo = new AlgoritmoGenetico(tipo_mutacion, tipo_seleccion, tipo_cruce, 
                								  tamPoblacion, numGeneraciones, 
                								  probabilidadCruce, probabilidadMutacion, probabilidadUniforme,
                								  precision, elitismo);
        int tam_elite = 0;
        		
        if (algoritmo.getBooleanElite()) {
        			// Extraemos los mejores individuos de la población (hacemos una copia)
        			tam_elite = algoritmo.getNumElites();
        }
        
		algoritmo.inicializaPoblacion();
		algoritmo.evaluaPoblacion();
		
		for (int i = 0; i < numGeneraciones; i++) {
			
			//if(algoritmo.getBooleanElite()) {
				algoritmo.seleccionaElite();
			//}
			//algoritmo.adaptar();
			algoritmo.seleccionaPoblacion();
			algoritmo.reproducePoblacion();
			algoritmo.mutaPoblacion();
			
			//if(algoritmo.getBooleanElite()) {
			algoritmo.incluyeElite();
			//}   
			
			algoritmo.evaluaPoblacion();
			algoritmo.aumentaGeneracion();
			
		}
		
		String aux ="Mejor combinacion: [";
        for(int i = 0; i < algoritmo.getMejor().getLongitud(); ++i) {
        	 
        	 aux += algoritmo.getMejor().getFenotipo().get(i).toString();
        	 if(i != algoritmo.getMejor().getLongitud() - 1 ) aux+= ", ";
        	 
        }
        aux += "]";
        String aux2 ="  Aptitud: ";
       
        aux2 += algoritmo.getMejor().getFitness();
        
        String aux3 ="	Peor combinacion: [";
        for(int i = 0; i < algoritmo.getMejor().getLongitud(); ++i) {
        	 
        	aux3 += algoritmo.getPeor().getFenotipo().get(i).toString();
        	 if(i != algoritmo.getPeor().getLongitud() - 1 ) aux3+= ", ";
        	 
        }
        aux3 += "]";
        String aux4 ="  Aptitud: ";
       
        aux4 += algoritmo.getPeor().getFitness();

        String aux5 = "Mutaciones: " + algoritmo.getNumMutaciones() + ", Cruces: " + algoritmo.getNumCruzes()+
        		", Selecciones: " + algoritmo.getNumSelecionados();
        
       
        this.mejorAbsolutoText.setText(aux  + aux2 );   
        this.peorAbsoluto.setText(aux3  + aux4 );
		this.numOperaciones .setText(aux5);

		// define your data
		double[] x = new double[algoritmo.getNumGeneraciones()];

		for (int i = 0; i < algoritmo.getNumGeneraciones(); i++) {
			x[i] = i;
		}

		double[] y = algoritmo.getMejoresAbsolutos();
		double[] z = algoritmo.getMejoresGeneracion();
		double[] k = algoritmo.getMedias();
		double[] p = algoritmo.getPeoresAbsolutos();
		
		Main.plot.removeAllPlots();
		Main.plot.addLinePlot("Mejor absoluto", Color.CYAN, x, y);
		Main.plot.addLinePlot("Media fitness", Color.YELLOW, x, k);
		Main. plot.addLinePlot("Mejor", Color.GREEN , x, z);
		Main. plot.addLinePlot("Peor", Color.RED, x, p);


		
//		if(algoritmo.getBooleanElite()) {
//			Main. plot.addLinePlot("Mejor", Color.GREEN, x, y);
//		}else
//			Main. plot.addLinePlot("Mejor", Color.GREEN, x, z);
		
		
		

	}// GEN-LAST:event_evaluarButtonActionPerformed

	private void precisionTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_precisionTextActionPerformed
		// TODO add your handling code here:		
	}// GEN-LAST:event_precisionTextActionPerformed

	private void numGeneracionesTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_numGeneracionesTextActionPerformed
		// TODO add your handling code here:		
	}// GEN-LAST:event_numGeneracionesTextActionPerformed
	

	private void porcentajeCrucesTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_porcentajeCrucesTextActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_porcentajeCrucesTextActionPerformed

	private void tamPoblacionTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tamPoblacionTextActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_tamPoblacionTextActionPerformed

	private void numGenesBoxTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_numGenesBoxTextActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_numGenesBoxTextActionPerformed

	private void seleccionBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_seleccionBoxActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_seleccionBoxActionPerformed

	private void cruceBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cruceBoxActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cruceBoxActionPerformed

	private void mutacionBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_funcionBoxActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_funcionBoxActionPerformed

	private void elitismoTextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_elitismoTextActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_elitismoTextActionPerformed

	private void porcentajeMutacionesTextActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	

	private void porcentajeUniformeTextActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField elitismoText;
    private javax.swing.JButton evaluarButton;
    private javax.swing.JComboBox mutacionBox;
    private javax.swing.JComboBox seleccionBox;
    private javax.swing.JComboBox cruceBox;

    private javax.swing.JPanel graficaPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel mejorAbsolutoText;
    private javax.swing.JLabel peorAbsoluto;
    private javax.swing.JLabel numOperaciones;
    private javax.swing.JTextField numGeneracionesText;
    //private javax.swing.JTextField numGenesBox;
    private javax.swing.JTextField porcentajeCrucesText;
    private javax.swing.JTextField porcentajeMutacionesText;
    private javax.swing.JTextField porcentajeUniformeText;
    private javax.swing.JTextField precisionText;
    private javax.swing.JTextField tamPoblacionText;
    // End of variables declaration//GEN-END:variables

	public javax.swing.JPanel getGraficaPanel() {
		return this.graficaPanel;
	}

	public javax.swing.JLabel getJLabel9() {
		return this.jLabel9;
	}

//	public JTextField getNumGenesBox() {
//		return this.numGenesBox;
//	}
}
