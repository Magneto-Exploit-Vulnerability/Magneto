package net.sourceforge.ondex.ovtk2.ui.console;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import net.sourceforge.ondex.core.ONDEXConcept;
import net.sourceforge.ondex.core.ONDEXRelation;
import net.sourceforge.ondex.core.RelationType;
import net.sourceforge.ondex.ovtk2.config.OVTK2PluginLoader;
import net.sourceforge.ondex.ovtk2.graph.ONDEXJUNGGraph;
import net.sourceforge.ondex.ovtk2.layout.GEMLayout;
import net.sourceforge.ondex.ovtk2.layout.OVTK2Layouter;
import net.sourceforge.ondex.ovtk2.ui.OVTK2Desktop;
import net.sourceforge.ondex.ovtk2.ui.OVTK2ResourceAssesor;
import net.sourceforge.ondex.ovtk2.ui.OVTK2Viewer;
import net.sourceforge.ondex.ovtk2.ui.mouse.OVTK2GraphMouse;
import net.sourceforge.ondex.ovtk2.util.ErrorDialog;
import net.sourceforge.ondex.ovtk2.util.OVTKProgressMonitor;
import net.sourceforge.ondex.ovtk2.util.VisualisationUtils;
import net.sourceforge.ondex.tools.functions.StandardFunctions;
import net.sourceforge.ondex.tools.ondex.MdHelper;
import net.sourceforge.ondex.tools.threading.monitoring.IndeterminateProcessAdapter;
import net.sourceforge.ondex.tools.threading.monitoring.Monitorable;

/**
 * 
 * @author lysenkoa
 */
public class SetSelector extends javax.swing.JInternalFrame implements ActionListener {
	private ONDEXJUNGGraph graph;
	Map<JCheckBox, ONDEXConcept> supersets = new HashMap<JCheckBox, ONDEXConcept>();

	// setSelector();

