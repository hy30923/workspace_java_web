// 
// Decompiled by Procyon v0.5.36
// 

package com.jspsmart.upload;

import javax.servlet.ServletException;
import java.sql.SQLException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.io.FileOutputStream;
import java.io.IOException;

public class File
{
    private SmartUpload m_parent;
    private int m_startData;
    private int m_endData;
    private int m_size;
    private String m_fieldname;
    private String m_filename;
    private String m_fileExt;
    private String m_filePathName;
    private String m_contentType;
    private String m_contentDisp;
    private String m_typeMime;
    private String m_subTypeMime;
    private String m_contentString;
    private boolean m_isMissing;
    public static final int SAVEAS_AUTO = 0;
    public static final int SAVEAS_VIRTUAL = 1;
    public static final int SAVEAS_PHYSICAL = 2;
    
    File() {
        this.m_startData = 0;
        this.m_endData = 0;
        this.m_size = 0;
        this.m_fieldname = new String();
        this.m_filename = new String();
        this.m_fileExt = new String();
        this.m_filePathName = new String();
        this.m_contentType = new String();
        this.m_contentDisp = new String();
        this.m_typeMime = new String();
        this.m_subTypeMime = new String();
        this.m_contentString = new String();
        this.m_isMissing = true;
    }
    
    public void saveAs(final String s) throws IOException, SmartUploadException {
        this.saveAs(s, 0);
    }
    
    public void saveAs(final String s, final int i) throws IOException, SmartUploadException {
        final String s2 = new String();
        final String physicalPath = this.m_parent.getPhysicalPath(s, i);
        if (physicalPath == null) {
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        }
        try {
            final FileOutputStream fileOutputStream = new FileOutputStream(new java.io.File(physicalPath));
            fileOutputStream.write(this.m_parent.m_binArray, this.m_startData, this.m_size);
            fileOutputStream.close();
        }
        catch (IOException ex) {
            throw new SmartUploadException("File can't be saved (1120).");
        }
    }
    
    public void fileToField(final ResultSet set, final String s) throws ServletException, IOException, SmartUploadException, SQLException {
        final int length = 65536;
        int startData = this.m_startData;
        if (set == null) {
            throw new IllegalArgumentException("The RecordSet cannot be null (1145).");
        }
        if (s == null) {
            throw new IllegalArgumentException("The columnName cannot be null (1150).");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("The columnName cannot be empty (1155).");
        }
        final long longValue = BigInteger.valueOf(this.m_size).divide(BigInteger.valueOf(length)).longValue();
        final int intValue = BigInteger.valueOf(this.m_size).mod(BigInteger.valueOf(length)).intValue();
        try {
            for (int n = 1; n < longValue; ++n) {
                set.updateBinaryStream(s, new ByteArrayInputStream(this.m_parent.m_binArray, startData, length), length);
                final int n2 = (startData == 0) ? 1 : startData;
                startData = n * length + this.m_startData;
            }
            if (intValue > 0) {
                set.updateBinaryStream(s, new ByteArrayInputStream(this.m_parent.m_binArray, startData, intValue), intValue);
            }
        }
        catch (SQLException ex) {
            final byte[] array = new byte[this.m_size];
            System.arraycopy(this.m_parent.m_binArray, this.m_startData, array, 0, this.m_size);
            set.updateBytes(s, array);
        }
        catch (Exception ex2) {
            throw new SmartUploadException("Unable to save file in the DataBase (1130).");
        }
    }
    
    public boolean isMissing() {
        return this.m_isMissing;
    }
    
    public String getFieldName() {
        return this.m_fieldname;
    }
    
    public String getFileName() {
        return this.m_filename;
    }
    
    public String getFilePathName() {
        return this.m_filePathName;
    }
    
    public String getFileExt() {
        return this.m_fileExt;
    }
    
    public String getContentType() {
        return this.m_contentType;
    }
    
    public String getContentDisp() {
        return this.m_contentDisp;
    }
    
    public String getContentString() {
        return new String(this.m_parent.m_binArray, this.m_startData, this.m_size);
    }
    
    public String getTypeMIME() throws IOException {
        return this.m_typeMime;
    }
    
    public String getSubTypeMIME() {
        return this.m_subTypeMime;
    }
    
    public int getSize() {
        return this.m_size;
    }
    
    protected int getStartData() {
        return this.m_startData;
    }
    
    protected int getEndData() {
        return this.m_endData;
    }
    
    protected void setParent(final SmartUpload parent) {
        this.m_parent = parent;
    }
    
    protected void setStartData(final int startData) {
        this.m_startData = startData;
    }
    
    protected void setEndData(final int endData) {
        this.m_endData = endData;
    }
    
    protected void setSize(final int size) {
        this.m_size = size;
    }
    
    protected void setIsMissing(final boolean isMissing) {
        this.m_isMissing = isMissing;
    }
    
    protected void setFieldName(final String fieldname) {
        this.m_fieldname = fieldname;
    }
    
    protected void setFileName(final String filename) {
        this.m_filename = filename;
    }
    
    protected void setFilePathName(final String filePathName) {
        this.m_filePathName = filePathName;
    }
    
    protected void setFileExt(final String fileExt) {
        this.m_fileExt = fileExt;
    }
    
    protected void setContentType(final String contentType) {
        this.m_contentType = contentType;
    }
    
    protected void setContentDisp(final String contentDisp) {
        this.m_contentDisp = contentDisp;
    }
    
    protected void setTypeMIME(final String typeMime) {
        this.m_typeMime = typeMime;
    }
    
    protected void setSubTypeMIME(final String subTypeMime) {
        this.m_subTypeMime = subTypeMime;
    }
    
    public byte getBinaryData(final int n) {
        if (this.m_startData + n > this.m_endData) {
            throw new ArrayIndexOutOfBoundsException("Index Out of range (1115).");
        }
        if (this.m_startData + n <= this.m_endData) {
            return this.m_parent.m_binArray[this.m_startData + n];
        }
        return 0;
    }
}
