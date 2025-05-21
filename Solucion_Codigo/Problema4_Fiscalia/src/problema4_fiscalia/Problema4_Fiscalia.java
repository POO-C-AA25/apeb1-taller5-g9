package problema4_fiscalia;

import java.util.ArrayList;
import java.util.Scanner;

public class Problema4_Fiscalia {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        ArrayList<CasoCorrupcion> casos = new ArrayList<>();
        char opcion = 'S';

        while (opcion == 'S') {
            System.out.println("\n REGISTRAR NUEVO CASO DE CORRUPCION");
            System.out.print("Nombre del caso: ");
            String nombre = entrada.nextLine();

            System.out.print("¿Cuántos dias han pasado desde el"
                    + " inicio del caso?: ");
            int diasTranscurridos = entrada.nextInt();
            entrada.nextLine(); // 

            System.out.print("Numero de personas implicadas: ");
            int numImplicados = entrada.nextInt();
            entrada.nextLine();

            ArrayList<PersonaImplicada> implicados = new ArrayList<>();
            for (int i = 0; i < numImplicados; i++) {
                System.out.println("\nPersona implicada numero " + (i + 1));

                System.out.print("Nombre: ");
                String nombreImp = entrada.nextLine();

                System.out.print("Edad: ");
                int edad = entrada.nextInt();
                entrada.nextLine(); // 

                System.out.print("Ocupación: ");
                String ocupacion = entrada.nextLine();

                System.out.print("Nivel de implicación"
                        + " (acusado/testigo/victima): ");
                String nivel = entrada.nextLine();

                System.out.print("¿Colabora con el caso? (true/false): ");
                boolean colabora = entrada.nextBoolean();
                entrada.nextLine();

                double sentencia = 0;
                double dano = 0;

                if (nivel.equalsIgnoreCase("acusado ")) {
                    System.out.print("Años de sentencia: ");
                    sentencia = entrada.nextDouble();
                    entrada.nextLine();

                    System.out.print("Daño económico causado: ");
                    dano = entrada.nextDouble();
                    entrada.nextLine(); // 
                }

                PersonaImplicada personaImplicada
                        = new PersonaImplicada(nombreImp, edad, ocupacion, nivel, sentencia, dano, colabora);
                implicados.add(personaImplicada);
            }

            CasoCorrupcion caso = new CasoCorrupcion(nombre, diasTranscurridos, implicados);
            caso.actualizarEstado();
            casos.add(caso);

            System.out.println(caso);

            for (PersonaImplicada persona : implicados) {
                if (persona.puedeReducirPena()) {
                    System.out.println(persona.nombre + " puede acogerse a reducción de pena ");
                }
                if (persona.calcularFianza() > 0) {
                    System.out.println(persona.nombre + " puede pagar una fianza de: $ "
                            + persona.calcularFianza());
                }
            }

            System.out.print("\n¿Desea registrar otro caso? (S/N): ");
            opcion = entrada.nextLine().charAt(0);
        }
    }
}

class CasoCorrupcion {

    public String nombre;
    public int diasTranscurridos;
    public String estadoActual;
    public ArrayList<PersonaImplicada> personasImplicadas;

    public CasoCorrupcion(String nombre, int diasTranscurridos,
            ArrayList<PersonaImplicada> personasImplicadas) {
        this.nombre = nombre;
        this.diasTranscurridos = diasTranscurridos;
        this.estadoActual = "Iniciado";
        this.personasImplicadas = personasImplicadas;
    }

    public void actualizarEstado() {
        if (diasTranscurridos > 7 && diasTranscurridos < 14) {
            this.estadoActual = "Alerta";
        } else if (diasTranscurridos > 14) {
            this.estadoActual = "Urgente";
        }
    }

    @Override
    public String toString() {
        return "CasoCorrupcion{" + "nombre = " + nombre + ", diasTranscurridos = "
                + diasTranscurridos + ", estadoActual = " + estadoActual + ", "
                + "personasImplicadas = " + personasImplicadas + '}';
    }

}

class PersonaImplicada {

    public String nombre;
    public int edad;
    public String ocupacion;
    public String nivelImplicacion; // "acusado", "testigo", "victima"
    public double sentenciaAnos;
    public double danoEconomico;
    public boolean colabora;

    public PersonaImplicada(String nombre, int edad, String ocupacion, String nivelImplicacion,
            double sentenciaAnos, double danoEconomico, boolean colabora) {
        this.nombre = nombre;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.nivelImplicacion = nivelImplicacion;
        this.sentenciaAnos = sentenciaAnos;
        this.danoEconomico = danoEconomico;
        this.colabora = colabora;
    }

    public boolean puedeReducirPena() {
        return nivelImplicacion.equalsIgnoreCase("acusado") && colabora;
    }

    public double calcularFianza() {
        if (nivelImplicacion.equalsIgnoreCase("acusado") && sentenciaAnos < 1 && colabora) {
            return danoEconomico * 0.5;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PersonaImplicada{" + "nombre = " + nombre + ", edad = " + edad
                + ", ocupacion = " + ocupacion + ", nivelImplicacion = "
                + nivelImplicacion + ", sentenciaAnos = " + sentenciaAnos
                + ", danoEconomico = " + danoEconomico + ", colabora = " + colabora + "}";
    }
}
