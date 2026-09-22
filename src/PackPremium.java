/**
 * DECORATOR - Concrete Decorator
 *
 * Tipo concreto de Packaging. Agrega un costo mayor asociado a un
 * empaquetado premium (más elaborado) a cualquier Producto que envuelva.
 */
public class PackPremium extends Packaging {

    private static final double COSTO_PACKAGING = 15.0;

    public PackPremium(Producto producto) {
        super(producto);
    }

    @Override
    protected double getCostoPackaging() {
        return COSTO_PACKAGING;
    }

    @Override
    protected String getNombrePackaging() {
        return "Pack Premium";
    }
}
