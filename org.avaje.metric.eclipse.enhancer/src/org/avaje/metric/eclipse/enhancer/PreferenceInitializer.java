package org.avaje.metric.eclipse.enhancer;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import org.avaje.metric.eclipse.enhancer.ui.preferences.PreferenceConstants;

/**
 * Class used to initialise default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{

    @Override public void initializeDefaultPreferences()
    {
        IPreferenceStore store = EnhancerPlugin.getDefault().getPreferenceStore();
        store.setDefault(PreferenceConstants.P_PLUGIN_DEBUG_LEVEL, "1");
        store.setDefault(PreferenceConstants.P_ENHANCE_DEBUG_LEVEL, "1");
    }

}
