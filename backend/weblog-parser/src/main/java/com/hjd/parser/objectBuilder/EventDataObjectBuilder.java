package com.hjd.parser.objectBuilder;

import com.hjd.parser.dataobject.BaseDataObject;
import com.hjd.prepaser.PreParsedLog;

import java.util.List;

public class EventDataObjectBuilder extends AbstractDataObjectBuilder{

    @Override
    public String getCommand() {
        return "ev";
    }

    @Override
    public List<BaseDataObject> doBuildDataObjects(PreParsedLog preParsedLog) {
        return null;
    }


}
