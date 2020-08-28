package com.hjd.parser.objectBuilder;

import com.hjd.parser.dataobject.BaseDataObject;
import com.hjd.prepaser.PreParsedLog;

import java.util.List;

public class PvDataObjectBuilder extends AbstractDataObjectBuilder{
    @Override
    public String getCommand() {
        return "pv";
    }

    @Override
    public List<BaseDataObject> doBuildDataObjects(PreParsedLog preParsedLog) {
        return null;
    }


}
