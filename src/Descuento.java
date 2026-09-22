/**
 * DECORATOR - Concrete Decorator
 *
 * Agrega el comportamiento de aplicar un descuento porcentual sobre el
 * precio del producto que envuelve. No modifica ProductoSimple ni
 * Compuesto: solo delega y ajusta el resultado del cálculo del precio.
 */
public class Descuento extends Decorador {

    private final double porcentaje;

    public Descuento(Producto producto, double porcentaje) {
        super(producto);
        this.porcentaje = porcentaje;
    }

    @Override
    public double getPrecio() {
        double precioOriginal = producto.getPrecio();
        return precioOriginal - (precioOriginal * porcentaje / 100);
    }

    @Override
    public String getNombre() {
        return producto.getNombre() + " [con " + porcentaje + "% de descuento]";
    }

    @Override
    public String describir() {
        return producto.describir()
                + String.format("%n     + Descuento aplicado: %.1f%% -> precio final: $%.2f", porcentaje, getPrecio());
    }
}
