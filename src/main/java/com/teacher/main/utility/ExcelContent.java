package com.teacher.main.utility;

import java.io.Serializable;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.teacher.main.enums.UploadHeader;

import lombok.Data;

@Data
public class ExcelContent implements Serializable {

    private static final long serialVersionUID = 357989738873681746L;
    private HashMap<String, Integer> headerMap;
    private Row row;

    public String getValue(UploadHeader header) {

        Cell cell = getCell(header);
        if (cell != null) {
            return new DataFormatter().formatCellValue(cell);
        }
        return null;
    }

    public Integer getIntValue(UploadHeader header) {

        if (getCell(header) != null) {
            return Integer.valueOf(getValue(header).replaceAll(",", ""));
        }
        return 0;
    }

    public Double getDoubleValue(UploadHeader header) {

        if (getCell(header) != null) {
            return Double.valueOf(getValue(header).replaceAll(",", ""));
        }
        return 0.0;
    }

    public Cell getCell(UploadHeader header) {
    	return getCell(header.toString());
    }
    
    public Cell getCell(String header) {

        if (headerMap.containsKey(header)) {
            Cell cell = row.getCell(headerMap.get(header));
            if (cell == null) {
                return null;
            } else if (cell.getCellType() == cell.CELL_TYPE_ERROR ||
                    cell.getCellType() == cell.CELL_TYPE_BLANK) {
                return null;
            }
            return cell;
        }
        return null;
    }
    
    public String getValue(String header) {

        Cell cell = getCell(header);
        if (cell != null) {
            return new DataFormatter().formatCellValue(cell);
        }
        return null;
    }
}