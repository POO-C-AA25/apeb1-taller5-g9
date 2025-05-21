package problema2_sistemacalificaciones;

import java.util.ArrayList;
import java.util.Scanner;

public class Problema2_SistemaCalificaciones {

    Scanner entrada = new Scanner(System.in);
    ArrayList<Problema2_Estudiante> listaEstudiantes = new ArrayList<>();
    ArrayList<Problema2_Materia> listaMaterias = new ArrayList<>();

    public static void main(String[] args) {
        Problema2_SistemaCalificaciones sistema = new Problema2_SistemaCalificaciones();
        sistema.menu();
    }

    public void menu() {
        int opc;
        do {
            System.out.println("\nMenú:");
            System.out.println("[1] Ingresar estudiante");
            System.out.println("[2] Crear materia");
            System.out.println("[3] Vincular materia a estudiante");
            System.out.println("[4] Verificar aprobación");
            System.out.println("[5] Salir");
            opc = entrada.nextInt();

            switch (opc) {
                case 1:
                    ingresarEstudiante();
                case 2:
                    crearMateria();
                case 3:
                    vincularMateria();
                case 4:
                    verificarAprobacion();
            }

        } while (opc != 5);
    }

    public void ingresarEstudiante() {
        entrada.nextLine();
        System.out.print("Nombre del estudiante:");
        String nombre = entrada.nextLine();

        System.out.print("Edad:");
        int edad = entrada.nextInt();

        listaEstudiantes.add(new Problema2_Estudiante(nombre, edad));
        System.out.println("Estudiante registrado");
    }

    public void crearMateria() {
        entrada.nextLine();
        System.out.print("Nombre de la materia:");
        String nombreMateria = entrada.nextLine();

        System.out.print("Nota ACD sobre 10: ");
        double acd = entrada.nextDouble();

        System.out.print("Nota APE sobre 10: ");
        double ape = entrada.nextDouble();

        System.out.print("Nota AA sobre 10: ");
        double aa = entrada.nextDouble();

        listaMaterias.add(new Problema2_Materia(nombreMateria, acd, ape, aa));

    }

    public void vincularMateria() {
        entrada.nextLine();
        System.out.print("Ingrese el nombre del estudiante:");
        String nombreEst = entrada.nextLine();
        Problema2_Estudiante estudiante = buscarEstudiante(nombreEst);

        System.out.print("Ingrese el nombre de la materia: ");
        String nombreMat = entrada.nextLine();
        Problema2_Materia materia = buscarMateriaPorNombre(nombreMat);
        estudiante.materia = materia;
        System.out.println("Materia vinculada al estudiante");
    }

    public void verificarAprobacion() {
        entrada.nextLine();
        System.out.print("Ingrese el nombre del estudiante: ");
        String nombreEst = entrada.nextLine();

        Problema2_Estudiante estudiante = buscarEstudiante(nombreEst);

        if (estudiante == null) {
            System.out.println("Estudiante no encontrado");
            return;
        }

        Problema2_Materia materia = estudiante.materia;
        if (materia == null) {
            System.out.println("El estudiante no tiene una materia asignada");
            return;
        }

        double notaFinal = materia.calcularNotaFinal();
        System.out.println("Nota final:" + notaFinal);

        if (notaFinal >= 6.5) {
            System.out.println("Estudiante aprobado");
        } else {
            System.out.println("Reprobado");
            System.out.print("Ingrese nota de recuperación: ");
            double recuperacion = entrada.nextDouble();

            double notaConRecuperacion = materia.calcularConRecuperacion(recuperacion);
            System.out.println("Nota final con recuperación:" + notaConRecuperacion);

            if (notaConRecuperacion >= 6.5) {
                System.out.println("Aprobado con recuperación");
            } else {
                System.out.println(" Reprobado incluso con supletorios");
            }
        }
    }

    public Problema2_Estudiante buscarEstudiante(String nombre) {
        for (Problema2_Estudiante estudiante : listaEstudiantes) {
            if (estudiante.nombre.equalsIgnoreCase(nombre)) {
                return estudiante;
            }
        }
        return null;
    }

    public Problema2_Materia buscarMateriaPorNombre(String nombre) {
        for (Problema2_Materia materia : listaMaterias) {
            if (materia.nombreMateria.equalsIgnoreCase(nombre)) {
                return materia;
            }
        }
        return null;
    }
}

class Problema2_Materia {

    public String nombreMateria;
    public double acd;
    public double ape;
    public double aa;

    public Problema2_Materia(String nombreMateria, double acd, double ape, double aa) {
        this.nombreMateria = nombreMateria;
        this.acd = acd;
        this.ape = ape;
        this.aa = aa;
    }

    public double calcularNotaFinal() {
        return (acd * 0.35) + (ape * 0.35) + (aa * 0.30);
    }

    public double calcularConRecuperacion(double notaRecuperacion) {
        double acumulado = calcularNotaFinal() * 0.6;
        return acumulado + notaRecuperacion;
    }
}

class Problema2_Estudiante {

    public String nombre;
    public int edad;
    public Problema2_Materia materia;

    public Problema2_Estudiante(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
