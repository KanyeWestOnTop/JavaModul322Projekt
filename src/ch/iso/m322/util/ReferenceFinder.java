package ch.iso.m322.util;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;

public class ReferenceFinder {

	public static JFrame findFrame(Component item) {

		if (item instanceof JFrame) {
			return (JFrame) item;
		} else if (item instanceof JPopupMenu) {
			JPopupMenu pop = (JPopupMenu) item;
			return findFrame(pop.getInvoker());
		} else {
			Container parent = item.getParent();
			return parent == null ? null : findFrame(parent);
		}
	}

	public static JDialog findDialog(Component item) {

		if (item instanceof JDialog) {
			return (JDialog) item;
		} else if (item instanceof JPopupMenu) {
			JPopupMenu pop = (JPopupMenu) item;
			return findDialog(pop.getInvoker());
		} else {
			Container parent = item.getParent();
			return parent == null ? null : findDialog(parent);
		}
	}

}
