package org.avaje.metric.eclipse.enhancer.ui.preferences;

import org.avaje.metric.eclipse.enhancer.EnhancerPlugin;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing <samp>
 * FieldEditorPreferencePage</samp>, we can use the field support built into JFace that allows us to create a page that is small and knows
 * how to save, restore and apply itself.
 *
 * <p>This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class. That
 * way, preferences can be accessed directly via the preference store.</p>
 */
public class EnhancePreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{

    private String toggleMenuDescription = "Enhancement is enabled/disabled per project:\n"
        + " - Select the project\n"
        + " - Right mouse button menu\n"
        + " - Configure - Enable/Disable Avaje Metrics Enhancer";

    public EnhancePreferencePage()
    {
        super(GRID);

        EnhancerPlugin activator = EnhancerPlugin.getDefault();
        if (activator == null)
        {
            EnhancerPlugin.logError("Plugin not activated when creating Preference Page?", null);
        }
        else
        {
            setPreferenceStore(activator.getPreferenceStore());
        }
    }

    /**
     * Creates the field editors. Field editors are abstractions of the common GUI blocks needed to manipulate various types of preferences.
     * Each field editor knows how to save and restore itself.
     */
    @Override public void createFieldEditors()
    {
        addField(new LabelFieldEditor(toggleMenuDescription, getFieldEditorParent()));
        addField(new SpacerFieldEditor(getFieldEditorParent()));

        addField(new ComboFieldEditor(
                PreferenceConstants.P_ENHANCE_DEBUG_LEVEL,
                "Enhancement Logging:       ",
                new String[][]
                {
                    { "0 - No Logging", "0" },
                    { "1 - Minimum Logging", "1" },
                    { "2", "2" },
                    { "3", "3" },
                    { "4", "4" },
                    { "5", "5" },
                    { "6", "6" },
                    { "7", "7" },
                    { "8", "8" },
                    { "9", "9" },
                    { "10 - Maximum Logging", "10" }
                }, getFieldEditorParent()));

        addField(new LabelPairFieldEditor("Purpose:", "Logging the enhancement process", getFieldEditorParent()));
        addField(new LabelPairFieldEditor("Location:", "${workspace}/.metadata/.plugins/\norg.avaje.metric.enhancer.plugin.log/enhance.log",
                getFieldEditorParent()));

        addField(new SpacerFieldEditor(getFieldEditorParent()));

        addField(new ComboFieldEditor(
                PreferenceConstants.P_PLUGIN_DEBUG_LEVEL,
                "Plugin Logging:",
                new String[][]
                {
                    { "No Logging", "0" },
                    { "Minimum Logging", "1" },
                    { "Full Logging", "2" }
                }, getFieldEditorParent()));

        addField(new LabelPairFieldEditor("Purpose:", "Logging this plugin", getFieldEditorParent()));
        addField(new LabelPairFieldEditor("Location:", "${workspace}/.metadata/.log", getFieldEditorParent()));

        addField(new SpacerFieldEditor(getFieldEditorParent()));
        addField(new LabelFieldEditor("Note: You can view this log via (Window - Show View - Error Log).",
                getFieldEditorParent()));

    }

    @Override public void init(IWorkbench workbench)
    {
        // no-op
    }

    private class SpacerFieldEditor extends LabelFieldEditor
    {
        // Implemented as an empty label field editor.
        public SpacerFieldEditor(Composite parent)
        {
            super("", parent);
        }
    }
}