	/**
	 * Creates new form NewJFrame1
	 */
	public SetSelector() throws Exception {
		if (OVTK2Desktop.getDesktopResources().getSelectedViewer() == null) {
			return;
		}
		OVTK2Desktop.getInstance().getDesktopResources().getParentPane().add(this);
		graph = OVTK2Desktop.getDesktopResources().getSelectedViewer().getONDEXJUNGGraph();
		initComponents();
		this.setVisible(true);
		this.setTitle("Show tagget sets for members");

		DefaultListModel listModel = new DefaultListModel();
		lSets.setModel(listModel);
		for (ONDEXConcept c : graph.getConceptsOfConceptClass(MdHelper.createCC(graph, "Superset"))) {
			JCheckBox check = new JCheckBox(c.getConceptName().getName());
			supersets.put(check, c);
			listModel.addElement(check);
		}

		System.err.println("Set selectior initialized.");
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		lSets = new CheckBoxList();
		bApply = new javax.swing.JButton();
		bClose = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setViewportView(lSets);

		bApply.setText("Apply");
		bApply.addActionListener(this);

		bClose.setText("Close");
		bClose.addActionListener(this);

		jLabel1.setText("Select supersets:");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addComponent(bApply).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(bClose)).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(0, 0, Short.MAX_VALUE))).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(4, 4, 4).addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(bApply).addComponent(bClose)).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}

	private class CheckBoxList extends JList {

		public CheckBoxList() {
			setCellRenderer(new CellRenderer());

			addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					int index = locationToIndex(e.getPoint());

					if (index != -1) {
						JCheckBox checkbox = (JCheckBox) getModel().getElementAt(index);
						checkbox.setSelected(!checkbox.isSelected());
						repaint();
					}
				}
			});

			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}

		protected class CellRenderer implements ListCellRenderer {
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				JCheckBox checkbox = (JCheckBox) value;
				checkbox.setBackground(isSelected ? getSelectionBackground() : getBackground());
				checkbox.setForeground(isSelected ? getSelectionForeground() : getForeground());
				checkbox.setEnabled(isEnabled());
				checkbox.setFont(getFont());
				checkbox.setFocusPainted(false);
				checkbox.setBorderPainted(false);
				return checkbox;
			}
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton bApply;
	private javax.swing.JButton bClose;
	private javax.swing.JScrollPane jScrollPane1;
	private CheckBoxList lSets;
	private javax.swing.JLabel jLabel1;

	// End of variables declaration

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Apply")) {
			RelationType[] t = new RelationType[] { MdHelper.createRT(graph, "is_subset_of") };
			Set<ONDEXConcept> propagatedTags = new HashSet<ONDEXConcept>();
			for (Entry<JCheckBox, ONDEXConcept> ent : supersets.entrySet()) {
				if (ent.getKey().isSelected()) {
					propagatedTags.addAll(StandardFunctions.getOtherNodes(graph, ent.getValue(), t));
				}
			}
			System.err.println("propagatedTags: " + propagatedTags.size());
			Set<ONDEXConcept> tags = new HashSet<ONDEXConcept>();
			for (ONDEXConcept selected : OVTK2Desktop.getDesktopResources().getSelectedViewer().getPickedNodes()) {
				tags.addAll(selected.getTags());
			}
			System.err.println("Tags: " + tags.size());
			tags.retainAll(propagatedTags);
			graph.setVisibility(graph.getRelations(), false);
			graph.setVisibility(graph.getConcepts(), false);

			for (ONDEXConcept tag : tags) {
				for (ONDEXConcept c : graph.getConceptsOfTag(tag)) {
					graph.setVisibility(c, true);
				}
				for (ONDEXRelation r : graph.getRelationsOfTag(tag)) {
					graph.setVisibility(r, true);
				}
			}
			OVTK2Desktop.getDesktopResources().getSelectedViewer().getVisualizationViewer().getModel().fireStateChanged();
			// GEMLayout gem = new
			// GEMLayout(OVTK2Desktop.getDesktopResources().getSelectedViewer());
			// OVTK2Desktop.getDesktopResources().getSelectedViewer().getVisualizationViewer().getModel().fireStateChanged();
			runLayout();
		}

		else if (e.getActionCommand().equals("Close")) {
			this.dispose();
		}
	}

	private void runLayout() {
		try {
			OVTK2ResourceAssesor resources = OVTK2Desktop.getDesktopResources();
			final OVTK2Viewer viewer = (OVTK2Viewer) resources.getSelectedViewer();
			final OVTK2Layouter layouter_new = new GEMLayout(viewer);

			if (layouter_new instanceof Monitorable) {
				// layout knows about its progress
				Monitorable p = (Monitorable) layouter_new;
				OVTKProgressMonitor.start(OVTK2Desktop.getInstance().getMainFrame(), "Running Layout...", p);
				Thread t = new Thread() {
					public void run() {
						VisualisationUtils.runLayout(layouter_new, viewer);
					}
				};
				// for coping with plug-in Attribute data types
				try {
					t.setContextClassLoader(OVTK2PluginLoader.getInstance().ucl);
				} catch (FileNotFoundException e) {
					ErrorDialog.show(e);
				}
				t.start();
			} else {
				// wrap into indefinite process
				IndeterminateProcessAdapter p = new IndeterminateProcessAdapter() {
					public void task() {
						// for coping with plug-in Attribute data types
						try {
							Thread.currentThread().setContextClassLoader(OVTK2PluginLoader.getInstance().ucl);
						} catch (FileNotFoundException e) {
							ErrorDialog.show(e);
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						VisualisationUtils.runLayout(layouter_new, viewer);
					}
				};

				// set layout
				OVTKProgressMonitor.start(OVTK2Desktop.getInstance().getMainFrame(), "Running Layout...", p);
				p.start();
			}

			// central handling of scaling
			OVTK2GraphMouse mouse = (OVTK2GraphMouse) viewer.getVisualizationViewer().getGraphMouse();
			mouse.setViewScaling(false);
		} catch (Exception e) {
			ErrorDialog.show(e);
		}
	}
}
