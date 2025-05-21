package problema1_sistemadeventas;

import java.util.ArrayList;
import java.util.Scanner;

public class Problema1_SistemaDeVentas {

    Scanner entrada = new Scanner(System.in);
    public ArrayList<Problema1_Producto> inventario = new ArrayList<>();
    public ArrayList<Problema1_Producto> carrito = new ArrayList<>();
    public double totalCompra = 0;
    public double descuento = 0.10; // Digamos que esto dijo el dueno
    public double faltante;

    public static void main(String[] args) {
        Problema1_SistemaDeVentas metodos = new Problema1_SistemaDeVentas();
        int opc;

        do {
            opc = metodos.menu();
            switch (opc) {
                case 1:
                    metodos.PresentarInventario();
                    break;

                case 2:
                    metodos.entrada.nextLine(); // Limpiar búfer
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = metodos.entrada.nextLine();

                    System.out.print("Ingrese la cantidad: ");
                    int cantidad = metodos.entrada.nextInt();
                    metodos.AgregarProducto(nombre, cantidad);
                    break;

                case 3:
                    metodos.MostrarDetalleCompra();
                    metodos.CalcularTotal();
                    break;

                case 4:
                    metodos.RealizarPago();
                    break;

            }

        } while (opc != 5);
    }

    public Problema1_SistemaDeVentas() {
        inventario.add(new Problema1_Producto("Samsung s25 ", 450.99, 4));
        inventario.add(new Problema1_Producto("Iphone 16 ", 500.0, 6));
        inventario.add(new Problema1_Producto("Msi Black Viper ", 300.0, 5));
        inventario.add(new Problema1_Producto("Mica de vidrio ", 30.0, 10));
        inventario.add(new Problema1_Producto("Bateria celular ", 65.0, 6));
        inventario.add(new Problema1_Producto("Estuche protector ", 40.89, 7));
        inventario.add(new Problema1_Producto("Cargador carga rapida iphone ", 65.0, 8));
        inventario.add(new Problema1_Producto("Cargador carga rapida samsung ", 55.0, 8));
    }

    public int menu() {
        System.out.println("\n--- Menu Interactivo DiegoTech ---");
        System.out.println("[1] Ver inventario");
        System.out.println("[2] Agregar producto al carrito");
        System.out.println("[3] Ver detalle y calcular total");
        System.out.println("[4] Realizar pago");
        System.out.println("[5] Salir");
        System.out.print("Seleccione una opción: ");
        return entrada.nextInt();
    }

    public void PresentarInventario() {
        System.out.println("\n Inventario Disponible ");
        for (int i = 0; i < inventario.size(); i++) {
            Problema1_Producto producto = inventario.get(i);
            System.out.println((i + 1) + " " + producto.nombre + producto.precio);
        }
    }

    public void AgregarProducto(String nombreP, int cantidad) {
        nombreP = nombreP.toLowerCase();
        boolean encontrado = false;

        for (int i = 0; i < inventario.size(); i++) {
            Problema1_Producto producto = inventario.get(i);

            if (producto.nombre.toLowerCase().equals(nombreP)) {
                encontrado = true;

                if (cantidad <= producto.cantidad) {
                    carrito.add(new Problema1_Producto(producto.nombre, producto.precio, cantidad));
                    System.out.println("Producto agregado al carrito ");
                } else {
                    System.out.println("Cantidad no disponible en el inventario ");
                }
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Producto no encontrado en el inventario. ");
        }
    }

    public void CalcularTotal() {
        totalCompra = 0;

        for (int i = 0; i < carrito.size(); i++) {
            Problema1_Producto producto = carrito.get(i);
            totalCompra = totalCompra + producto.precio * producto.cantidad;
        }

        if (totalCompra > 1000) {
            double montoDescuento = totalCompra * descuento;
            totalCompra = totalCompra - montoDescuento;
            System.out.println("Descuento aplicado: " + montoDescuento);
        }

        System.out.println("Total a pagar: " + totalCompra + " dolares");
    }

    public void RealizarPago() {
        System.out.print("Ingrese el monto pagado por el cliente: ");
        double pago = entrada.nextDouble();

        if (pago >= totalCompra) {
            System.out.println("Gracias por su compra");
        } else {
            faltante = totalCompra - pago;
            System.out.println("Pago insuficiente Faltan: " + faltante + " dolares");
        }
    }

    public void MostrarDetalleCompra() {
        System.out.println("\n Detalle de la compra ");
        for (int i = 0; i < carrito.size(); i++) {
            Problema1_Producto producto = carrito.get(i);
            System.out.println(producto.nombre + ": " + producto.cantidad + " unidades");
        }
    }
}

class Problema1_Producto {

    public String nombre;
    public double precio;
    public int cantidad;

    public Problema1_Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}
