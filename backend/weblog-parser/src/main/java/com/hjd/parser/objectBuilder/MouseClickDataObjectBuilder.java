package com.hjd.parser.objectBuilder;

import com.hjd.parser.dataobject.BaseDataObject;
import com.hjd.parser.dataobject.McDataObject;
import com.hjd.parser.utils.ColumnReader;
import com.hjd.parser.utils.ParserUtils;
import com.hjd.parser.utils.UrlInfo;
import com.hjd.prepaser.PreParsedLog;

import java.util.ArrayList;
import java.util.List;

// 静态导入方法
import static com.hjd.parser.utils.UrlParseUtils.getInfoFromUrl;

public class MouseClickDataObjectBuilder extends AbstractDataObjectBuilder{

    private static int clickXBoundary = 10000;
    private static int clickYBoundary = 100000;

    @Override
    public String getCommand() {
        return "mc";
    }

    @Override
    public List<BaseDataObject> doBuildDataObjects(PreParsedLog preParsedLog) {
        List<BaseDataObject> dataObjects = new ArrayList<>();

        // 1. 解析并填充公用字段
        McDataObject mcDataObject = new McDataObject();
        ColumnReader reader = new ColumnReader(preParsedLog.getQueryString());
        fillCommonBaseDataObjectValue(mcDataObject, preParsedLog, reader);

        // 2. 解析并填充mc特有的字段
        //点击页面的信息
        UrlInfo urlInfo = getInfoFromUrl(reader.getStringValue("gsmcurl"));
        mcDataObject.setUrl(urlInfo.getFullUrl());
        mcDataObject.setOriginalUrl(reader.getStringValue("gsorurl"));
        mcDataObject.setPageTitle(reader.getStringValue("gst1"));
        mcDataObject.setPageHostName(urlInfo.getDomain());
        mcDataObject.setPageRegion(getIntValue("lh", reader));
        mcDataObject.setPageVersion(reader.getStringValue("pageVersion"));
        mcDataObject.setSnapshotId(Integer.parseInt(reader.getStringValue("gssn")));

        //点击的x和y值
        int accurateClickX = Integer.parseInt(reader.getStringValue("gsmcoffsetx"));
        int accurateClickY = Integer.parseInt(reader.getStringValue("gsmcoffsety"));
        mcDataObject.setClickX(accurateClickX);
        mcDataObject.setClickY(accurateClickY);

        // 点击的链接信息
        mcDataObject.setLinkX(getValidClickXYPoint(getIntValue("lx", reader), clickXBoundary, -1 * clickXBoundary));
        mcDataObject.setLinkY(getValidClickXYPoint(getIntValue("ly", reader), clickYBoundary, 0));
        mcDataObject.setLinkHeight(getIntValue("lh", reader));
        mcDataObject.setLinkWidth(getIntValue("lw", reader));
        mcDataObject.setLinkText(reader.getStringValue("lt"));
        mcDataObject.setLinkUrl(reader.getStringValue("lk"));
        mcDataObject.setLinkHostName(getInfoFromUrl(mcDataObject.getLinkUrl()).getDomain());
        if(!ParserUtils.isNullOrEmptyOrDash(mcDataObject.getLinkUrl()) ||
                !ParserUtils.isNullOrEmptyOrDash(mcDataObject.getLinkText())){
            mcDataObject.setLinkClicked(true);
        }
        //分辨率
        mcDataObject.setClickScreenResolution(reader.getStringValue("gsscr"));

        dataObjects.add(mcDataObject);

        return dataObjects;
    }


    /**
     * 将string类型转换为Int类型
     * @param key
     * @param columnReader
     * @return
     */
    private Integer getIntValue(String key, ColumnReader columnReader){
        String value = columnReader.getStringValue(key);
        if (!ParserUtils.isNullOrEmptyOrDash(value)){
            return Integer.parseInt(value);
        }else{
            return 0;
        }
    }

    /**
     * 规范点击位置
     * @param point
     * @param maxBoundaryValue
     * @param minBoundaryValue
     * @return
     */
    private int getValidClickXYPoint(int point, int maxBoundaryValue, int minBoundaryValue){
        if(point < minBoundaryValue){
            return maxBoundaryValue;
        } else if(point > maxBoundaryValue){
            return maxBoundaryValue;
        } else {
            return point;
        }
    }
}

