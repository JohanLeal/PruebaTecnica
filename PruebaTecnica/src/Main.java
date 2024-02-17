
import Controlador.controlador;
import Modelo.modelo;
import Vista.vista;

public class Main {
    public static void main(String[] args) { 
        modelo mod = new modelo();
        vista view = new vista();
        
        controlador ctrl = new controlador(view,mod);
        ctrl.iniciar();
        view.setVisible(true);
    }
}
