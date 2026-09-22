/**
 * DECORATOR - Concrete Decorator
 *
 * Tipo concreto de Packaging. Agrega un costo fijo y económico de
 * empaquetado a cualquier Producto que envuelva.
 */
public class PackBasico extends Packaging {

    private static final double COSTO_PACKAGING = 5.0;

    public PackBasico(Producto producto) {
        super(producto);
    }

    @Override
    protected double getCostoPackaging() {
        return COSTO_PACKAGING;
    }

    @Override
    protected String getNombrePackaging() {
        return "Pack Básico";
    }
}
