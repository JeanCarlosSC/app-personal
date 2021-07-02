package lib.sRAD.gui.component;

import lib.sRAD.gui.sComponent.SFrame;

import static lib.sRAD.gui.component.Resource.grayBorder;

public class VentanaEmergente extends SFrame {
    private final SFrame frame;

    public VentanaEmergente(SFrame frame, int width, int height) {
        this.frame = frame;
        setLayout(null);
        setProperties(width, height, Theme.veBg, true, grayBorder,null);
    }

    public void lanzar() {
        frame.setEnabled(false);
        setVisible(true);
    }

    public void cerrar() {
        frame.setEnabled(true);
        this.setVisible(false);
    }
}
