package problema3_sistemacategorizacion;

import java.util.ArrayList;
import java.util.Scanner;

public class Problema3_SistemaCategorizacion {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la empresa: ");
        String nombre = entrada.nextLine();

        System.out.print("Ingrese el RUC: ");
        String ruc = entrada.nextLine();

        System.out.print("Ingrese la dirección: ");
        String direccion = entrada.nextLine();

        Empresa empresa = new Empresa(nombre, ruc, direccion);

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n Departamento " + i);

            System.out.print("Nombre del departamento: ");
            String nombreDepto = entrada.nextLine();

            System.out.print("Número de empleados: ");
            int empleados = entrada.nextInt();

            System.out.print("Producción anual: ");
            double produccion = entrada.nextDouble();
            entrada.nextLine();

            Departamento depto = new Departamento(nombreDepto, empleados, produccion);
            empresa.agregarDepartamento(depto);
        }
        empresa.mostrarInformacion();
    }
}

class Empresa {

    String nombre;
    String ruc;
    String direccion;
    Departamento[] departamentos;
    int contador = 0;

    public Empresa(String nombre, String ruc, String direccion) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.departamentos = new Departamento[3];
    }

    public void agregarDepartamento(Departamento d) {
        if (contador < 3) {
            departamentos[contador] = d;
            contador++;
        }
    }

    public void mostrarInformacion() {
        System.out.println("Empresa: " + nombre);
        System.out.println("RUC: " + ruc);
        System.out.println("Dirección: " + direccion);

        for (Departamento d : departamentos) {
            System.out.println("\n" + d);
        }
    }

    @Override
    public String toString() {
        return "Empresa: " + nombre + "\nRUC: " + ruc + "\nDirección: " + direccion;
    }
}

class Departamento {

    String nombre;
    int numeroEmpleados;
    double produccion;
    String categoria;

    public Departamento(String nombre, int empleados, double produccion) {
        this.nombre = nombre;
        this.numeroEmpleados = empleados;
        this.produccion = produccion;
        this.categoria = calcularCategoria();
    }

    private String calcularCategoria() {
        if (numeroEmpleados > 20 && produccion > 1000000) {
            return "A";
        } else if (numeroEmpleados == 20 && produccion == 1000000) {
            return "B";
        } else {
            return "C";
        }

    }

    @Override
    public String toString() {
        return "Departamento: " + nombre + "\nEmpleados: " + numeroEmpleados
                + "\nProducción anual: $" + produccion + "\nCategoría: " + categoria;
    }
}
