// 
// Decompiled by Procyon v0.5.36
// 

package com.jspsmart.upload;

import java.util.Enumeration;
import java.util.Collection;
import java.io.IOException;
import java.util.Hashtable;

public class Files
{
    private SmartUpload m_parent;
    private Hashtable m_files;
    private int m_counter;
    
    Files() {
        this.m_files = new Hashtable();
        this.m_counter = 0;
    }
    
    protected void addFile(final File value) {
        if (value == null) {
            throw new IllegalArgumentException("newFile cannot be null.");
        }
        this.m_files.put(new Integer(this.m_counter), value);
        ++this.m_counter;
    }
    
    public File getFile(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("File's index cannot be a negative value (1210).");
        }
        final File file = (File) this.m_files.get(new Integer(value));
        if (file == null) {
            throw new IllegalArgumentException("Files' name is invalid or does not exist (1205).");
        }
        return file;
    }
    
    public int getCount() {
        return this.m_counter;
    }
    
    public long getSize() throws IOException {
        long n = 0L;
        for (int i = 0; i < this.m_counter; ++i) {
            n += this.getFile(i).getSize();
        }
        return n;
    }
    
    public Collection getCollection() {
        return this.m_files.values();
    }
    
    public Enumeration getEnumeration() {
        return this.m_files.elements();
    }
}
