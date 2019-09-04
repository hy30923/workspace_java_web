// 
// Decompiled by Procyon v0.5.36
// 

package com.jspsmart.upload;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.FileInputStream;
import javax.servlet.jsp.JspWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.util.Vector;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

public class SmartUpload
{
    protected byte[] m_binArray;
    protected HttpServletRequest m_request;
    protected HttpServletResponse m_response;
    protected ServletContext m_application;
    private int m_totalBytes;
    private int m_currentIndex;
    private int m_startData;
    private int m_endData;
    private String m_boundary;
    private long m_totalMaxFileSize;
    private long m_maxFileSize;
    private Vector m_deniedFilesList;
    private Vector m_allowedFilesList;
    private boolean m_denyPhysicalPath;
    private boolean m_forcePhysicalPath;
    private String m_contentDisposition;
    public static final int SAVE_AUTO = 0;
    public static final int SAVE_VIRTUAL = 1;
    public static final int SAVE_PHYSICAL = 2;
    private Files m_files;
    private Request m_formRequest;
    
    public SmartUpload() {
        this.m_totalBytes = 0;
        this.m_currentIndex = 0;
        this.m_startData = 0;
        this.m_endData = 0;
        this.m_boundary = new String();
        this.m_totalMaxFileSize = 0L;
        this.m_maxFileSize = 0L;
        this.m_deniedFilesList = new Vector();
        this.m_allowedFilesList = new Vector();
        this.m_denyPhysicalPath = false;
        this.m_forcePhysicalPath = false;
        this.m_contentDisposition = new String();
        this.m_files = new Files();
        this.m_formRequest = new Request();
    }

    @Deprecated
    public final void init(final ServletConfig servletconfig) throws ServletException {
        this.m_application = servletconfig.getServletContext();
    }
    
    @Deprecated
    public void service(final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse) throws ServletException, IOException {
        this.m_request = httpservletrequest;
        this.m_response = httpservletresponse;
    }
    
    public final void initialize(final ServletConfig servletconfig, final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse) throws ServletException {
        this.m_application = servletconfig.getServletContext();
        this.m_request = httpservletrequest;
        this.m_response = httpservletresponse;
    }
    
    public final void initialize(final PageContext pagecontext) throws ServletException {
        this.m_application = pagecontext.getServletContext();
        this.m_request = (HttpServletRequest)pagecontext.getRequest();
        this.m_response = (HttpServletResponse)pagecontext.getResponse();
    }
    
    @Deprecated
    public final void initialize(final ServletContext servletcontext, final HttpSession httpsession, final HttpServletRequest httpservletrequest, final HttpServletResponse httpservletresponse, final JspWriter jspwriter) throws ServletException {
        this.m_application = servletcontext;
        this.m_request = httpservletrequest;
        this.m_response = httpservletresponse;
    }
    
