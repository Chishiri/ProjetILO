package figures.enums;

import java.awt.BasicStroke;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

import figures.Circle;
import figures.Drawing;
import figures.Ellipse;
import figures.Figure;
import figures.Polygon;
import figures.PolygonRegulier;
import figures.Rectangle;
import figures.RoundedRectangle;
import figures.creationListeners.AbstractCreationListener;
import figures.creationListeners.PolygonCreationListener;
import figures.creationListeners.PolygonRegulierCreationListener;
import figures.creationListeners.RectangularShapeCreationListener;
import figures.creationListeners.RoundedRectangleCreationListener;

/**
 * Enumeration des différentes figures possibles
 * @author davidroussel
 */
public enum FigureType
{
	/**
	 * Les différents types de figures
	 */
	CIRCLE, 
	ELLIPSE, 
	RECTANGLE, 
	ROUNDED_RECTANGLE, 
	POLYGON, 
	POLYGONREGULIER,
	NONE;

	/**
	 * Nombre de figures référencées ici (à changer si on ajoute des types de 
	 * figures)
	 */
	public final static int NbFigureTypes = 6;
	
	/**
	 * Obtention d'une instance de figure correspondant au type
	 *
	 * @param stroke la césure du trait (ou pas de trait si null)
	 * @param edge la couleur du trait (ou pas de trait si null)
	 * @param fill la couleur de remplissage (ou pas de remplissage si null)
	 * @param x l'abcisse du premier point de la figure
	 * @param y l'ordonnée du premier point de la figure
	 * @return une nouvelle instance correspondant à la valeur de cet enum
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	public Figure getFigure(BasicStroke stroke,
			Paint edge,
			Paint fill,
			Point2D p) throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
				return new Circle(stroke, edge, fill, p, 0.0f);
			case ELLIPSE:
				return new Ellipse(stroke, edge, fill, p, p);
			case RECTANGLE:
				return new Rectangle(stroke, edge, fill, p, p);
			case ROUNDED_RECTANGLE:
				return new RoundedRectangle(stroke, edge, fill, p, p, 0);
			case POLYGON:
				Point pp = new Point(Double.valueOf(p.getX()).intValue(),
						Double.valueOf(p.getY()).intValue());
				return new Polygon(stroke, edge, fill, pp, pp);
			case POLYGONREGULIER:
				Point pr = new Point(Double.valueOf(p.getX()).intValue(),
						Double.valueOf(p.getY()).intValue());
				return new PolygonRegulier(stroke, edge, fill, p, pr, 5);
			case NONE:
				return null;
		}

		throw new AssertionError("FigureType unknown assertion: " + this);
	}

	/**
	 * Obtention d'un CreationListener adequat pour la valeur de cet enum
	 *
	 * @param model le modèle de dessin à modifier
	 * @param tipLabel le label dans lequel afficher les conseils utilisateur
	 * @return une nouvelle instance de CreationListener adéquate pour le type
	 *         de figure de cet enum.
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	public AbstractCreationListener getCreationListener(Drawing model,
			JLabel tipLabel) throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
			case ELLIPSE:
			case RECTANGLE:
				return new RectangularShapeCreationListener(model, tipLabel);
			case ROUNDED_RECTANGLE:
				return new RoundedRectangleCreationListener(model, tipLabel);
			case POLYGON:
				return new PolygonCreationListener(model, tipLabel);
			case POLYGONREGULIER:
				return new PolygonRegulierCreationListener(model, tipLabel);
			case NONE:
				return null;
		}

		throw new AssertionError("FigureType unknown assertion: " + this);
	}

	/**
	 * Représentation sous forme de chaine de caractères
	 *
	 * @return une chaine de caractère représentant la valeur de cet enum
	 * @throws AssertionError si la valeur de cet enum n'est pas prévue
	 */
	@Override
	public String toString() throws AssertionError
	{
		switch (this)
		{
			case CIRCLE:
				return new String("Circle");
			case ELLIPSE:
				return new String("Ellipse");
			case RECTANGLE:
				return new String("Rectangle");
			case ROUNDED_RECTANGLE:
				return new String("Rounded Rectangle");
			case POLYGON:
				return new String("Polygon");
			case POLYGONREGULIER:
				return new String("Polygon Régulier");
			case NONE:
				return new String("None");
		}

		throw new AssertionError("FigureType unknown assertion: " + this);
	}

	/**
	 * Otention d'un tableau de chaine de caractères contenant l'ensemble des
	 * nom des figures
	 *
	 * @return un tableau de chaine de caractères contenant l'ensemble des nom
	 *         des figures
	 */
	public static String[] stringValues()
	{
		FigureType[] values = FigureType.values();
		String[] stringValues = new String[values.length - 1]; // Except NONE

		for (int i = 0; i < stringValues.length; i++)
		{
			stringValues[i] = values[i].toString();
		}

		return stringValues;
	}
	
	/**
	 * Conversion d'un entier en FigureType
	 *
	 * @param i l'entier à convertir en FigureType
	 * @return le FigureType correspondant à l'entier
	 */
	public static FigureType fromInteger(int i)
	{
		switch (i)
		{
			case 0:
				return CIRCLE;
			case 1:
				return ELLIPSE;
			case 2:
				return RECTANGLE;
			case 3:
				return ROUNDED_RECTANGLE;
			case 4:
				return POLYGON;
			case 5:
				return POLYGONREGULIER;
			default:
				return POLYGON;
		}
	}
}
