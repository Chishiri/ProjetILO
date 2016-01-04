package figures.creationListeners;

import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import figures.Figure;
import figures.Drawing;

/**
 * Listener permettant d'enchainer les actions souris nécessaires à la création
 * d'un polygone :
 * <ul>
 * <li>premier click avec le bouton gauche pour initier la création du polygone</li>
 * <li>les clicks suivants:
 * <ul>
 * <li>avec le bouton gauche ajoute un point au polygone</li>
 * <li>avec le bouton droit termine le polygone</li>
 * <li>avec le bouton du milieu retire le dernier point du polygone</li>
 * </ul>
 * </li>
 * <li>Une fois le polygone en cours de création, le déplacement de la souris
 * déplace le dernier point du polygone</li>
 * </ul>
 *
 * @author davidroussel
 */
public class PolygonRegulierCreationListener extends AbstractCreationListener
{

	/**
	 * Constructeur d'un Listener pour créer un polygon en plusieurs clicks
	 *
	 * @param model le modèle de dessin à modifier
	 * @param infoLabel le label dans lequel afficher les conseils utilisateurs
	 */
	public PolygonRegulierCreationListener(Drawing model, JLabel infoLabel)
	{
		super(model, infoLabel, 2);

		tips[0] = new String("Clic gauche pour centrer le polygone");
		tips[1] = new String("clic gauche/bouger souris pour définir le polygone");

		updateTip();

		System.out.println("PolygonRegulierCreationListener created");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * figures.creationListeners.AbstractCreationListener#mousePressed(java.
	 * awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			startFigure(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * figures.creationListeners.AbstractCreationListener#mouseReleased(java
	 * .awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (e.getButton() == MouseEvent.BUTTON1)
		{
			endFigure(e);
		}
	}

	/**
	 * Actions à réaliser lorsqu'un bouton de la souris est cliqué.
	 * Si l'on se trouve à l'étape 0 et que le bouton cliqué est
	 * {@link MouseEvent#BUTTON1}, on initie la figure et on passe à l'étape suivante.
	 * Dans l'étape suivante si c'est {@link MouseEvent#BUTTON1} qui est cliqué
	 * on ajoute un point, si c'est le {@link MouseEvent#BUTTON2} on supprime le
	 * dernier point ajouté et si c'est le bouton {@link MouseEvent#BUTTON3},
	 * on termine la figure.
	 * @param e l'évènement souris associé
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e)
	{
		//Rien
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		// Rien
	}

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e)
	{
		// Rien
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (currentStep == 1)
		{
			// AbstractFigure figure = drawingModel.getLastFigure();
			Figure figure = currentFigure;
			if (figure != null)
			{
				figure.setLastPoint(e.getPoint());
			}
			drawingModel.update();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Rien
	}
}