    public void upload() throws ServletException, IOException, SmartUploadException {
        int i = 0;
        final boolean flag = false;
        long l = 0L;
        boolean flag2 = false;
        final String s = new String();
        final String s2 = new String();
        String s3 = new String();
        String s4 = new String();
        String s5 = new String();
        String s6 = new String();
        String s7 = new String();
        String s8 = new String();
        String s9 = new String();
        final boolean flag3 = false;
        this.m_totalBytes = this.m_request.getContentLength();
        this.m_binArray = new byte[this.m_totalBytes];
        while (i < this.m_totalBytes) {
            int j;
            try {
                this.m_request.getInputStream();
                j = this.m_request.getInputStream().read(this.m_binArray, i, this.m_totalBytes - i);
            }
            catch (Exception exception) {
                throw new SmartUploadException("Unable to upload.");
            }
            i += j;
        }
        while (!flag2 && this.m_currentIndex < this.m_totalBytes) {
            if (this.m_binArray[this.m_currentIndex] == 13) {
                flag2 = true;
            }
            else {
                this.m_boundary = String.valueOf(this.m_boundary) + (char)this.m_binArray[this.m_currentIndex];
            }
            ++this.m_currentIndex;
        }
        if (this.m_currentIndex == 1) {
            return;
        }
        ++this.m_currentIndex;
        while (this.m_currentIndex < this.m_totalBytes) {
            final String s10 = this.getDataHeader();
            this.m_currentIndex += 2;
            final boolean flag4 = s10.indexOf("filename") > 0;
            final String s11 = this.getDataFieldValue(s10, "name");
            if (flag4) {
                s5 = this.getDataFieldValue(s10, "filename");
                s3 = this.getFileName(s5);
                s4 = this.getFileExt(s3);
                s6 = this.getContentType(s10);
                s7 = this.getContentDisp(s10);
                s8 = this.getTypeMIME(s6);
                s9 = this.getSubTypeMIME(s6);
            }
            this.getDataSection();
            if (flag4 && s3.length() > 0) {
                if (this.m_deniedFilesList.contains(s4)) {
                    throw new SecurityException("The extension of the file is denied to be uploaded (1015).");
                }
                if (!this.m_allowedFilesList.isEmpty() && !this.m_allowedFilesList.contains(s4)) {
                    throw new SecurityException("The extension of the file is not allowed to be uploaded (1010).");
                }
                if (this.m_maxFileSize > 0L && this.m_endData - this.m_startData + 1 > this.m_maxFileSize) {
                    throw new SecurityException("Size exceeded for this file : " + s3 + " (1105).");
                }
                l += this.m_endData - this.m_startData + 1;
                if (this.m_totalMaxFileSize > 0L && l > this.m_totalMaxFileSize) {
                    throw new SecurityException("Total File Size exceeded (1110).");
                }
            }
            if (flag4) {
                final File file = new File();
                file.setParent(this);
                file.setFieldName(s11);
                file.setFileName(s3);
                file.setFileExt(s4);
                file.setFilePathName(s5);
                file.setIsMissing(s5.length() == 0);
                file.setContentType(s6);
                file.setContentDisp(s7);
                file.setTypeMIME(s8);
                file.setSubTypeMIME(s9);
                if (s6.indexOf("application/x-macbinary") > 0) {
                    this.m_startData += 128;
                }
                file.setSize(this.m_endData - this.m_startData + 1);
                file.setStartData(this.m_startData);
                file.setEndData(this.m_endData);
                this.m_files.addFile(file);
            }
            else {
                final String s12 = new String(this.m_binArray, this.m_startData, this.m_endData - this.m_startData + 1);
                this.m_formRequest.putParameter(s11, s12);
            }
            if ((char)this.m_binArray[this.m_currentIndex + 1] == '-') {
                break;
            }
            this.m_currentIndex += 2;
        }
    }
    
    public int save(final String s) throws ServletException, IOException, SmartUploadException {
        return this.save(s, 0);
    }
    
    public int save(String s, final int i) throws ServletException, IOException, SmartUploadException {
        int j = 0;
        if (s == null) {
            s = this.m_application.getRealPath("/");
        }
        if (s.indexOf("/") != -1) {
            if (s.charAt(s.length() - 1) != '/') {
                s = String.valueOf(s) + "/";
            }
        }
        else if (s.charAt(s.length() - 1) != '\\') {
            s = String.valueOf(s) + "\\";
        }
        for (int k = 0; k < this.m_files.getCount(); ++k) {
            if (!this.m_files.getFile(k).isMissing()) {
                this.m_files.getFile(k).saveAs(String.valueOf(s) + this.m_files.getFile(k).getFileName(), i);
                ++j;
            }
        }
        return j;
    }
    
    public int getSize() {
        return this.m_totalBytes;
    }
    
    public byte getBinaryData(final int i) {
        byte byte0;
        try {
            byte0 = this.m_binArray[i];
        }
        catch (Exception exception) {
            throw new ArrayIndexOutOfBoundsException("Index out of range (1005).");
        }
        return byte0;
    }
    
    public Files getFiles() {
        return this.m_files;
    }
    
    public Request getRequest() {
        return this.m_formRequest;
    }
    
    public void downloadFile(final String s) throws ServletException, IOException, SmartUploadException {
        this.downloadFile(s, null, null);
    }
    
    public void downloadFile(final String s, final String s1) throws ServletException, IOException, SmartUploadException {
        this.downloadFile(s, s1, null);
    }
    
    public void downloadFile(final String s, final String s1, final String s2) throws ServletException, IOException, SmartUploadException {
        this.downloadFile(s, s1, s2, 65000);
    }
    
