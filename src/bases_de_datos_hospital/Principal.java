/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bases_de_datos_hospital;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("No se pudo aplicar Nimbus: " + e.getMessage());
        }
     SwingUtilities.invokeLater(() -> {
            VentanaPaciente ventana = new VentanaPaciente();
            ventana.setVisible(true);
        });
    }
    
}
