
import Controlador.controlador;
import Modelo.modelo;
import Vista.CrearCuenta;
import Vista.Index;

public class Main {
    public static void main(String[] args) { 
        modelo mod = new modelo();
        Index view = new Index();
        CrearCuenta regis = new CrearCuenta();
        controlador ctrl = new controlador(view,mod,regis);
        ctrl.iniciar();
        view.setVisible(true);
    }
}