    public void downloadFile(String s, final String s1, final String s2, final int i) throws ServletException, IOException, SmartUploadException {
        if (s == null) {
            throw new IllegalArgumentException("File '" + s + "' not found (1040).");
        }
        if (s.equals("")) {
            throw new IllegalArgumentException("File '" + s + "' not found (1040).");
        }
        if (!this.isVirtual(s) && this.m_denyPhysicalPath) {
            throw new SecurityException("Physical path is denied (1035).");
        }
        if (this.isVirtual(s)) {
            s = this.m_application.getRealPath(s);
        }
        final java.io.File file = new java.io.File(s);
        final FileInputStream fileinputstream = new FileInputStream(file);
        final long l = file.length();
        final boolean flag = false;
        int k = 0;
        final byte[] abyte0 = new byte[i];
        if (s1 == null) {
            this.m_response.setContentType("application/x-msdownload");
        }
        else if (s1.length() == 0) {
            this.m_response.setContentType("application/x-msdownload");
        }
        else {
            this.m_response.setContentType(s1);
        }
        this.m_response.setContentLength((int)l);
        this.m_contentDisposition = ((this.m_contentDisposition != null) ? this.m_contentDisposition : "attachment;");
        if (s2 == null) {
            this.m_response.setHeader("Content-Disposition", String.valueOf(this.m_contentDisposition) + " filename=" + toUtf8String(this.getFileName(s)));
        }
        else if (s2.length() == 0) {
            this.m_response.setHeader("Content-Disposition", this.m_contentDisposition);
        }
        else {
            this.m_response.setHeader("Content-Disposition", String.valueOf(this.m_contentDisposition) + " filename=" + toUtf8String(s2));
        }
        while (k < l) {
            final int j = fileinputstream.read(abyte0, 0, i);
            k += j;
            this.m_response.getOutputStream().write(abyte0, 0, j);
        }
        fileinputstream.close();
    }
    
