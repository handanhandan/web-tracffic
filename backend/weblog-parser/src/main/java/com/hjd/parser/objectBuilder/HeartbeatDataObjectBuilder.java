package com.hjd.parser.objectBuilder;

import com.hjd.parser.dataobject.BaseDataObject;
import com.hjd.parser.dataobject.HeartbeatDataObject;
import com.hjd.parser.utils.ColumnReader;
import com.hjd.prepaser.PreParsedLog;

import java.util.List;

public class HeartbeatDataObjectBuilder extends AbstractDataObjectBuilder{


    @Override
    public String getCommand() {
        return "hb";
    }

    @Override
    public List<BaseDataObject> doBuildDataObjects(PreParsedLog preParsedLog) {
        // 解析公共字段
        HeartbeatDataObject heartbeatDataObject = new HeartbeatDataObject();
        ColumnReader columnReader = new ColumnReader(preParsedLog.getQueryString());
        fillCommonBaseDataObjectValue(heartbeatDataObject, preParsedLog, columnReader);
        return null;
    }


}
