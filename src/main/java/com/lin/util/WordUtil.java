package com.lin.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * 閫傜敤浜巜ord 2007
 * poi 鐗堟湰 3.7
 */
public class WordUtil {

    /**
     * 鏍规嵁鎸囧畾鐨勫弬鏁板�笺�佹ā鏉匡紝鐢熸垚 word 鏂囨。
     * @param param 闇�瑕佹浛鎹㈢殑鍙橀噺
     * @param template 妯℃澘
     */
    public static CustomXWPFDocument generateWord(Map<String, Object> param, String template) {
        CustomXWPFDocument doc = null;
        XWPFTable table = null;
        List<XWPFTableRow> rows = null;
        List<XWPFTableCell> cells = null;
        List<XWPFParagraph> paragraphListTable =  null;
        try {
            OPCPackage pack = POIXMLDocument.openPackage(template);
            doc = new CustomXWPFDocument(pack);
            //System.out.println("param.size:"+param.size());
            if (param != null && param.size() > 0) {
                
                //澶勭悊娈佃惤
                List<XWPFParagraph> paragraphList = doc.getParagraphs();
                processParagraphs(paragraphList, param, doc);
                
                //澶勭悊琛ㄦ牸
                Iterator<XWPFTable> it = doc.getTablesIterator();
                while (it.hasNext()) {
                    table = it.next();
                    rows = table.getRows();
                    for (XWPFTableRow row : rows) {
                        cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            paragraphListTable =  cell.getParagraphs();
                            processParagraphs(paragraphListTable, param, doc);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    /**
     * 澶勭悊娈佃惤
     * @param paragraphList
     */
    @SuppressWarnings("unchecked")
    public static void processParagraphs(List<XWPFParagraph> paragraphList,Map<String, Object> param,CustomXWPFDocument doc){
        List<XWPFRun> runs = null;
        String text = null;
        boolean isSetText = false;
        String key = null;
        Object value = null;
        Map<String, Object> pic = null;
        int width =  0;
        int height = 0;
        int picType = 0;
        byte[] byteArray = null;
        ByteArrayInputStream byteInputStream = null;
        int ind = 0;
        if(paragraphList != null && paragraphList.size() > 0){
            for(XWPFParagraph paragraph:paragraphList){
                runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    isSetText = false;
                    text = run.getText(0).trim();
                    
                   // if ( text.length() > 0){
                        
                   //     System.out.println("processParagraphs--text11:"+text);                            	
                    //}
                    
                    if(text != null){
                        for (Entry<String, Object> entry : param.entrySet()) {
                            key = entry.getKey();


                            if(text.indexOf(key) != -1){
                                isSetText = true;
                                value = entry.getValue();
                                
                                if (value instanceof String) {//鏂囨湰鏇挎崲
                                    text = text.replace(key, value.toString());
                                } else if (value instanceof Map) {//鍥剧墖鏇挎崲
                                    text = text.replace(key, "");
                                    pic = (Map<String, Object>)value;
                                    width = Integer.parseInt(pic.get("width").toString());
                                    height = Integer.parseInt(pic.get("height").toString());
                                    picType = getPictureType(pic.get("type").toString());
                                    byteArray = (byte[]) pic.get("content");
                                    byteInputStream = new ByteArrayInputStream(byteArray);
                                    try {
                                        //ind = doc.addPicture(byteInputStream,picType);
                                        doc.createPicture(ind, width , height,paragraph);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                	text = "";
                                }
                            }
                        }
                        if(isSetText){
                            run.setText(text,0);
                        }
                    }
                }
            }
        }
    }
    /**
     * 鏍规嵁鍥剧墖绫诲瀷锛屽彇寰楀搴旂殑鍥剧墖绫诲瀷浠ｇ爜
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType){
        int res = CustomXWPFDocument.PICTURE_TYPE_PICT;
        if(picType != null){
            if(picType.equalsIgnoreCase("png")){
                res = CustomXWPFDocument.PICTURE_TYPE_PNG;
            }else if(picType.equalsIgnoreCase("dib")){
                res = CustomXWPFDocument.PICTURE_TYPE_DIB;
            }else if(picType.equalsIgnoreCase("emf")){
                res = CustomXWPFDocument.PICTURE_TYPE_EMF;
            }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){
                res = CustomXWPFDocument.PICTURE_TYPE_JPEG;
            }else if(picType.equalsIgnoreCase("wmf")){
                res = CustomXWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }
    /**
     * 灏嗚緭鍏ユ祦涓殑鏁版嵁鍐欏叆瀛楄妭鏁扮粍
     * @param in
     * @return
     */
    public static byte[] inputStream2ByteArray(InputStream in,boolean isClose){
        byte[] byteArray = null;
        try {
            int total = in.available();
            byteArray = new byte[total];
            in.read(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(isClose){
                try {
                    in.close();
                } catch (Exception e2) {
                    System.out.println("鍏抽棴娴佸け璐�");
                }
            }
        }
        return byteArray;
    }
}