    public static String toUtf8String(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            if (c >= '\0' && c <= '\u00ff') {
                sb.append(c);
            }
            else {
                byte[] b;
                try {
                    b = Character.toString(c).getBytes("utf-8");
                }
                catch (Exception ex) {
                    System.out.println(ex);
                    b = new byte[0];
                }
                for (int j = 0; j < b.length; ++j) {
                    int k = b[j];
                    if (k < 0) {
                        k += 256;
                    }
                    sb.append("%" + Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }
    
    public void downloadField(final ResultSet resultset, final String s, final String s1, final String s2) throws ServletException, IOException, SQLException {
        if (resultset == null) {
            throw new IllegalArgumentException("The RecordSet cannot be null (1045).");
        }
        if (s == null) {
            throw new IllegalArgumentException("The columnName cannot be null (1050).");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("The columnName cannot be empty (1055).");
        }
        final byte[] abyte0 = resultset.getBytes(s);
        if (s1 == null) {
            this.m_response.setContentType("application/x-msdownload");
        }
        else if (s1.length() == 0) {
            this.m_response.setContentType("application/x-msdownload");
        }
        else {
            this.m_response.setContentType(s1);
        }
        this.m_response.setContentLength(abyte0.length);
        if (s2 == null) {
            this.m_response.setHeader("Content-Disposition", "attachment;");
        }
        else if (s2.length() == 0) {
            this.m_response.setHeader("Content-Disposition", "attachment;");
        }
        else {
            this.m_response.setHeader("Content-Disposition", "attachment; filename=" + s2);
        }
        this.m_response.getOutputStream().write(abyte0, 0, abyte0.length);
    }
    
    public void fieldToFile(final ResultSet resultset, final String s, String s1) throws ServletException, IOException, SmartUploadException, SQLException {
        try {
            if (this.m_application.getRealPath(s1) != null) {
                s1 = this.m_application.getRealPath(s1);
            }
            final InputStream inputstream = resultset.getBinaryStream(s);
            final FileOutputStream fileoutputstream = new FileOutputStream(s1);
            int i;
            while ((i = inputstream.read()) != -1) {
                fileoutputstream.write(i);
            }
            fileoutputstream.close();
        }
        catch (Exception exception) {
            throw new SmartUploadException("Unable to save file from the DataBase (1020).");
        }
    }
    
    private String getDataFieldValue(final String s, final String s1) {
        String s2 = new String();
        String s3 = new String();
        int i = 0;
        final boolean flag = false;
        final boolean flag2 = false;
        final boolean flag3 = false;
        s2 = String.valueOf(s1) + "=" + '\"';
        i = s.indexOf(s2);
        if (i > 0) {
            final int k;
            final int j = k = i + s2.length();
            s2 = "\"";
            final int l = s.indexOf(s2, j);
            if (k > 0 && l > 0) {
                s3 = s.substring(k, l);
            }
        }
        return s3;
    }
    
    private String getFileExt(final String s) {
        String s2 = new String();
        int i = 0;
        int j = 0;
        if (s == null) {
            return null;
        }
        i = s.lastIndexOf(46) + 1;
        j = s.length();
        s2 = s.substring(i, j);
        if (s.lastIndexOf(46) > 0) {
            return s2;
        }
        return "";
    }
    
    private String getContentType(final String s) {
        String s2 = new String();
        String s3 = new String();
        int i = 0;
        final boolean flag = false;
        s2 = "Content-Type:";
        i = s.indexOf(s2) + s2.length();
        if (i != -1) {
            final int j = s.length();
            s3 = s.substring(i, j);
        }
        return s3;
    }
    
    private String getTypeMIME(final String s) {
        final String s2 = new String();
        int i = 0;
        i = s.indexOf("/");
        if (i != -1) {
            return s.substring(1, i);
        }
        return s;
    }
    
    private String getSubTypeMIME(final String s) {
        final String s2 = new String();
        int i = 0;
        final boolean flag = false;
        i = s.indexOf("/") + 1;
        if (i != -1) {
            final int j = s.length();
            return s.substring(i, j);
        }
        return s;
    }
    
    private String getContentDisp(final String s) {
        String s2 = new String();
        int i = 0;
        int j = 0;
        i = s.indexOf(":") + 1;
        j = s.indexOf(";");
        s2 = s.substring(i, j);
        return s2;
    }
    
    private void getDataSection() {
        final boolean flag = false;
        final String s = new String();
        int i = this.m_currentIndex;
        int j = 0;
        final int k = this.m_boundary.length();
        this.m_startData = this.m_currentIndex;
        this.m_endData = 0;
        while (i < this.m_totalBytes) {
            if (this.m_binArray[i] == (byte)this.m_boundary.charAt(j)) {
                if (j == k - 1) {
                    this.m_endData = i - k + 1 - 3;
                    break;
                }
                ++i;
                ++j;
            }
            else {
                ++i;
                j = 0;
            }
        }
        this.m_currentIndex = this.m_endData + k + 3;
    }
    
    private String getDataHeader() throws UnsupportedEncodingException {
        final int i = this.m_currentIndex;
        int j = 0;
        final boolean flag = false;
        boolean flag2 = false;
        while (!flag2) {
            if (this.m_binArray[this.m_currentIndex] == 13 && this.m_binArray[this.m_currentIndex + 2] == 13) {
                flag2 = true;
                j = this.m_currentIndex - 1;
                this.m_currentIndex += 2;
            }
            else {
                ++this.m_currentIndex;
            }
        }
        final String s = new String(this.m_binArray, i, j - i + 1, "utf-8");
        return s;
    }
    
    private String getFileName(final String s) {
        final String s2 = new String();
        final String s3 = new String();
        int i = 0;
        final boolean flag = false;
        final boolean flag2 = false;
        final boolean flag3 = false;
        i = s.lastIndexOf(47);
        if (i != -1) {
            return s.substring(i + 1, s.length());
        }
        i = s.lastIndexOf(92);
        if (i != -1) {
            return s.substring(i + 1, s.length());
        }
        return s;
    }
    
    public void setDeniedFilesList(final String s) throws ServletException, IOException, SQLException {
        final String s2 = "";
        if (s != null) {
            String s3 = "";
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ',') {
                    if (!this.m_deniedFilesList.contains(s3)) {
                        this.m_deniedFilesList.addElement(s3);
                    }
                    s3 = "";
                }
                else {
                    s3 = String.valueOf(s3) + s.charAt(i);
                }
            }
            if (s3 != "") {
                this.m_deniedFilesList.addElement(s3);
            }
        }
        else {
            this.m_deniedFilesList = null;
        }
    }
    
    public void setAllowedFilesList(final String s) {
        final String s2 = "";
        if (s != null) {
            String s3 = "";
            for (int i = 0; i < s.length(); ++i) {
                if (s.charAt(i) == ',') {
                    if (!this.m_allowedFilesList.contains(s3)) {
                        this.m_allowedFilesList.addElement(s3);
                    }
                    s3 = "";
                }
                else {
                    s3 = String.valueOf(s3) + s.charAt(i);
                }
            }
            if (s3 != "") {
                this.m_allowedFilesList.addElement(s3);
            }
        }
        else {
            this.m_allowedFilesList = null;
        }
    }
    
    public void setDenyPhysicalPath(final boolean flag) {
        this.m_denyPhysicalPath = flag;
    }
    
    public void setForcePhysicalPath(final boolean flag) {
        this.m_forcePhysicalPath = flag;
    }
    
    public void setContentDisposition(final String s) {
        this.m_contentDisposition = s;
    }
    
    public void setTotalMaxFileSize(final long l) {
        this.m_totalMaxFileSize = l;
    }
    
    public void setMaxFileSize(final long l) {
        this.m_maxFileSize = l;
    }
    
    protected String getPhysicalPath(final String s, final int i) throws IOException {
        String s2 = new String();
        String s3 = new String();
        String s4 = new String();
        boolean flag = false;
        s4 = System.getProperty("file.separator");
        if (s == null) {
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        }
        if (s.equals("")) {
            throw new IllegalArgumentException("There is no specified destination file (1140).");
        }
        if (s.lastIndexOf("\\") >= 0) {
            s2 = s.substring(0, s.lastIndexOf("\\"));
            s3 = s.substring(s.lastIndexOf("\\") + 1);
        }
        if (s.lastIndexOf("/") >= 0) {
            s2 = s.substring(0, s.lastIndexOf("/"));
            s3 = s.substring(s.lastIndexOf("/") + 1);
        }
        s2 = ((s2.length() != 0) ? s2 : "/");
        final java.io.File file = new java.io.File(s2);
        if (file.exists()) {
            flag = true;
        }
        if (i == 0) {
            if (this.isVirtual(s2)) {
                s2 = this.m_application.getRealPath(s2);
                if (s2.endsWith(s4)) {
                    s2 = String.valueOf(s2) + s3;
                }
                else {
                    s2 = String.valueOf(s2) + s4 + s3;
                }
                return s2;
            }
            if (!flag) {
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
            if (this.m_denyPhysicalPath) {
                throw new IllegalArgumentException("Physical path is denied (1125).");
            }
            return s;
        }
        else if (i == 1) {
            if (this.isVirtual(s2)) {
                s2 = this.m_application.getRealPath(s2);
                if (s2.endsWith(s4)) {
                    s2 = String.valueOf(s2) + s3;
                }
                else {
                    s2 = String.valueOf(s2) + s4 + s3;
                }
                return s2;
            }
            if (flag) {
                throw new IllegalArgumentException("The path is not a virtual path.");
            }
            throw new IllegalArgumentException("This path does not exist (1135).");
        }
        else {
            if (i != 2) {
                return null;
            }
            if (flag) {
                if (this.m_denyPhysicalPath) {
                    throw new IllegalArgumentException("Physical path is denied (1125).");
                }
                return s;
            }
            else {
                if (this.isVirtual(s2)) {
                    throw new IllegalArgumentException("The path is not a physical path.");
                }
                throw new IllegalArgumentException("This path does not exist (1135).");
            }
        }
    }
    
    public void uploadInFile(String s) throws IOException, SmartUploadException {
        int i = 0;
        int j = 0;
        final boolean flag = false;
        if (s == null) {
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        }
        if (s.length() == 0) {
            throw new IllegalArgumentException("There is no specified destination file (1025).");
        }
        if (!this.isVirtual(s) && this.m_denyPhysicalPath) {
            throw new SecurityException("Physical path is denied (1035).");
        }
        i = this.m_request.getContentLength();
        this.m_binArray = new byte[i];
        while (j < i) {
            int k;
            try {
                k = this.m_request.getInputStream().read(this.m_binArray, j, i - j);
            }
            catch (Exception exception) {
                throw new SmartUploadException("Unable to upload.");
            }
            j += k;
        }
        if (this.isVirtual(s)) {
            s = this.m_application.getRealPath(s);
        }
        try {
            final java.io.File file = new java.io.File(s);
            final FileOutputStream fileoutputstream = new FileOutputStream(file);
            fileoutputstream.write(this.m_binArray);
            fileoutputstream.close();
        }
        catch (Exception exception2) {
            throw new SmartUploadException("The Form cannot be saved in the specified file (1030).");
        }
    }
    
    private boolean isVirtual(final String s) {
        if (this.m_application.getRealPath(s) != null) {
            final java.io.File file = new java.io.File(this.m_application.getRealPath(s));
            return file.exists();
        }
        return false;
    }
}
