package org.germanbeyger.lab5.interfaces;

import org.germanbeyger.lab5.datatypes.TargetCollection;

public interface IMetainfo {
    void addToHistory(String command); 
    String getStringifiedHistory(); // Return history as single string object.
    String getInfoAboutCollection(TargetCollection targetCollection);
}
