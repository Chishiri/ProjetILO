package figures;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

import figures.enums.FigureType;

/**
 * Classe de Rectangle pour les {@link Figure}
 *
 * @author davidroussel
 */
public class PolygonRegulier extends Figure
{
	/**
	 * Le compteur d'instance des cercles.
	 * Utilisé pour donner un numéro d'instance après l'avoir incrémenté
	 */
	private static int counter = 0;

	private int n;
	private Point2D centre;
	/**
	 * Création d'un rectangle avec les points en haut à gauche et en bas à
	 * droite
	 *
	 * @param stroke le type de trait
	 * @param edge la couleur du trait
	 * @param fill la couleur de remplissage
	 * @param topLeft le point en haut à gauche
	 * @param bottomRight le point en bas à droite
	 */
	public PolygonRegulier(BasicStroke stroke, Paint edge, Paint fill, Point2D c,
			Point p, int nb)
	{
		super(stroke, edge, fill);
		instanceNumber = ++counter;
		n = nb;
		centre = c;
		
		int i;
		double x, y;
		double rayon = c.distance(p.getX(), p.getY());
		java.awt.Polygon poly = new java.awt.Polygon();
		
		for(i=0; i<n; i++) {
			x = Math.cos(2*Math.PI*i/n)*rayon + centre.getX();
			y = Math.sin(2*Math.PI*i/n)*rayon + centre.getY();
			poly.addPoint((int)x, (int)y);
		}
		shape = poly;
	}

	/**
	 * Déplacement du point en bas à droite du rectangle à la position
	 * du point p
	 *
	 * @param p la nouvelle position du dernier point
	 * @see figures.Figure#setLastPoint(Point2D)
	 */
	@Override
	public void setLastPoint(Point2D p)
	{
		if (shape != null)
		{
			int i;
			double x, y, rayon = centre.distance(p);
			java.awt.Polygon poly = new java.awt.Polygon();
			
			for(i=0; i<n; i++) {
				x = Math.cos(2*Math.PI*i/n)*rayon + centre.getX();
				y = Math.sin(2*Math.PI*i/n)*rayon + centre.getY();
				poly.addPoint((int)x, (int)y);
			}
			shape = poly;
		}
	}

	/**
	 * Obtention du barycentre de la figure.
	 * @return le point correspondant au barycentre de la figure
	 */
	@Override
	public Point2D getCenter()
	{
		return new Point2D.Double(centre.getX(), centre.getY());
	}
	
 	/**
 	 * Accesseur du type de figure selon {@link FigureType}
 	 * @return le type de figure
 	 */
 	@Override
	public FigureType getType()
 	{
 		return FigureType.POLYGONREGULIER;
 	}
 	
 	public void setLocation(Point2D p){
 		java.awt.Polygon poly = (java.awt.Polygon) shape;
 		Rectangle2D bounds = poly.getBounds2D();
 		double w = bounds.getWidth();
 		double h = bounds.getHeight();
 		poly.translate((int) (p.getX()-bounds.getX()-w/2), (int) (p.getY()-bounds.getY()-h/2));
 	}
}
