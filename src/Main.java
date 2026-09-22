/**
 * Clase de demostración. Muestra el uso combinado de los patrones
 * COMPOSITE y DECORATOR sobre la abstracción Producto.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("========================================================");
        System.out.println("1) COMPOSITE - Leaf: crear un ProductoSimple");
        System.out.println("========================================================");
        Producto teclado = new ProductoSimple("Teclado mecánico", 100.0, 10);
        System.out.println(teclado.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("2) COMPOSITE - Crear un Compuesto con varios productos");
        System.out.println("========================================================");
        Producto mouse = new ProductoSimple("Mouse óptico", 40.0, 20);
        Producto monitor = new ProductoSimple("Monitor 24\"", 300.0, 5);

        Compuesto comboEscritorio = new Compuesto("Combo Escritorio");
        comboEscritorio.agregar(teclado);
        comboEscritorio.agregar(mouse);
        comboEscritorio.agregar(monitor);
        System.out.println(comboEscritorio.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("3) COMPOSITE - Un Compuesto que contiene otro Compuesto");
        System.out.println("========================================================");
        Producto auriculares = new ProductoSimple("Auriculares", 60.0, 15);
        Producto webcam = new ProductoSimple("Webcam HD", 80.0, 8);

        Compuesto comboAccesorios = new Compuesto("Combo Accesorios");
        comboAccesorios.agregar(auriculares);
        comboAccesorios.agregar(webcam);

        Compuesto pcCompleta = new Compuesto("PC Completa");
        pcCompleta.agregar(comboEscritorio);
        pcCompleta.agregar(comboAccesorios);
        System.out.println(pcCompleta.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("4) DECORATOR - Aplicar un Descuento a un producto");
        System.out.println("========================================================");
        Producto tecladoConDescuento = new Descuento(teclado, 10);
        System.out.println(tecladoConDescuento.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("5) DECORATOR - Aplicar PackBasico");
        System.out.println("========================================================");
        Producto mouseConPackBasico = new PackBasico(mouse);
        System.out.println(mouseConPackBasico.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("6) DECORATOR - Aplicar PackPremium");
        System.out.println("========================================================");
        Producto monitorConPackPremium = new PackPremium(monitor);
        System.out.println(monitorConPackPremium.describir());
        System.out.println();

        System.out.println("========================================================");
        System.out.println("7) DECORATOR - Combinar varios decoradores encadenados");
        System.out.println("========================================================");
        Producto tecladoCompleto = new PackPremium(new Descuento(teclado, 15));
        System.out.println(tecladoCompleto.describir());
        System.out.println("Precio final: $" + String.format("%.2f", tecladoCompleto.getPrecio()));
        System.out.println();

        System.out.println("========================================================");
        System.out.println("8) COMPOSITE + DECORATOR - Decorar un ProductoSimple y un Compuesto");
        System.out.println("========================================================");
        Producto webcamDecorada = new PackBasico(new Descuento(webcam, 5));
        System.out.println("-- Decorando un ProductoSimple --");
        System.out.println(webcamDecorada.describir());
        System.out.println("Precio final: $" + String.format("%.2f", webcamDecorada.getPrecio()));
        System.out.println();

        Producto pcCompletaDecorada = new Descuento(new PackPremium(pcCompleta), 20);
        System.out.println("-- Decorando un Compuesto completo --");
        System.out.println(pcCompletaDecorada.describir());
        System.out.println("Precio final: $" + String.format("%.2f", pcCompletaDecorada.getPrecio()));
    }
}
